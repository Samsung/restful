package com.sec.ax.restful.pojo;

/**
 *
 * @author heesik.jeon
 *
 */

public class Note extends Audit {
	
	private int idx;
	
	private String subject;
	private String content;
	
	private String wssdi;
	
	public Note() {		
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWssdi() {
		return wssdi;
	}

	public void setWssdi(String wssdi) {
		this.wssdi = wssdi;
	}

}
