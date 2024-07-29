package dev.reactive.flux.app.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Shop {

    @Id
    private Integer id;
    private String name;
    private ShopStatus status;
    private LocalDateTime createdAt;
}
