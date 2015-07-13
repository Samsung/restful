package com.samsung.ax.restful.validate.note;

import java.util.List;

import com.samsung.ax.restful.common.Constant;
import com.samsung.ax.restful.pojo.Note;
import com.samsung.ax.restful.validate.Error;

/**
 *
 * @author heesik.jeon
 *
 */

public class MissingIdx extends AbstractNoteValidate {

    /* 
     * @see com.samsung.ax.restful.validate.note.AbstractNoteValidate#validate(com.samsung.ax.restful.pojo.Note, java.util.List)
     */
    @Override
    public void validate(Note target, List<Error> error) {

        if (target.getIdx() <= 0) {
            error.add(new Error(Constant.ERR_MANDATORY_MISSING, new Object[] {Constant.NOTE_IDX}));
        }
        
    }

}
