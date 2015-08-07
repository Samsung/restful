package com.samsung.ax.restful.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.samsung.ax.restful.annotation.RolesAllowed;
import com.samsung.ax.restful.annotation.ValidatedBy;
import com.samsung.ax.restful.common.Constants;
import com.samsung.ax.restful.common.PropertiesManager;
import com.samsung.ax.restful.pojo.List;
import com.samsung.ax.restful.pojo.Note;
import com.samsung.ax.restful.pojo.Paging;
import com.samsung.ax.restful.pojo.Query;
import com.samsung.ax.restful.pojo.ResponseElement;
import com.samsung.ax.restful.pojo.Role;
import com.samsung.ax.restful.pojo.UserPrincipal;
import com.samsung.ax.restful.service.NoteService;
import com.samsung.ax.restful.utils.FormatHelper;

/**
 * 
 * @author heesik.jeon
 *
 */

@Component
@Path("/note")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class NoteResource extends AbstractResource {
    
    private static final Logger logger = Logger.getLogger(NoteResource.class);
    
    @Autowired
    private NoteService service;
    
    @Autowired
    private PropertiesManager properties;
    
    /**
     * @param note
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    @ValidatedBy({"missingSubject","missingContent"})
    public ResponseElement create(Note note) {
        
        logger.debug("..");
        
        Object object = new Object();
        
        try {
            
            UserPrincipal me = getUserPrincipal();
            
            note.setSid(me.getSid());
            note.setUsername(me.getUsername());
            
            object = service.create(note);
            
        } catch (DataAccessException e) {
            exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
        }

        logger.debug(FormatHelper.printPretty(note));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param note
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    @ValidatedBy({"missingIdx","missingSubject","missingContent"})
    public ResponseElement update(Note note) {
        
        logger.debug("..");
        
        Object object = new Object();

        try {
            
        	UserPrincipal me = getUserPrincipal();
            String sid = service.sid(note.getIdx());

            if (sid == null) {
            	exceptionManager.fireUserException(Constants.ERR_NOTE_NOT_FOUND, new Object[] {note.getIdx()});
            } else if (Role.User.equals(me.getRole()) && !StringUtils.equals(me.getSid(), sid)) {
                exceptionManager.fireUserException(Constants.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
            }
            
            object = service.update(note);
            
        } catch (DataAccessException e) {
            exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
        }

        logger.debug(FormatHelper.printPretty(note));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param note
     * @return
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    @ValidatedBy({"missingIdx"})
    public ResponseElement delete(Note note) {
        
        logger.debug("..");
        
        Object object = new Object();

        try {
            
        	UserPrincipal me = getUserPrincipal();
            String sid = service.sid(note.getIdx());
            
            if (sid == null) {
            	exceptionManager.fireUserException(Constants.ERR_NOTE_NOT_FOUND, new Object[] {note.getIdx()});
            } else if (Role.User.equals(me.getRole()) && !StringUtils.equals(me.getSid(), sid)) {
                exceptionManager.fireUserException(Constants.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
            }

            object = service.delete(note);
            
        } catch (DataAccessException e) {
            exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
        }

        logger.debug(FormatHelper.printPretty(note));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param idx
     * @return
     */
    @GET
    @Path("/{idx}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement idx(@PathParam("idx") int idx) {
        
        logger.debug("..");
        
        Object object = new Object();

        try {
            
            service.access(idx);
            
            object = service.idx(idx);
            
            if (object == null) {
                exceptionManager.fireUserException(Constants.ERR_NOTE_NOT_FOUND, new Object[] {idx});
            }

        } catch (DataAccessException e) {
            exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
        }
        
        logger.debug(FormatHelper.printPretty(idx));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param pn
     * @param search
     * @return
     */
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement list(@DefaultValue("1") @QueryParam("pn") int pn, @QueryParam("q") String search) {
        
        logger.debug("..");
        
        Object object = new Object();
        
        Query query = Query.setQuery(pn, search);
        
        try {
            
            Paging paging = query.getPaging();
            
            paging.setMaxPaging(Integer.parseInt(properties.getProperty(Constants.LIST_MAX_PAGING)));
            paging.setMaxResults(Integer.parseInt(properties.getProperty(Constants.LIST_MAX_RESULTS)));
            paging.setTotalResults(service.count(query));
            
            List list = new List();
            
            list.setQuery(query);
            list.setObject(service.list(query));
            
            object = list;
            
        } catch (DataAccessException e) {
            exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
        }
        
        logger.debug(FormatHelper.printPretty(query));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }

}
