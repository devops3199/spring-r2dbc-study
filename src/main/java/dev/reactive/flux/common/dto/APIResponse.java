package dev.reactive.flux.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class APIResponse<T> {

    private Integer statusCode;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public APIResponse(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public APIResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
