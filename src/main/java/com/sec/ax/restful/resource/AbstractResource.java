package com.sec.ax.restful.resource;

import java.security.Principal;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sec.ax.restful.common.ExceptionManager;
import com.sec.ax.restful.pojo.User;

/**
 * 
 * @author heesik.jeon
 *
 */

@Component
public abstract class AbstractResource {

    @Context
    protected SecurityContext sc;

    @Autowired
    protected ExceptionManager exceptionManager;

    /**
     * @return
     */
    protected User getUserPrincipal() {
        
        Principal principal = sc.getUserPrincipal();
        
        if (!(principal instanceof User)) {
            exceptionManager.fireSystemException(new NullPointerException("User principle has not been loaded."));
        }
        
        return (User) principal;
    
    }

}
