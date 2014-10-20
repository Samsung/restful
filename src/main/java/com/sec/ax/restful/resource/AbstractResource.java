package com.sec.ax.restful.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.common.ExceptionManager;

/**
 * 
 * @author heesik.jeon
 *
 */

@Component
public abstract class AbstractResource {

    @Autowired
    protected ExceptionManager exceptionManager;

}
