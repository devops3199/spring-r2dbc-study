package dev.reactive.flux.app.advertisement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Advertisement {
    @Id
    private Integer id;
    private Integer shopId;
    private String name;
    private AdvertisementCategory category;
    private AdvertisementStatus status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
