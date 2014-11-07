package com.sec.ax.restful.resource;

import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.annotation.RolesAllowed;
import com.sec.ax.restful.annotation.ValidatedBy;
import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.crypt.AxCryptException;
import com.sec.ax.restful.crypt.aes.AxCrypt;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.pojo.Role;
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
    @RolesAllowed({Role.Admin})
    public ResponseElement getUsers(@DefaultValue("1") @QueryParam("pn") int pn, @QueryParam("q") String search) {
    	
        logger.debug("..");
        
        Query query = QueryUtils.setQuery(pn, search);
        
        Object object = new Object();
        
        try {
        	object = service.getUsers(query, object);
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
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin})
    public ResponseElement getUser(@PathParam("name") String name) {
    	
        logger.debug("..");
        
        Object object = new Object();

        try {
        	object = service.getUser(name, object);
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(name));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }

    /**
     * @param user
     * @return
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @ValidatedBy({"duplicatedName"})
    public ResponseElement createUser(User user) {
    	
        logger.debug("..");
        
        Object object = new Object();
        
        try {
        	object = service.createUser(user, object);
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }

        logger.debug(FormatHelper.printPretty(user));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }

    /**
     * @param user
     * @return
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    public ResponseElement updateUser(User user) {
    	
        logger.debug("..");
        
        Object object = new Object();

        try {
        	object = service.updateUser(user, object);
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }

        logger.debug(FormatHelper.printPretty(user));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param user
     * @return
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    public ResponseElement deleteUser(@Context HttpServletRequest request, @Context HttpServletResponse response, User user) {
    	
        logger.debug("..");
        
        Object object = new Object();

        try {
        	object = service.deleteUser(user, object);
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }

        logger.debug(FormatHelper.printPretty(user));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @return
     */
    @GET
    @Path("/me")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    public ResponseElement getMe() {
    	
        logger.debug("..");
        
        Object object = new Object();
        
        try {
        	object = getUserPrincipal();
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param request
     * @param response
     * @param user
     * @return
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement loginUser(@Context HttpServletRequest request, @Context HttpServletResponse response, User user) {
    	
        logger.debug("..");
        
        try {
        	user = service.loginUser(user);
        } catch (DataAccessException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }
        
        try {
        	
        	if (user != null) {

            	StringBuffer Ax = new StringBuffer();
            	
            	Ax.append(user.getName()).append("|");
            	Ax.append(user.getSid()).append("|");
            	Ax.append(user.getUsername()).append("|");
            	Ax.append(user.getRole()).append("|");
            	Ax.append(request.getRemoteAddr()).append("|");
            	
        		Calendar c = Calendar.getInstance();
            	Ax.append(c.getTimeInMillis());
            	
            	String crypted = AxCrypt.encrypt(Ax);
            	
            	logger.debug(crypted);
            	
            	Cookie cookie = new Cookie("Ax", crypted);
            	
            	cookie.setDomain(Constant.COOKIE_DOMAIN);
            	cookie.setMaxAge(Constant.COOKIE_MAX_AGE);
            	cookie.setPath("/");

            	response.addCookie(cookie);

        	} else {
        		exceptionManager.fireUserException(Constant.ERR_USER_LOGIN_FAILED, null);
        	}
        	
        } catch (AxCryptException e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }
        
        logger.debug(FormatHelper.printPretty(user));
        
        return ResponseElement.newSuccessInstance(true);
        
    }
    
    /**
     * @param user
     * @return
     */
    @GET
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement logoutUser(@Context HttpServletRequest request, @Context HttpServletResponse response) {
    	
        logger.debug("..");
        
        try {
        	service.expiryCookie(request, response);
        } catch (Exception e) {
        	exceptionManager.fireSystemException(new Exception(e));
        }
        
        return ResponseElement.newSuccessInstance(true);
        
    }
    
}
