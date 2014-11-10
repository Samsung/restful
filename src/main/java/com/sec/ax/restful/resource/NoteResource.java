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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.annotation.ValidatedBy;
import com.sec.ax.restful.pojo.List;
import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.pojo.Paging;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.resource.utils.QueryUtils;
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

    /**
     * @param pn
     * @param search
     * @return
     */
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement getNotes(@DefaultValue("1") @QueryParam("pn") int pn, @QueryParam("q") String search) {
    	
        logger.debug("..");
        
        Object object = new Object();
        
        Query query = QueryUtils.setQuery(pn, search);
        
        try {
        	
        	Paging paging = query.getPaging();
        	
        	paging.setTotalResults(service.cntNote());
        	
            List list = new List();
        	
        	list.setQuery(query);
        	list.setObject(service.getNotes(query, object));
        	
        	object = list;
        	
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(query));
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
    public ResponseElement getNote(@PathParam("idx") int idx) {
    	
        logger.debug("..");
        
        Object object = new Object();

        try {
        	object = service.getNote(idx, object);
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
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @ValidatedBy({"missingSubject","missingContent"})
    public ResponseElement createNote(Note note) {
    	
        logger.debug("..");
        
        Object object = new Object();
        
        try {
        	object = service.createNote(note, object);
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
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @ValidatedBy({"missingIdx","missingSubject","missingContent"})
    public ResponseElement updateNote(Note note) {
    	
        logger.debug("..");
        
        Object object = new Object();

        try {
        	object = service.updateNote(note, object);
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
    @ValidatedBy({"missingIdx"})
    public ResponseElement deleteNote(Note note) {
    	
        logger.debug("..");
        
        Object object = new Object();

        try {
        	object = service.deleteNote(note, object);
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }

        logger.debug(FormatHelper.printPretty(note));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }

}
