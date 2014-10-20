package com.sec.ax.restful.pojo;

/**
 *
 * @author heesik.jeon
 *
 */

public class Paging {
	
	private int pn;
	private int maxResults;
	
	public Paging() {
		this.setMaxResults(10);
	}
	
	public int getPn() {
		
		if (pn > 0) {
			pn = pn - 1;
		}

		return pn * getMaxResults();
		
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

}
