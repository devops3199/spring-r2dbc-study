package dev.reactive.flux.app.advertisement.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Advertisement(@Id Integer id, String name, String category, String status, LocalDateTime startedAt, LocalDateTime endedAt) {
}
