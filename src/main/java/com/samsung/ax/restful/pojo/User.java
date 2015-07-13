package com.samsung.ax.restful.pojo;

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
    private String pwd;
    
    private String sid;
    private String username; // UserName
    private Role role; 
    private int status;
    
    private String ip;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
