package com.samsung.ax.restful.validate.user;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.samsung.ax.restful.common.Constants;
import com.samsung.ax.restful.pojo.User;
import com.samsung.ax.restful.service.UserService;
import com.samsung.ax.restful.validate.Error;

/**
 *
 * @author heesik.jeon
 *
 */

public class ValidatingName extends AbstractUserValidate {
    

    @Autowired
    private UserService service;
    
    /* 
     * @see com.samsung.ax.restful.validate.user.AbstractValidatedByUser#validate(com.samsung.ax.restful.pojo.User, java.util.List)
     */
    @Override
    public void validate(User target, List<Error> error) {
        
        if (service.name(target.getName()) != null) {
            error.add(new Error(Constants.ERR_USER_NAME_DUPLICATED, new Object[] {target.getName()}));
        } else if (target.getName().length() < Constants.USER_NAME_MIN_LENGTH || target.getName().length() > Constants.USER_NAME_MAX_LENGTH) {
            error.add(new Error(Constants.ERR_USER_NAME_LENGTH, new Object[] {Constants.USER_NAME_MIN_LENGTH, Constants.USER_NAME_MAX_LENGTH}));
        } else if (!isNamePattern(target.getName())) {
            error.add(new Error(Constants.ERR_USER_NAME_PATTERN, null));
        }
        
    }
    
    private boolean isNamePattern(String name) {
        
        String regex = "^[a-z0-9.-]{3,15}+$";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(name);
        
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }

    }
    
}
