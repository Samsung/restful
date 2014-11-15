package com.sec.ax.restful.filter;

import java.security.Principal;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.common.ExceptionManager;
import com.sec.ax.restful.pojo.Role;
import com.sec.ax.restful.pojo.User;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 *
 * @author heesik.jeon
 *
 */

public class RoleControlResourceFilter implements ResourceFilter, ContainerRequestFilter {

    private static final Logger logger = Logger.getLogger(RoleControlResourceFilter.class);

    private final Role[] rolesAllowed;

    private ExceptionManager exceptionManager;

    protected RoleControlResourceFilter(ExceptionManager exceptionManager) {
        this.rolesAllowed = null;
        this.exceptionManager = exceptionManager;
    }

    protected RoleControlResourceFilter(Role[] rolesAllowed, ExceptionManager exceptionManager) {
        this.rolesAllowed = (rolesAllowed != null) ? rolesAllowed : new Role[] {};
        this.exceptionManager = exceptionManager;
    }

	/* 
	 * @see com.sun.jersey.spi.container.ContainerRequestFilter#filter(com.sun.jersey.spi.container.ContainerRequest)
	 */
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		
        logger.debug("..");
        
        Principal principal = request.getUserPrincipal();
        
        if (principal == null) {
            exceptionManager.fireSystemException(new NullPointerException(Constant.ERR_USER_AUTHENTICATION_FAILED));
        }

        if (principal instanceof User) {
        	
            User user = (User) principal;
            
            if (user.getRole() != null) {
            	
                for (Role role : rolesAllowed) {
                	if (StringUtils.equals(user.getRole().toString(), role.toString())) {
                		return request;
                	}
                }
                
                exceptionManager.fireUserException(Constant.ERR_USER_AUTHORIZATION_FAILED, new Object[] {user.getName()});
                
            } else {
                exceptionManager.fireUserException(Constant.ERR_USER_AUTHENTICATION_FAILED, null);
            }
            
        } else {
            exceptionManager.fireUserException(Constant.ERR_USER_AUTHENTICATION_FAILED, null);
        }

		return request;
		
	}

	/* 
	 * @see com.sun.jersey.spi.container.ResourceFilter#getRequestFilter()
	 */
	@Override
	public ContainerRequestFilter getRequestFilter() {
		return this;
	}

	/* 
	 * @see com.sun.jersey.spi.container.ResourceFilter#getResponseFilter()
	 */
	@Override
	public ContainerResponseFilter getResponseFilter() {
		return null;
	}

}
