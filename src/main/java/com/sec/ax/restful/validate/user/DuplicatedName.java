package com.sec.ax.restful.validate.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.service.UserService;
import com.sec.ax.restful.validate.Error;

/**
 *
 * @author heesik.jeon
 *
 */

public class DuplicatedName extends AbstractValidatedByUser {
	

    @Autowired
    private UserService service;
    
	/* 
	 * @see com.sec.ax.restful.validate.user.AbstractValidatedByUser#validate(com.sec.ax.restful.pojo.User, java.util.List)
	 */
	@Override
	public void validate(User target, List<Error> error) {
    	
		if (service.getUser(target.getName(), new Object()) != null) {
			error.add(new Error(Constant.ERR_USER_NAME_DUPLICATED, new Object[] {target.getName()}));
    	}
    	
	}
	
}
