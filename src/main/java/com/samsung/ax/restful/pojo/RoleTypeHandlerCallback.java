package com.samsung.ax.restful.pojo;

/**
 *
 * @author heesik.jeon
 *
 */

public class RoleTypeHandlerCallback extends AbstractTypeHandlerCallback {

    /* 
     * @see com.samsung.restful.pojo.AbstractTypeHandlerCallback#valueOf(java.lang.String)
     */
    @Override
    public Object valueOf(String paramString) {

        Role role = Role.valueOf(paramString);
        
        if (role == null) {
            throw new RuntimeException("Unexpected value found: " + paramString);
        }
        
        return role;

    }

}
