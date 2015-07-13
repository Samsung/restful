package com.samsung.ax.restful.validate;

import java.util.List;

/**
 *
 * @author heesik.jeon
 *
 */

public interface Validate {
    
    /**
     * @param target
     * @param errors
     */
    public void validate(Object target, List<Error> error);

}
