package com.guitoji.sample_control_api.controller.mapper;

import com.guitoji.sample_control_api.controller.dto.RequesterDTO;
import com.guitoji.sample_control_api.controller.dto.ResultRequesterSearchDTO;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.model.Sample;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequesterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "samples", ignore = true)
    Requester toEntity(RequesterDTO dto);

    /*
     * Implement list of samples after!!
     */
    ResultRequesterSearchDTO toDTO(Requester requester);
}
