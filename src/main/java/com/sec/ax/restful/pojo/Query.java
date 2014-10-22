package com.sec.ax.restful.pojo;

/**
 *
 * @author heesik.jeon
 *
 */

public class Query {
	
	private String search;
	private Paging paging;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
