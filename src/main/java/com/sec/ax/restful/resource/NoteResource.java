package com.sec.ax.restful.resource;

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

import com.sec.ax.restful.annotation.RolesAllowed;
import com.sec.ax.restful.annotation.ValidatedBy;
import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.common.PropertiesManager;
import com.sec.ax.restful.pojo.List;
import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.pojo.Paging;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.pojo.Role;
import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.service.NoteService;
import com.sec.ax.restful.utils.FormatHelper;

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
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    @ValidatedBy({"missingSubject","missingContent"})
    public ResponseElement create(Note note) {
        
        logger.debug("..");
        
        Object object = new Object();
        
        try {
        	
        	User me = getUserPrincipal();
        	
            note.setSid(me.getSid());
            note.setUsername(me.getUsername());
            
            object = service.create(note);
            
        } catch (DataAccessException e) {
            exceptionManager.fireSystemException(new Exception(e));
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
    @Path("/update/{idx}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement update(@PathParam("idx") int idx) {
        
        logger.debug("..");
        
        Object object = new Object();

        try {
        	
            User me = getUserPrincipal();
            String sid = service.sid(idx);

            if (Role.User.equals(me.getRole()) && sid != null && !StringUtils.equals(me.getSid(), sid)) {
                exceptionManager.fireUserException(Constant.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
            }

            object = service.idx(idx);
            
        } catch (DataAccessException e) {
            exceptionManager.fireSystemException(new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(idx));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param note
     * @return
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    @ValidatedBy({"missingIdx","missingSubject","missingContent"})
    public ResponseElement update(Note note) {
        
        logger.debug("..");
        
        Object object = new Object();

        try {
            
            User me = getUserPrincipal();
            String sid = service.sid(note.getIdx());

            if (Role.User.equals(me.getRole()) && sid != null && !StringUtils.equals(me.getSid(), sid)) {
                exceptionManager.fireUserException(Constant.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
            }
            
            object = service.update(note);
            
        } catch (DataAccessException e) {
            exceptionManager.fireSystemException(new Exception(e));
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
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    @ValidatedBy({"missingIdx"})
    public ResponseElement delete(Note note) {
        
        logger.debug("..");
        
        Object object = new Object();

        try {
            
            User me = getUserPrincipal();
            String sid = service.sid(note.getIdx());

            if (Role.User.equals(me.getRole()) && sid != null && !StringUtils.equals(me.getSid(), sid)) {
                exceptionManager.fireUserException(Constant.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
            }

            object = service.delete(note);
            
        } catch (DataAccessException e) {
            exceptionManager.fireSystemException(new Exception(e));
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
            
        } catch (DataAccessException e) {
            exceptionManager.fireSystemException(new Exception(e));
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
            
            paging.setMaxPaging(Integer.parseInt(properties.getProperty(Constant.LIST_MAX_PAGING)));
            paging.setMaxResults(Integer.parseInt(properties.getProperty(Constant.LIST_MAX_RESULTS)));
            paging.setTotalResults(service.count());
            
            List list = new List();
            
            list.setQuery(query);
            list.setObject(service.list(query));
            
            object = list;
            
        } catch (DataAccessException e) {
            exceptionManager.fireSystemException(new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(query));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }

}
