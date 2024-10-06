package dev.reactive.flux.app.advertisement.repository;

import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.model.AdvertisementStatus;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AdvertisementRepository extends R2dbcRepository<Advertisement, Integer> {
    Mono<Advertisement> findByShopIdAndStatus(Integer shopId, AdvertisementStatus status);
}
