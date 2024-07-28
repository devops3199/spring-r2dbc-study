package dev.reactive.flux.app.advertisement.controller;

import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.service.AdvertisementService;
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
    public Mono<Advertisement> getAdvertisement(@PathVariable("id") Integer id) {
        return advertisementService.getAdvertisementById(id);
    }
}
