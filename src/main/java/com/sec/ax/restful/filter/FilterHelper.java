package com.sec.ax.restful.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.jersey.core.util.ReaderWriter;
import com.sun.jersey.spi.container.ContainerRequest;

/**
 *
 * @author heesik.jeon
 *
 */

public class FilterHelper {

    private FilterHelper() {}

    /**
     * @param type
     * @param request
     * @return
     * @throws IOException
     */
    public static <T> T getEntity(Class<T> type, ContainerRequest request) throws IOException {
    
        byte[] requestEntity = getEntityBytes(request);
        T entity = request.<T>getEntity(type);
        request.setEntityInputStream(new ByteArrayInputStream(requestEntity));
    
        return entity;
    
    }

    /**
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getEntityBytes(ContainerRequest request) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = request.getEntityInputStream();
        ReaderWriter.writeTo(in, out);
        
        byte[] requestEntity = out.toByteArray();
        request.setEntityInputStream(new ByteArrayInputStream(requestEntity.clone()));

        return requestEntity;

    }

}
