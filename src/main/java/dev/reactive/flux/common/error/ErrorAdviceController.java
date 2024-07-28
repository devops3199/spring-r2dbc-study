package dev.reactive.flux.common.error;

import dev.reactive.flux.common.dto.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ErrorAdviceController {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<APIResponse<Void>> handleBindException(WebExchangeBindException e) {
        var firstFieldError = e.getFieldErrors().stream().findFirst()
                .orElse(new FieldError("fallback", "myField", "잘못된 요청입니다"));

        log.error("WebExchangeBindException: {}", firstFieldError.getDefaultMessage());

        return Mono.just(new APIResponse<>(400, firstFieldError.getDefaultMessage()));
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<Mono<APIResponse<Void>>> handleException(APIException e) {
        log.error("APIException: {}", e.getMessage());

        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Mono.just(new APIResponse<>(e.getHttpStatus().value(), e.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<APIResponse<Void>> handleException(Exception e) {
        log.error("RuntimeException: {}", e.getMessage());

        return Mono.just(new APIResponse<>(500, e.getMessage()));
    }
}
