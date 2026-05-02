package com.guitoji.sample_control_api.mapper;

import com.guitoji.sample_control_api.controller.dto.RequesterDTO;
import com.guitoji.sample_control_api.controller.dto.ResultRequesterSearchDTO;
import com.guitoji.sample_control_api.model.Requester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class RequesterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "samples", ignore = true)
    public abstract Requester toEntity(RequesterDTO dto);

    public abstract ResultRequesterSearchDTO toDTO(Requester requester);
}
