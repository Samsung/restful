package com.sec.ax.restful.filter;

import java.security.Principal;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.crypt.AxCryptException;
import com.sec.ax.restful.crypt.aes.AxCrypt;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.pojo.Role;
import com.sec.ax.restful.pojo.User;
import com.sun.jersey.api.core.HttpRequestContext;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 *
 * @author heesik.jeon
 *
 */

@Component
public class AuthenticationContainerFilter implements ContainerRequestFilter {

	private static final Logger logger = Logger.getLogger(AuthenticationContainerFilter.class);
	
	/* 
	 * @see com.sun.jersey.spi.container.ContainerRequestFilter#filter(com.sun.jersey.spi.container.ContainerRequest)
	 */
	@Override
	public ContainerRequest filter(ContainerRequest request) {

        logger.debug("..");
        
    	try {
    		
            User user = new User();
            
        	user = getCookie(request, user, Constant.COOKIE_USER_KEY);
        	
    		request.setSecurityContext(new SecurityContextWrapper(user, request.getSecurityContext()));
    		
		} catch (AxCryptException e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ResponseElement.newFailedInstance(e.getMessage())).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());
		}
        
		return request;
        
	}
	
	private class SecurityContextWrapper implements SecurityContext {

		private final User user;
		private final SecurityContext context;

		public SecurityContextWrapper(User user, SecurityContext context) {
			this.user = user;
			this.context = context;
		}

		@Override
		public Principal getUserPrincipal() {
			return user;
		}

		@Override
		public boolean isUserInRole(String role) {
            return user.getRole() == Role.valueOf(role);
		}

		@Override
		public boolean isSecure() {
			return context.isSecure();
		}

		@Override
		public String getAuthenticationScheme() {
			return context.getAuthenticationScheme();
		}
		
	}

	public static final int USER_ARRAY_NAME = 0;
	public static final int USER_ARRAY_SID = 1;
	public static final int USER_ARRAY_USERNAME = 2;
	public static final int USER_ARRAY_ROLE = 3;
	public static final int USER_ARRAY_IP = 4;
	
	/**
	 * @param request
	 * @param cookiename
	 * @return
	 */
	public String getCookie(HttpRequestContext request, String cookiename) {
		
		Map<String, Cookie> cookies = request.getCookies();
		
		if (cookies.get(cookiename) != null) {
			return cookies.get(cookiename).getValue();
		}
		
		return null;
		
	}

    /**
     * @param request
     * @param user
     * @return
     * @throws AxCryptException
     */
    public User getCookie(HttpRequestContext request, User user, String cookiename) throws AxCryptException {
    	
    	String cookie = getCookie(request, cookiename);
    	
    	if (cookie != null) {
    		
    		logger.debug(cookie);
    		
        	String[] splitted = AxCrypt.decrypt(cookie).split("[|]");
        	
        	for (int i = 0 ; i < splitted.length ; i++) {
        		
    	    	switch (i) {
    				case USER_ARRAY_NAME:
    					user.setName(splitted[i]);
    					break;
    				case USER_ARRAY_SID:
    					user.setSid(splitted[i]);
    					break;
    				case USER_ARRAY_USERNAME:
    					user.setUsername(splitted[i]);
    					break;
    				case USER_ARRAY_ROLE:
    					user.setRole(Role.valueOf(splitted[i]));
    					break;
    				case USER_ARRAY_IP:
    					user.setIp(splitted[i]);
    					break;
    			}
    	    	
        	}

    	}
    	
    	return user;
    	
    }

}
