package com.guitoji.sample_control_api.mapper;

import com.guitoji.sample_control_api.controller.dto.ResultRequesterSearchDTO;
import com.guitoji.sample_control_api.controller.dto.ResultSampleSearchDTO;
import com.guitoji.sample_control_api.controller.dto.SampleDTO;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.service.RequesterService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class SampleMapper {

    @Autowired
    private RequesterMapper requesterMapper;

    @Autowired
    private RequesterService requesterService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registerDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "requester", source = "id_requester", qualifiedByName = "getRequester")
    public abstract Sample toEntity(SampleDTO dto);

    @Mapping(target = "resultRequesterSearchDTO", source = "requester", qualifiedByName = "getRequesterDto")
    public abstract ResultSampleSearchDTO toDTO(Sample sample);

    @Named("getRequester")
    protected Requester getRequester(UUID idRequester) {
        return requesterService.searchReturningRequester(idRequester);
    }

    @Named("getRequesterDto")
    protected ResultRequesterSearchDTO getResultRequesterSearchDTO(Requester requester) {
        return requesterMapper.toDTO(requester);
    }
}
