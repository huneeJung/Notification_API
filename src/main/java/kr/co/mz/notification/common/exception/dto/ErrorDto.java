package kr.co.mz.notification.common.exception.dto;

public record ErrorDto(
        String message,
        Integer errorCode
) {
    public ErrorDto {

    }
}