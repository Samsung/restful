package com.sec.ax.restful.pojo;

import com.sec.ax.restful.common.Constant;

/**
 *
 * @author heesik.jeon
 *
 */

public class Paging {
	
	private int pn;
	private int maxResults;
	
	public Paging() {
		this.setMaxResults(Constant.PAGING_MAX_RESULTS);
	}
	
	public int getPn() {
		return pn;
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public int getSkipResults() {
		int skipResults = pn - 1;
		return skipResults * getMaxResults();
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

}
