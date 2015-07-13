package com.samsung.ax.restful.validate.note;

import java.util.List;

import com.samsung.ax.restful.pojo.Note;
import com.samsung.ax.restful.validate.Error;
import com.samsung.ax.restful.validate.Validate;

/**
 *
 * @author heesik.jeon
 *
 */

public abstract class AbstractNoteValidate implements Validate {
    
    /* 
     * @see com.samsung.ax.restful.validate.Validate#validate(java.lang.Object, java.util.List)
     */
    @Override
    public void validate(Object target, List<Error> error) {
        validate((Note) target, error);
    }
    
    public abstract void validate(Note target, List<Error> error);

}
