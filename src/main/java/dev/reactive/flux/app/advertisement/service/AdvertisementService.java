package dev.reactive.flux.app.advertisement.service;

import dev.reactive.flux.app.advertisement.dto.CreateAdvertisementDto;
import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.repository.AdvertisementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    public Mono<List<Advertisement>> getAdvertisements() {
        return advertisementRepository.findAll().collectList();
    }

    public Mono<Advertisement> getAdvertisementById(final Integer id) {
        return advertisementRepository.findById(id);
    }

    public Mono<Advertisement> createAdvertisement(final CreateAdvertisementDto dto) {
        return advertisementRepository.save(dto.toAdvertisement());
    }
}
