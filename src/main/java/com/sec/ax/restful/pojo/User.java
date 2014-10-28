package com.sec.ax.restful.pojo;

import java.security.Principal;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ax
 *
 */

@XmlRootElement
public class User extends Audit implements Principal {

    private int idx;
    
	private String name;     // UserId
    private String username; // UserName
	
	private String wssid;
    private String sid;

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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
