package com.school.metrics.service;

import com.school.metrics.kafka.KafkaSender;
import com.school.metrics.model.MetricModel;
import com.school.metrics.utils.Metrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.MeterNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class MetricsService implements MetricsServiceCommon {
    MeterRegistry meterRegistry;
    KafkaSender sender;

    @Override
    public void sendMetrics() {
        for (String metric : Metrics.all) {
            UUID id = UUID.randomUUID();
            LocalDateTime timestamp = LocalDateTime.now();
            String name = metric;
            double value;
            try {
                value = meterRegistry.get(name).gauge().value();
            } catch (MeterNotFoundException exception) {
                log.info("Sending metrics for " + name + " " + exception.getMessage());
                continue;
            }
            MetricModel metricModel = new MetricModel(id, timestamp, name, value);
            sender.sendMetrics(metricModel);
            System.out.println(metricModel.toString());
        }
    }
}


