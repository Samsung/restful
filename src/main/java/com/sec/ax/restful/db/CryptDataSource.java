package com.sec.ax.restful.db;

import org.apache.commons.dbcp.BasicDataSource;

import com.sec.ax.restful.crypt.aes.AxCrypt;

/**
 * @author heesik.jeon
 *
 */

public class CryptDataSource extends BasicDataSource {
	
	/* 
	 * @see org.apache.commons.dbcp.BasicDataSource#setPassword(java.lang.String)
	 */
	@Override
	public synchronized void setPassword(String password) {
		
		try {
			
			super.setPassword(AxCrypt.decrypt("Vvb7oruYPkOHrEwoTVnXtw==", password));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
