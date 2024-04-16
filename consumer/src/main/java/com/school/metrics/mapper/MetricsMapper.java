package com.school.metrics.mapper;

import com.school.metrics.model.MetricModel;
import com.school.metrics.store.MetricsEntity;

import java.util.List;


public interface MetricsMapper {

    MetricsEntity dtoToEntity(MetricModel model);

    MetricModel entityToDto(MetricsEntity entity);

    List<MetricModel> entitiesToDtos(List<MetricsEntity> entity);
}
