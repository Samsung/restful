package com.sec.ax.restful.common.impl;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.common.ExceptionManager;
import com.sec.ax.restful.common.LocalizationManager;
import com.sec.ax.restful.pojo.ResponseElement;
import com.sec.ax.restful.validate.Error;

/**
 * 
 * @author heesik.jeon
 *
 */

public class ExceptionManagerImpl implements ExceptionManager {

    private static final Logger logger = Logger.getLogger(ExceptionManagerImpl.class);

    @Autowired
    private LocalizationManager localizationManager;

    public ExceptionManagerImpl() {}

    /**
     * @param localizationManager
     */
    public ExceptionManagerImpl(LocalizationManager localizationManager) {
        this.localizationManager = localizationManager;
    }

	@Override
	public void fireSystemException(Throwable cause) {

		logger.debug("..");

    	String referenceId = RandomStringUtils.randomAlphanumeric(16);
        String message = localizationManager.getMessage(Constant.ERR_SYSTEM_ERROR, new String[] {referenceId});

        throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ResponseElement.newFailedInstance(message)).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());
		
	}

    /* 
     * @see com.sec.ax.restful.common.ExceptionManager#fireUserException(java.lang.String, java.lang.Object[])
     */
    @Override
    public void fireUserException(String code, Object[] args) {
    	
		logger.debug("..");
		
		String message = localizationManager.getMessage(code, args);
        
        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ResponseElement.newFailedInstance(message)).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());

    }

	/* 
	 * @see com.sec.ax.restful.common.ExceptionManager#fireValidationException(java.util.List)
	 */
	@Override
	public void fireValidationException(List<Error> error) {
		
		String[] messages = new String[error.size()];
		
        for (int i = 0; i < error.size(); i++) {
            Error err = error.get(i);
            messages[i] = localizationManager.getMessage(err.getCode(), err.getArgs());
        }

        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(ResponseElement.newFailedInstance(StringUtils.join(messages, ", "))).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());

	}

}
