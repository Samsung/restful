package com.samsung.ax.restful.validate.note;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.samsung.ax.restful.common.Constant;
import com.samsung.ax.restful.pojo.Note;
import com.samsung.ax.restful.validate.Error;

/**
 *
 * @author heesik.jeon
 *
 */

public class MissingContent extends AbstractNoteValidate {

    /* 
     * @see com.samsung.ax.restful.validate.note.AbstractNoteValidate#validate(com.samsung.ax.restful.pojo.Note, java.util.List)
     */
    @Override
    public void validate(Note target, List<Error> error) {

        if (StringUtils.isBlank(target.getContent())) {
            error.add(new Error(Constant.ERR_MANDATORY_MISSING, new Object[] {Constant.NOTE_CONTENT}));
        }
        
    }

}
