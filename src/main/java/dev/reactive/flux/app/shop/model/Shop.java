package dev.reactive.flux.app.shop.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Shop(@Id Integer id, String name, String category, String status, LocalDateTime createdAt) {
}
