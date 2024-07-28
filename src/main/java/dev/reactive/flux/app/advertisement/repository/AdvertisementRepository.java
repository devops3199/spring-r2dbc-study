package dev.reactive.flux.app.advertisement.repository;

import dev.reactive.flux.app.advertisement.model.Advertisement;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AdvertisementRepository extends R2dbcRepository<Advertisement, Integer> {
}
