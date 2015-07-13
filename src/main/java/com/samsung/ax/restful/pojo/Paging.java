package com.samsung.ax.restful.pojo;

/**
 *
 * @author heesik.jeon
 *
 */

public class Paging {
    
    private int pn;
    private int maxPaging;
    private int maxResults;
    private int totalResults;
    
    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public int getMaxPaging() {
        return maxPaging;
    }

    public void setMaxPaging(int maxPaging) {
        this.maxPaging = maxPaging;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getSkipResults() {
        int skipResults = pn - 1;
        return skipResults * getMaxResults();
    }

}
