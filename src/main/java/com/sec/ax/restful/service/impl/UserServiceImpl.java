package com.sec.ax.restful.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.persistence.UserPersistence;
import com.sec.ax.restful.pojo.User;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.service.UserService;
import com.sec.ax.restful.utils.FormatHelper;

/**
 * 
 * @author heesik.jeon
 *
 */

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    
    @Autowired
    private UserPersistence persistence;

	/* 
	 * @see com.sec.ax.restful.service.UserService#getUsers(com.sec.ax.restful.pojo.Query, java.lang.Object)
	 */
	@Override
	public Object getUsers(Query query, Object response) throws Exception {

        logger.debug("..");
        
        response = persistence.getUsers(query);
        
		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#getUser(java.lang.String, java.lang.Object)
	 */
	@Override
	public Object getUser(String name, Object response) throws Exception {

        logger.debug("..");
        
        response = persistence.getUser(name);
        
		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#createUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public Object createUser(User user, Object response) throws Exception {

        logger.debug("..");
        
        // TODO Check duplicate entry
        
        user.setIdx(persistence.createUser(user));
        user.setSid(FormatHelper.getNumConverter(Constant.USER_BASE_NUMERAL_SYSTEM, user.getIdx()+Constant.USER_SID_BASE_VALUE));
        
        response = persistence.updateSid(user);

		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#updateUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public Object updateUser(User user, Object response) throws Exception {

        logger.debug("..");
        
        response = persistence.updateUser(user);
        
		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#deleteUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public Object deleteUser(User user, Object response) throws Exception {

        logger.debug("..");
        
        response = persistence.deleteUser(user);

		return response;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#loginUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public User loginUser(User user, Object object) throws Exception {

        logger.debug("..");
        
        user = persistence.loginUser(user);
        
        if (user != null) {
        	user.setWssid(String.valueOf(object));
        }
        
		return user;

	}
}
