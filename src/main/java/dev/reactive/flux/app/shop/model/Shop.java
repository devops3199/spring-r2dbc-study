package dev.reactive.flux.app.shop.model;

import dev.reactive.flux.app.shop.dto.ModifyShopDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
public class Shop {

    @Id
    private Integer id;
    private String name;
    private ShopStatus status;
    private LocalDateTime createdAt;

    public Shop patch(ModifyShopDto dto) {
        if (Objects.nonNull(dto.name())) {
            this.name = dto.name();
        }

        if (Objects.nonNull(dto.status())) {
            this.status = dto.status();
        }

        return this;
    }
}
