package dev.reactive.flux.common.error;

import dev.reactive.flux.common.dto.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ErrorAdviceController {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<APIResponse<Void>> handleBindException(WebExchangeBindException e) {
        var firstFieldError = e.getFieldErrors().stream().findFirst()
                .orElse(new FieldError("fallback", "myField", "잘못된 요청입니다"));

        log.error("WebExchangeBindException: {}", firstFieldError.getDefaultMessage());

        return Mono.just(new APIResponse<>(400, firstFieldError.getDefaultMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<APIResponse<Void>> handleException(Exception e) {
        log.error("Runtime Exception: {}", e.getMessage());

        return Mono.just(new APIResponse<>(500, e.getMessage()));
    }
}
