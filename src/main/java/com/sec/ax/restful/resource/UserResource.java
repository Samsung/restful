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
import org.springframework.stereotype.Component;

import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.resource.utils.QueryUtils;
import com.sec.ax.restful.service.UserService;
import com.sec.ax.restful.utils.FormatHelper;

/**
 * 
 * @author heesik.jeon
 *
 */

@Component
@Path("/user")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class UserResource extends AbstractResource {
	
    private static final Logger logger = Logger.getLogger(UserResource.class);
    
    @Autowired
    private UserService service;

    /**
     * @param pn
     * @param search
     * @return
     */
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement getUsers(@DefaultValue("1") @QueryParam("pn") int pn, @QueryParam("q") String search) {
    	
        logger.debug("..");
        
        Query query = QueryUtils.setQuery(pn, search);
        
        Object response = new Object();
        
        try {
        	response = service.getUsers(query, response);
        } catch (Exception e) {
        	exceptionManager.fireSystemException(null, new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(query));
        logger.debug(FormatHelper.printPretty(response));
        
        return ResponseElement.newSuccessInstance(query, response);

    }

    /**
     * @param idx
     * @return
     */
    @GET
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement getUser(@PathParam("name") String name) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.getUser(name, response);
        } catch (Exception e) {
        	exceptionManager.fireSystemException(name, new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(name));
        logger.debug(FormatHelper.printPretty(response));
        
        return ResponseElement.newSuccessInstance(name, response);

    }

    /**
     * @param user
     * @return
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement createUser(User user) {
    	
        logger.debug("..");
        
        Object response = new Object();
        
        try {
        	response = service.createUser(user, response);
        } catch (Exception e) {
        	e.printStackTrace();
        	exceptionManager.fireSystemException(user, new Exception(e));
        }

        logger.debug(FormatHelper.printPretty(user));
        logger.debug(FormatHelper.printPretty(response));
        
        return ResponseElement.newSuccessInstance(user, response);

    }

    /**
     * @param user
     * @return
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement updateUser(User user) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.updateUser(user, response);
        } catch (Exception e) {
        	exceptionManager.fireSystemException(user, new Exception(e));
        }

        logger.debug(FormatHelper.printPretty(user));
        logger.debug(FormatHelper.printPretty(response));
        
        return ResponseElement.newSuccessInstance(user, response);

    }

    /**
     * @param user
     * @return
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement deleteUser(User user) {
    	
        logger.debug("..");
        
        Object response = new Object();

        try {
        	response = service.deleteUser(user, response);
        } catch (Exception e) {
        	exceptionManager.fireSystemException(user, new Exception(e));
        }

        logger.debug(FormatHelper.printPretty(user));
        logger.debug(FormatHelper.printPretty(response));
        
        return ResponseElement.newSuccessInstance(user, response);

    }

}
