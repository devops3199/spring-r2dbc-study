package dev.reactive.flux.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientException;

@Getter
public class APIException extends WebClientException {

    private final HttpStatus httpStatus;

    public APIException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.httpStatus = errorMessage.getHttpStatus();
    }
}
