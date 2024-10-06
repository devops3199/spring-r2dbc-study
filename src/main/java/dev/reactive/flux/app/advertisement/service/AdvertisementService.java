package dev.reactive.flux.app.advertisement.service;

import dev.reactive.flux.app.advertisement.dto.CreateAdvertisementDto;
import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.repository.AdvertisementRepository;
import dev.reactive.flux.app.advertisement.spec.CreateAdvertisementSpec;
import dev.reactive.flux.common.error.APIException;
import dev.reactive.flux.common.error.ErrorMessage;
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
    private final CreateAdvertisementSpec createAdvertisementSpec;

    public Mono<List<Advertisement>> getAdvertisements() {
        return advertisementRepository.findAll().collectList();
    }

    public Mono<Advertisement> getAdvertisementById(final Integer id) {
        return advertisementRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new APIException(ErrorMessage.ADVERTISEMENT_NOT_FOUND)));
    }

    public Mono<Advertisement> createAdvertisement(final CreateAdvertisementDto dto) {
        return createAdvertisementSpec.isSatisfiedBy(dto)
                .flatMap(isSatisfied -> isSatisfied ? advertisementRepository.save(dto.toAdvertisement()) : Mono.empty())
                .switchIfEmpty(Mono.error(() -> new APIException(ErrorMessage.ADVERTISEMENT_ALREADY_ON)));
    }

    public Mono<Advertisement> offAdvertisement(final Integer id) {
        return advertisementRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new APIException(ErrorMessage.ADVERTISEMENT_NOT_FOUND)))
                .flatMap(advertisement -> {
                    var modified = advertisement.off();
                    return advertisementRepository.save(modified);
                });
    }
}
