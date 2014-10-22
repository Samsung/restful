package com.sec.ax.restful.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

/**
 * 
 * @author heesik.jeon
 *
 */

public class ResourceFilterFactoryImpl implements ResourceFilterFactory {

	private static final Logger logger = Logger.getLogger(ResourceFilterFactoryImpl.class);

	/* 
	 * @see com.sun.jersey.spi.container.ResourceFilterFactory#create(com.sun.jersey.api.model.AbstractMethod)
	 */
	@Override
	public List<ResourceFilter> create(AbstractMethod am) {
		
        logger.debug("..");
        
        ArrayList<ResourceFilter> filter = new ArrayList<ResourceFilter>();

        
        if (filter.size() == 0) {
            logger.debug("Created 0 resource filter for method: " + am.getMethod().getName());
            return null;
        } else {
            logger.debug("Created " + filter.size() + " resource filter(s) for method: " + am.getMethod().getName());
            return Collections.<ResourceFilter>unmodifiableList(filter);
        }
		
	}

}
