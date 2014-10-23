package com.sec.ax.restful.validate.note;

import java.util.List;

import com.sec.ax.restful.common.Constant;
import com.sec.ax.restful.pojo.Note;
import com.sec.ax.restful.validate.Error;

/**
 *
 * @author heesik.jeon
 *
 */

public class ValidateDELETE extends AbstractNoteValidate {

	/* 
	 * @see com.sec.ax.restful.validate.note.AbstractNoteValidate#validate(com.sec.ax.restful.pojo.Note, java.util.List)
	 */
	@Override
	public void validate(Note target, List<Error> error) {

		if (target.getIdx() <= 0) {
			error.add(new Error(Constant.ERR_MANDATORY_MISSING, null));
		}
		
	}

}
