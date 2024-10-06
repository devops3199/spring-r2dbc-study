package dev.reactive.flux.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    ADVERTISEMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 광고가 없습니다."),
    ADVERTISEMENT_ALREADY_ON(HttpStatus.BAD_REQUEST, "이미 운영중인 광고가 있습니다."),
    SHOP_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 샵이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
