package dev.reactive.flux.app.advertisement.controller;

import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.service.AdvertisementService;
import dev.reactive.flux.common.dto.APIResponse;
import dev.reactive.flux.common.error.APIException;
import dev.reactive.flux.common.error.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @GetMapping("/advertisement/{id}")
    public Mono<APIResponse<Advertisement>> getAdvertisement(@PathVariable("id") Integer id) {
        return advertisementService.getAdvertisementById(id)
                .switchIfEmpty(Mono.error(() -> new APIException(ErrorMessage.ADVERTISEMENT_NOT_FOUND)))
                .map(advertisement -> APIResponse.<Advertisement>builder()
                        .statusCode(200)
                        .message("success")
                        .data(advertisement)
                        .build());
    }
}
