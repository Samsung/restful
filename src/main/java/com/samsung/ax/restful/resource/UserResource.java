package com.samsung.ax.restful.resource;

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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.samsung.ax.restful.annotation.RolesAllowed;
import com.samsung.ax.restful.annotation.ValidatedBy;
import com.samsung.ax.restful.common.Constants;
import com.samsung.ax.restful.common.PropertiesManager;
import com.samsung.ax.restful.crypt.AxCryptException;
import com.samsung.ax.restful.pojo.List;
import com.samsung.ax.restful.pojo.Paging;
import com.samsung.ax.restful.pojo.Query;
import com.samsung.ax.restful.pojo.ResponseElement;
import com.samsung.ax.restful.pojo.Role;
import com.samsung.ax.restful.pojo.User;
import com.samsung.ax.restful.pojo.UserPrincipal;
import com.samsung.ax.restful.service.UserService;
import com.samsung.ax.restful.utils.FormatHelper;

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
    
    @Autowired
    private PropertiesManager properties;
    
    /**
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@POST
	@Path("/signin")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseElement signin(@Context HttpServletRequest request, @Context HttpServletResponse response, User user) {
	    
	    logger.debug("..");
	    
	    try {
	        
	        user = service.signin(request, response, user);
	        
	        if (user == null) {
	            exceptionManager.fireUserException(Constants.ERR_USER_LOGIN_FAILED, null);
	        }
	        
	    } catch (DataAccessException e) {
	        exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
	    } catch (AxCryptException e) {
	        exceptionManager.fireSystemException(new Exception(e));
	    }
	    
	    logger.debug(FormatHelper.printPretty(user));
	    
	    return ResponseElement.newSuccessInstance(true);
	    
	}
	
	/**
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({Role.Admin,Role.User})
	public ResponseElement update(@Context HttpServletRequest request, @Context HttpServletResponse response, User user) {
	    
	    logger.debug("..");
	    
	    Object object = new Object();
	
	    try {
	        
	    	UserPrincipal me = getUserPrincipal();
	        User target = (User) service.name(user.getName());
	        
	        if (target == null) {
	            exceptionManager.fireUserException(Constants.ERR_USER_NOT_FOUND, new Object[] {user.getName()});
	        } else if (Role.User.equals(me.getRole()) && !StringUtils.equals(me.getSid(), target.getSid())) {
	            exceptionManager.fireUserException(Constants.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
	        } else if (user.getRole() != null && !Role.Admin.equals(me.getRole())) {
	            exceptionManager.fireUserException(Constants.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
	        }
	        
	        object = service.update(user);
	        
	        if (StringUtils.equals(me.getSid(), target.getSid())) {
	            if(user.getName() == null) user.setName(target.getName());
	            if(user.getSid() == null) user.setSid(target.getSid());
	            if(user.getUsername() == null) user.setUsername(target.getUsername());
	            if(user.getRole() == null) user.setRole(target.getRole());
	            service.cookie(request, response, user);
	        }
	        
	    } catch (DataAccessException e) {
	        exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
	    } catch (AxCryptException e) {
	        exceptionManager.fireSystemException(new Exception(e));
	    }
	
	    logger.debug(FormatHelper.printPretty(user));
	    logger.debug(FormatHelper.printPretty(object));
	    
	    return ResponseElement.newSuccessInstance(object);
	
	}
    
	/**
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({Role.Admin,Role.User})
	public ResponseElement delete(@Context HttpServletRequest request, @Context HttpServletResponse response, User user) {
	    
	    logger.debug("..");
	    
	    Object object = new Object();
	
	    try {
	        
	    	UserPrincipal me = getUserPrincipal();
	        User target = (User) service.name(user.getName());
	
	        if (target == null) {
	            exceptionManager.fireUserException(Constants.ERR_USER_NOT_FOUND, new Object[] {user.getName()});
	        } else if (Role.User.equals(me.getRole()) && !StringUtils.equals(me.getSid(), target.getSid())) {
	            exceptionManager.fireUserException(Constants.ERR_USER_AUTHORIZATION_FAILED, new Object[] {me.getName()});
	        }
	
	        object = service.delete(user);
	        
	        if (StringUtils.equals(me.getSid(), target.getSid())) {
	            service.signout(request, response);
	        }
	        
	    } catch (DataAccessException e) {
	        exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
	    }
	
	    logger.debug(FormatHelper.printPretty(user));
	    logger.debug(FormatHelper.printPretty(object));
	    
	    return ResponseElement.newSuccessInstance(object);
	
	}
	
	/**
     * @param user
     * @return
     */
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @ValidatedBy({"validatingName"})
    public ResponseElement signup(User user) {
        
        logger.debug("..");
        
        Object object = new Object();
        
        try {
            
            user.setRole(Role.User);
            user.setIdx(service.signup(user));
            user.setSid(FormatHelper.convertNumeral(Constants.USER_BASE_NUMERAL_SYSTEM, user.getIdx()+Constants.USER_SID_BASE_VALUE));
            
            object = service.sid(user);

        } catch (DataAccessException e) {
            exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
        }

        logger.debug(FormatHelper.printPretty(user));
        logger.debug(FormatHelper.printPretty(object));
        
        return ResponseElement.newSuccessInstance(object);

    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("/signout")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseElement signout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        
        logger.debug("..");
        
        try {
            service.signout(request, response);
        } catch (Exception e) {
            exceptionManager.fireSystemException(new Exception(e));
        }
        
        return ResponseElement.newSuccessInstance(true);
        
    }
    
    /**
     * @return
     */
    @GET
    @Path("/profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin,Role.User})
    public ResponseElement profile() {
        
        logger.debug("..");
        
        UserPrincipal me = getUserPrincipal();
        
        logger.debug(FormatHelper.printPretty(me));
        
        return ResponseElement.newSuccessInstance(me);

    }
    
    /**
     * @param name
     * @return
     */
    @GET
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Role.Admin})
    public ResponseElement name(@PathParam("name") String name) {
        
        logger.debug("..");
        
        Object object = new Object();

        try {
            object = service.name(name);
        } catch (DataAccessException e) {
            exceptionManager.fireUserException(Constants.ERR_DATA_ACCESS, null);
        }
        
        logger.debug(FormatHelper.printPretty(name));
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
    @RolesAllowed({Role.Admin})
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
