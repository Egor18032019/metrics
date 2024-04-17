package com.school.metrics.kafka;

import com.school.metrics.mapper.MetricsMapper;
import com.school.metrics.model.MetricModel;
import com.school.metrics.store.MetricsEntity;
import com.school.metrics.store.MetricsRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MetricsListener {
    MetricsRepository repository;
    MetricsMapper metricsMapper;

    @KafkaListener(id = "1", topics = "metrics-topic")
    public void listen(MetricModel metricsDto) {
        log.info("Received metrics: {}", metricsDto);
        MetricsEntity entity = metricsMapper.dtoToEntity(metricsDto);
        repository.save(entity);
        log.info("Metrics сохранили в бд");
    }
}
