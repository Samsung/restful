package com.sec.ax.restful.validate.user;

import java.util.List;

import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.validate.Error;
import com.sec.ax.restful.validate.Validate;

/**
 *
 * @author heesik.jeon
 *
 */

public abstract class AbstractValidatedByUser implements Validate {
	
	/* 
	 * @see com.sec.ax.restful.validate.Validate#validate(java.lang.Object, java.util.List)
	 */
	@Override
	public void validate(Object target, List<Error> error) {
		validate((User) target, error);
	}
	
	/**
	 * @param target
	 * @param error
	 */
	public abstract void validate(User target, List<Error> error);

}
