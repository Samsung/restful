package com.sec.ax.restful.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.HttpMethod;

import org.apache.log4j.Logger;

/**
 *
 * @author heesik.jeon
 *
 */

public class StreamHelper {
	
	private static final Logger logger = Logger.getLogger(StreamHelper.class);

	/**
	 * @param endpoint
	 * @param raw
	 * @param timeout
	 * @return
	 * @throws IOException
	 */
	public static String open(String endpoint, String raw, int timeout) throws IOException {
		return open(HttpMethod.POST, endpoint, raw, timeout);
	}
	
	/**
	 * @param method
	 * @param endpoint
	 * @param raw
	 * @param timeout
	 * @return
	 * @throws IOException
	 */
	public static String open(String method, String endpoint, String raw, int timeout) throws IOException {
		
		logger.debug("..");
		
		URL url = new URL(endpoint);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Cache-Control", "no-cache");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "text/*,application/*");
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setConnectTimeout(timeout);
		conn.setReadTimeout(timeout);
		
		conn.connect();

		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		
		dos.write(raw.getBytes("utf-8")); 
		dos.flush();
		dos.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
		StringBuffer json = new StringBuffer();
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			json.append(line);
		}


		reader.close();
		conn.disconnect();

		return json.toString();
		
	}

}
