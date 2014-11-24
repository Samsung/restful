package com.sec.ax.restful.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.crypt.AxCryptException;
import com.sec.ax.restful.crypt.aes.AxCrypt;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 *
 * @author heesik.jeon
 *
 */

@Component
public class WSSIDContainerFilter implements ContainerRequestFilter {

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
    		
            String wssid = request.getHeaderValue(Constant.USER_WSSID_KEY);

        	if (wssid == null) {
        		wssid = request.getQueryParameters().getFirst(Constant.USER_WSSID_KEY);
        	}

    		if (StringUtils.isBlank(wssid) || !isValid(wssid)) {
    			wssid = issue();
    			throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity(ResponseElement.newWSSIDInstance(wssid)).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());
    		}
    		
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
	protected boolean isValid(String wssid) throws AxCryptException {
		AxCrypt.decrypt(wssid);
		return true;
	}

}
