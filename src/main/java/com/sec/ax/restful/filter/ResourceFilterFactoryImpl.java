package com.sec.ax.restful.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.annotation.ValidatedBy;
import com.sec.ax.restful.common.ExceptionManager;
import com.sec.ax.restful.validate.Validate;
import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

/**
 * 
 * @author heesik.jeon
 *
 */

@Component
public class ResourceFilterFactoryImpl implements ResourceFilterFactory {

	private static final Logger logger = Logger.getLogger(ResourceFilterFactoryImpl.class);

    @Autowired
    private ExceptionManager exceptionManager;
	
    @Autowired
    private Map<String, Validate> map;

	/* 
	 * @see com.sun.jersey.spi.container.ResourceFilterFactory#create(com.sun.jersey.api.model.AbstractMethod)
	 */
	@Override
	public List<ResourceFilter> create(AbstractMethod am) {
		
        logger.debug("..");
        
        ArrayList<ResourceFilter> filter = new ArrayList<ResourceFilter>();

        if (am.isAnnotationPresent(ValidatedBy.class)) {

            ArrayList<Validate> validate = new ArrayList<Validate>();
            
            ValidatedBy validated = am.getAnnotation(ValidatedBy.class);
            
            String[] validatedValue = validated.value();
            
            for (String validatedName : validatedValue) {
            	
            	Validate v = map.get(validatedName);
                
            	if (v != null) {
                    validate.add(v);
                } else {
                    logger.debug("No validate found for: " + validatedName);
                }
            	
            }

			Class<?>[] target = am.getMethod().getParameterTypes();
			
            if (validate.size() > 0 && target.length > 0) {
                filter.add(new ValidateResourceFilter(target[0], validate, exceptionManager));
            }

        }

        if (filter.size() == 0) {
            return null;
        } else {
            return Collections.<ResourceFilter>unmodifiableList(filter);
        }
		
	}

}
