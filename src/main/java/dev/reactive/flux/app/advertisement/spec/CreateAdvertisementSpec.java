package dev.reactive.flux.app.advertisement.spec;

import dev.reactive.flux.app.advertisement.dto.CreateAdvertisementDto;
import dev.reactive.flux.app.advertisement.model.AdvertisementStatus;
import dev.reactive.flux.app.advertisement.repository.AdvertisementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class CreateAdvertisementSpec {

    private final AdvertisementRepository advertisementRepository;

    public Mono<Boolean> isSatisfiedBy(CreateAdvertisementDto createAdvertisementDto) {
        return advertisementRepository.findByShopIdAndStatus(createAdvertisementDto.shopId(), AdvertisementStatus.READY)
                .flatMap(advertisement -> Mono.just(false))
                .switchIfEmpty(Mono.just(true));
    }
}
