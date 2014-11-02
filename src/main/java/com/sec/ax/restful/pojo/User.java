package com.sec.ax.restful.pojo;

import java.security.Principal;
import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.xml.bind.annotation.XmlRootElement;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.crypt.AxCryptException;
import com.sec.ax.restful.crypt.aes.AxCrypt;
import com.sun.jersey.api.core.HttpRequestContext;

/**
 * 
 * @author ax
 *
 */

@XmlRootElement
public class User extends Audit implements Principal {

    private int idx;
    
    private String name;     // UserId
    private String pwd;
    
    private String sid;
    private String username; // UserName
    
    private String wssid;
    private String ip;
    
    public User() {
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getWssid() {
        return wssid;
    }

    public void setWssid(String wssid) {
        this.wssid = wssid;
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getCookie(HttpRequestContext request) {
		return getCookie(request, Constant.USER_COOKIE_KEY);
	}
	
	public String getCookie(HttpRequestContext request, String cookiename) {
		
		Map<String, Cookie> cookies = request.getCookies();
		
		if (cookies.get(cookiename) != null) {
			return cookies.get(cookiename).getValue();
		}
		
		return null;
		
	}
	
	public void setCookie(User user) {
	}

	public static final int USER_ARRAY_NAME = 0;
	public static final int USER_ARRAY_SID = 1;
	public static final int USER_ARRAY_USERNAME = 2;
	public static final int USER_ARRAY_WSSID = 3;
	public static final int USER_ARRAY_IP = 4;
	
    public User getUser(HttpRequestContext request, User user) throws AxCryptException {
    	
    	String cookie = user.getCookie(request);
    	
    	if (cookie != null) {
    		
        	// name|sid|username|wssid|ip
        	String[] splitted = AxCrypt.decrypt(cookie).split("[|]");
        	
        	for (int i = 0 ; i < splitted.length ; i++) {
    	    	switch (i) {
    				case USER_ARRAY_NAME:
    					user.setName(splitted[i]);
    					break;
    				case USER_ARRAY_SID:
    					user.setSid(splitted[i]);
    					break;
    				case USER_ARRAY_USERNAME:
    					user.setUsername(splitted[i]);
    					break;
    				case USER_ARRAY_WSSID:
    					user.setWssid(splitted[i]);
    					break;
    				case USER_ARRAY_IP:
    					user.setIp(splitted[i]);
    					break;
    			}
        	}

    	}
    	
    	return user;
    	
    }

}
