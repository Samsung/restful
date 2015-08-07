package com.samsung.ax.restful.validate.user;

import java.util.List;

import com.samsung.ax.restful.pojo.User;
import com.samsung.ax.restful.validate.Error;
import com.samsung.ax.restful.validate.Validate;

/**
 *
 * @author heesik.jeon
 *
 */

public abstract class AbstractUserValidate implements Validate {
    
    /* 
     * @see com.samsung.ax.restful.validate.Validate#validate(java.lang.Object, java.util.List)
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
