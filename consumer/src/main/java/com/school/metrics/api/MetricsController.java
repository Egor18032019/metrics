package com.school.metrics.api;

import com.school.metrics.exception.NotFoundException;
import com.school.metrics.mapper.MetricsMapper;
import com.school.metrics.model.MetricModel;
import com.school.metrics.store.MetricsEntity;
import com.school.metrics.store.MetricsRepository;
import com.school.metrics.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = EndPoint.metrics)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name="Metrics Consumer", description="REST API для просмотра метрик.")
public class MetricsController {
    MetricsRepository repository;
    MetricsMapper mapper;

    public MetricsController(MetricsRepository repository, MetricsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Выдача всех метрик из бд.")
    public List<MetricModel> getAllMetrics() {
        List<MetricsEntity> metricsEntities = repository.findAll();
        return mapper.entitiesToDtos(metricsEntities);
    }

    @Operation(
            summary = "Получение конкретной метрики по ее идентификатору.",
            description = "Ждет на вход id(UUID) метрики."
    )
    @GetMapping(path = "/{id}")
    public MetricModel getOneMetricById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        MetricsEntity metricsEntity = repository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException("Metric with uuid = " + uuid
                        + " was not found"));
        return mapper.entityToDto(metricsEntity);
    }
}
