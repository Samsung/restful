package com.sec.ax.restful.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author heesik.jeon
 *
 */

@XmlRootElement
public class ResponseElement {

    private String status;
    private Object request;
    private Object response;
    
    public ResponseElement() {}

    public ResponseElement(String status, Object response) {
    	this.status = status;
    	this.response = response;
    }

    public ResponseElement(String status, Object request, Object response) {
        this.status = status;
        this.request = request;
        this.response = response;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

    public static ResponseElement newSuccessInstance(Object response) {
        return new ResponseElement("OK", response);
    }

    public static ResponseElement newSuccessInstance(Object request, Object response) {
        return new ResponseElement("OK", request, response);
    }

    public static ResponseElement newFailedInstance(Object request, Object message) {
        return new ResponseElement("FAILED", request, message);
    }

}
