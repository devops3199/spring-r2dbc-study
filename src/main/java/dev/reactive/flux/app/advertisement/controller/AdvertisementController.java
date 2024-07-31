package dev.reactive.flux.app.advertisement.controller;

import dev.reactive.flux.app.advertisement.dto.CreateAdvertisementDto;
import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.service.AdvertisementService;
import dev.reactive.flux.common.dto.APIResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @GetMapping("/advertisements")
    public Mono<APIResponse<List<Advertisement>>> getAdvertisements() {
        return advertisementService.getAdvertisements()
                .map(advertisements -> APIResponse.<List<Advertisement>>builder()
                        .statusCode(200)
                        .message("success")
                        .data(advertisements)
                        .build());
    }

    @GetMapping("/advertisements/{id}")
    public Mono<APIResponse<Advertisement>> getAdvertisement(@PathVariable("id") Integer id) {
        return advertisementService.getAdvertisementById(id)
                .map(advertisement -> APIResponse.<Advertisement>builder()
                        .statusCode(200)
                        .message("success")
                        .data(advertisement)
                        .build());
    }

    @PostMapping("/advertisements")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<APIResponse<Advertisement>> createAdvertisement(@Valid @RequestBody CreateAdvertisementDto createAdvertisementDto) {
        return advertisementService.createAdvertisement(createAdvertisementDto)
                .map(advertisement -> APIResponse.<Advertisement>builder()
                        .statusCode(201)
                        .message("success")
                        .data(advertisement)
                        .build());
    }

    @PatchMapping("/advertisements/{id}/status/off")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Advertisement> offAdvertisement(@PathVariable Integer id) {
        return advertisementService.offAdvertisement(id);
    }
}
