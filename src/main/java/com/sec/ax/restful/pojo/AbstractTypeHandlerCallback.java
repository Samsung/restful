package com.sec.ax.restful.pojo;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 *
 * @author heesik.jeon
 *
 */

public abstract class AbstractTypeHandlerCallback implements TypeHandlerCallback {

    /* 
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter, java.lang.Object)
     */
    @Override
    public void setParameter(ParameterSetter paramParameterSetter, Object paramObject) throws SQLException {
        paramParameterSetter.setString(paramObject.toString());
    }

    /* 
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#getResult(com.ibatis.sqlmap.client.extensions.ResultGetter)
     */
    @Override
    public Object getResult(ResultGetter paramResultGetter) throws SQLException {
        return this.valueOf(paramResultGetter.getString());
    }

    /* 
     * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
     */
    @Override
    public abstract Object valueOf(String paramString);

}
