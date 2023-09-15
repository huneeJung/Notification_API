package kr.co.mz.notification.common.exception.custom;

public class NotExistException extends RuntimeException {
    public NotExistException(String message) {
        super(message);
    }
}
