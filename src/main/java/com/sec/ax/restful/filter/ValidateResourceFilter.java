package com.sec.ax.restful.filter;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.apache.log4j.Logger;

import com.sec.ax.restful.common.ExceptionManager;
import com.sec.ax.restful.validate.Error;
import com.sec.ax.restful.validate.Validate;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

/**
 *
 * @author heesik.jeon
 *
 */

public class ValidateResourceFilter implements ResourceFilter, ContainerRequestFilter {
	
    private static final Logger logger = Logger.getLogger(ValidateResourceFilter.class);

    private ExceptionManager exceptionManager;

    private Class<?> target;
	private List<Validate> list;
	
	/**
	 * @param target
	 * @param list
	 * @param exceptionManager
	 */
	protected ValidateResourceFilter(Class<?> target, List<Validate> list, ExceptionManager exceptionManager) {
		this.target = target;
		this.list = list;
		this.exceptionManager = exceptionManager;
	}

	/* 
	 * @see com.sun.jersey.spi.container.ContainerRequestFilter#filter(com.sun.jersey.spi.container.ContainerRequest)
	 */
	@Override
	public ContainerRequest filter(ContainerRequest request) {

        logger.debug("..");
        
        try {
        	
        	Object obj = FilterHelper.getEntity(target, request);
        	
        	List<Error> error = new ArrayList<Error>();
        	
        	for (Validate validate : list) {
        		validate.validate(obj, error);
        	}
        	
            if (error.size() > 0) {
            	exceptionManager.fireValidationException(error);
            }
            
        } catch (WebApplicationException wae) {
            throw wae;
        } catch (Exception e) {
        	exceptionManager.fireSystemException(e);
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
