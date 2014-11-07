package com.sec.ax.restful.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.persistence.UserPersistence;
import com.sec.ax.restful.pojo.Query;
import com.sec.ax.restful.pojo.User;
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
	public Object getUsers(Query query, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.getUsers(query);
        
		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#getUser(java.lang.String, java.lang.Object)
	 */
	@Override
	public Object getUser(String name, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.getUser(name);
        
		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#createUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public Object createUser(User user, Object object) throws DataAccessException {

        logger.debug("..");
        
        // TODO Check duplicate entry
        
        user.setIdx(persistence.createUser(user));
        user.setSid(FormatHelper.getNumConverter(Constant.USER_BASE_NUMERAL_SYSTEM, user.getIdx()+Constant.USER_SID_BASE_VALUE));
        
        object = persistence.updateSid(user);

		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#updateUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public Object updateUser(User user, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.updateUser(user);
        
		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#deleteUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public Object deleteUser(User user, Object object) throws DataAccessException {

        logger.debug("..");
        
        object = persistence.deleteUser(user);

		return object;
		
	}

	/* 
	 * @see com.sec.ax.restful.service.UserService#loginUser(com.sec.ax.restful.pojo.User, java.lang.Object)
	 */
	@Override
	public User loginUser(User user) throws DataAccessException {

        logger.debug("..");
        
        user = persistence.loginUser(user);
        
		return user;

	}
	
	/* 
	 * @see com.sec.ax.restful.service.UserService#expiryCookie(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    public void expiryCookie(@Context HttpServletRequest request, @Context HttpServletResponse response) {
    	
    	Cookie[] cookies = request.getCookies();
    	
    	for(Cookie cookie : cookies) {
    		
    		logger.debug(cookie.getName() + ": " + cookie.getValue());
    		
    		if (StringUtils.equals(Constant.COOKIE_USER_KEY, cookie.getName())) {

            	cookie.setDomain(Constant.COOKIE_DOMAIN);
        		cookie.setMaxAge(Constant.COOKIE_EXPIRY);
            	cookie.setPath("/");

        		response.addCookie(cookie);
        		
        		break;
        		
    		}
    		
    	}

    }


}
