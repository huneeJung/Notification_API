package kr.co.mz.notification.common.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NO_TARGET("해당되는 대상이 없습니다."),
    DUPLICATED_ID("Id가 중복되어 있습니다."),

    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),

    USERNAME_ALREADY_EXISTS("이미 존재하는 회원입니다."),
    USER_NOT_FOUND("해당되는 유저를 찾을 수 없습니다."),

    AWS_S3_UPLOAD_ERROR("AWS S3 업로드에 실패했습니다."),

    NOT_INCLUDE_BOARD("해당되는 게시글이 없습니다."),

    CATEGORY_ID_LIST_SIZE_NOT_MATCH("카테고리 리스트의 크기가 맞지 않습니다."),

    ROLE_NOT_FOUND("해당되는 권한을 찾을 수 없습니다."),

    OPTION_SIZE_OVER_ERROR_CODE("옵션의 개수가 10개를 넘어섰습니다."),
    ALREADY_EXIST_CATEGORY("이미 Board에 등록된 카테고리입니다."),
    IO_EXCEPTION_CODE("IO Exception이 발생했습니다."),
    EMPTY_ORDERS_INFO_CODE("주문 정보가 없습니다."),
    TOO_MANY_ORDERS_INFO_CODE("주문 정보가 너무 많습니다.");

    private final String description;

}
