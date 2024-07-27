package dev.reactive.flux.app.shop.repository;

import dev.reactive.flux.app.shop.model.Shop;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends R2dbcRepository<Shop, Integer> {
}
