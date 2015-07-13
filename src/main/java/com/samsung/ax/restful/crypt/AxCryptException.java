package com.samsung.ax.restful.crypt;

/**
 *
 * @author heesik.jeon
 *
 */

public class AxCryptException extends Exception {

    private static final long serialVersionUID = -3861017507298019589L;
    
    private Exception e;

    public AxCryptException() {
    }

    public AxCryptException(String msg) {
        super(msg);
    }

    public AxCryptException(String msg, Exception exception) {
        super(msg);
        e = exception;
    }

    public String toString() {
        return (e == null ? "" : e.toString() + "\n") + super.toString();
    }

}
