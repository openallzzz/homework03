package org.zzzzzz.exception;

public class BusinessException extends  RuntimeException {

    public BusinessException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }


}