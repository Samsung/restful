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
    private Object response;
    
    public ResponseElement() {}

    public ResponseElement(String status, Object response) {
    	this.status = status;
    	this.response = response;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

    public static ResponseElement newFailedInstance(Object message) {
        return new ResponseElement("FAILED", message);
    }

    public static ResponseElement newWSSIDInstance(Object response) {
        return new ResponseElement("WSSID", response);
    }

}
