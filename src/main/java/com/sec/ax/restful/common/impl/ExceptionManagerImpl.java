package com.sec.ax.restful.common.impl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.common.ExceptionManager;
import com.sec.ax.restful.common.LocalizationManager;
import com.sec.ax.restful.pojo.ResponseElement;

/**
 * 
 * @author heesik.jeon
 *
 */

public class ExceptionManagerImpl implements ExceptionManager {

//    private static final Logger logger = Logger.getLogger(ExceptionManagerImpl.class);

    @Autowired
    private LocalizationManager localizationManager;

    public ExceptionManagerImpl() {}

    public ExceptionManagerImpl(LocalizationManager localizationManager) {
        this.localizationManager = localizationManager;
    }

	@Override
	public void fireSystemException(Object request, Throwable cause) {

    	String referenceId = RandomStringUtils.randomAlphanumeric(16);
        String message = localizationManager.getMessage(Constant.ERR_SYSTEM_ERROR, new String[] {referenceId});

        throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ResponseElement.newFailedInstance(request, message)).type(MediaType.APPLICATION_JSON  + ";charset=utf-8").build());

	}

}
