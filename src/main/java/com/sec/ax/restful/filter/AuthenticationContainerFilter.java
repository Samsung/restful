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
        
        User user = new User();

    	String wssid = request.getHeaderValue(Constant.KEY_NAME_WSSID);

    	try {
    		
    		if (StringUtils.isBlank(wssid) || !verify(wssid)) {
    			wssid = issue();
	    	}

		} catch (AxCryptException e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ResponseElement.newFailedInstance(e.getMessage())).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());
		}

    	user.setWssid(wssid);
    	
    	logger.debug(user.getWssid());
    	
		request.setSecurityContext(new SecurityContextWrapper(user, request.getSecurityContext()));
		
		return request;
        
	}
	
	/**
	 * @param wssid
	 * @return
	 */
	protected boolean verify(String wssid) {
		
		try {
			
			AxCrypt.decrypt(wssid);

		} catch (AxCryptException e) {
			logger.error("wssid has been modified");
			return false;
		}

		return true;

	}
	
	/**
	 * @return
	 * @throws AxCryptException
	 */
	protected String issue() throws AxCryptException {
		
		String random = RandomStringUtils.randomAlphanumeric(Constant.KEY_NAME_WSSID_RAMDOM_COUNT);
		
		return AxCrypt.encrypt(random);
		
	}

	private class SecurityContextWrapper implements SecurityContext {

		private final User user;
		private final SecurityContext security;

		public SecurityContextWrapper(User user, SecurityContext security) {
			this.user = user;
			this.security = security;
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
			return security.isSecure();
		}

		@Override
		public String getAuthenticationScheme() {
			return security.getAuthenticationScheme();
		}
		
	}

}
