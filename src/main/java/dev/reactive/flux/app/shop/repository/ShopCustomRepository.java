package dev.reactive.flux.app.shop.repository;

import dev.reactive.flux.app.advertisement.model.AdvertisementCategory;
import dev.reactive.flux.app.advertisement.model.AdvertisementStatus;
import dev.reactive.flux.app.shop.dto.ShopWithAdvertisementsDto;
import dev.reactive.flux.app.shop.model.ShopStatus;
import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.ZonedDateTime;

@Repository
@AllArgsConstructor
public class ShopCustomRepository {

    private final DatabaseClient databaseClient;
    private final String SHOP_ID = "shopId";

    public Flux<ShopWithAdvertisementsDto> findWithAdvertisements(final Integer shopId) {
        final String joinQuery = """
            SELECT
                s.id as shopId, 
                s.name as shopName,
                s.status as shopStatus,
                s.created_at as shopCreatedAt,
                a.id as advertisementId, 
                a.name as advertisementName,
                a.category as advertisementCategory,
                a.status as advertisementStatus,
                a.started_at as advertisementStartedAt,
                a.ended_at as advertisementEndedAt
            FROM shop s 
            LEFT JOIN advertisement a ON s.id = a.shop_id
            WHERE s.id = :shopId 
                            """;

        return databaseClient.sql(joinQuery)
                .bind(SHOP_ID, shopId)
                .fetch().all()
                .map(row -> ShopWithAdvertisementsDto.builder()
                        .shopId((Integer) row.get("shopId"))
                        .shopName((String) row.get("shopName"))
                        .shopStatus(ShopStatus.valueOf((String) row.get("shopStatus")))
                        .shopCreatedAt(((ZonedDateTime) row.get("shopCreatedAt")).toLocalDateTime())
                        .advertisementId((Integer) row.get("advertisementId"))
                        .advertisementName((String) row.get("advertisementName"))
                        .advertisementCategory(AdvertisementCategory.valueOf((String) row.get("advertisementCategory")))
                        .advertisementStatus(AdvertisementStatus.valueOf((String) row.get("advertisementStatus")))
                        .advertisementStartedAt(((ZonedDateTime) row.get("advertisementStartedAt")).toLocalDateTime())
                        .advertisementEndedAt(((ZonedDateTime) row.get("advertisementEndedAt")).toLocalDateTime())
                        .build());
    }
}
