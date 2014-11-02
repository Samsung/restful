package com.sec.ax.restful.filter;

import java.security.Principal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.crypt.AxCryptException;
import com.sec.ax.restful.crypt.aes.AxCrypt;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.resource.AbstractResource;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 *
 * @author heesik.jeon
 *
 */

@Component
public class WSSIDContainerFilter extends AbstractResource implements ContainerRequestFilter {

	private static final Logger logger = Logger.getLogger(WSSIDContainerFilter.class);
	
	static {
	}

	/* 
	 * @see com.sun.jersey.spi.container.ContainerRequestFilter#filter(com.sun.jersey.spi.container.ContainerRequest)
	 */
	@Override
	public ContainerRequest filter(ContainerRequest request) {

        logger.debug("..");
        
    	try {
    		
    		User user = new User();
    		
            String wssid = request.getHeaderValue(Constant.USER_WSSID_KEY);
            
    		if (StringUtils.isBlank(wssid)) {
    			
    			wssid = issue();
    		
    		} else {
    			
    			wssid = decode(wssid);
    			
        		if (request.getUserPrincipal() != null && StringUtils.isNotBlank(request.getUserPrincipal().getName())) {
        			
        			user = getUserPrincipal();
        			
        			if (!StringUtils.equals(wssid, user.getWssid())) {
            			wssid = issue();
            			// TODO remove cookie
            			throw new WebApplicationException(Response.status(Response.Status.OK).entity(ResponseElement.newWSSIDInstance(wssid)).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());
        			}
            		
        		}

    		}
    		
        	user.setWssid(wssid);
        	
        	logger.debug(user.getWssid());
        	
    		request.setSecurityContext(new SecurityContextWrapper(user, request.getSecurityContext()));
    		
		} catch (AxCryptException e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ResponseElement.newFailedInstance(e.getMessage())).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());
		}

		return request;
        
	}
	
	/**
	 * @return
	 * @throws AxCryptException
	 */
	protected String issue() throws AxCryptException {
		return AxCrypt.encrypt(RandomStringUtils.randomAlphanumeric(Constant.USER_WSSID_RAMDOM_COUNT));
	}
	
	/**
	 * @param wssid
	 * @return
	 * @throws AxCryptException
	 */
	protected String decode(String wssid) throws AxCryptException {
		return AxCrypt.decrypt(wssid);
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
			return false;
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

}
