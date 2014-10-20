package com.sec.ax.restful.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.springframework.stereotype.Component;

import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.service.NoteService;

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

    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement getNotes(@QueryParam("pn") int pn) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.getNotes(pn, response);
        } catch (Exception e) {
        	e.printStackTrace();
        	exceptionManager.fireSystemException(null, new Exception(e));
        }
        
        return ResponseElement.newSuccessInstance(pn, response);

    }

    @GET
    @Path("/{idx}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement getNote(@PathParam("idx") int idx) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.getNote(idx, response);
        } catch (Exception e) {
        	e.printStackTrace();
        	exceptionManager.fireSystemException(idx, new Exception(e));
        }
        
        return ResponseElement.newSuccessInstance(idx, response);

    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement createNote(Object request) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.createNote(request, response);
        } catch (Exception e) {
        	e.printStackTrace();
        	exceptionManager.fireSystemException(request, new Exception(e));
        }
        
        return ResponseElement.newSuccessInstance(request, response);

    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement updateNote(Object request) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.updateNote(request, response);
        } catch (Exception e) {
        	e.printStackTrace();
        	exceptionManager.fireSystemException(request, new Exception(e));
        }
        
        return ResponseElement.newSuccessInstance(request, response);

    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement deleteNote(Object request) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.deleteNote(request, response);
        } catch (Exception e) {
        	e.printStackTrace();
        	exceptionManager.fireSystemException(request, new Exception(e));
        }
        
        return ResponseElement.newSuccessInstance(request, response);

    }

}
