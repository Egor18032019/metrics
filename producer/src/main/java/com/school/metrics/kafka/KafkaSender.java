package com.school.metrics.kafka;

import com.school.metrics.model.MetricModel;
import com.school.metrics.utils.EndPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, MetricModel> kafkaTemplate;


    public void sendMetrics(MetricModel model) {
        kafkaTemplate.send(EndPoint.topic, model.getId().toString(), model);
//        kafkaTemplate.send(EndPoint.topic, model);
    }
}