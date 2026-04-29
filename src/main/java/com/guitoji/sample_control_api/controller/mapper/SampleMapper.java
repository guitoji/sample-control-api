package com.guitoji.sample_control_api.controller.mapper;

import com.guitoji.sample_control_api.controller.dto.SampleDTO;
import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.model.Sample;
import com.guitoji.sample_control_api.repository.RequesterRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class SampleMapper {

    @Autowired
    private RequesterRepository requesterRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requester", source = "id_requester")
    public abstract Sample toEntity(SampleDTO dto);

    public Requester toRequester(UUID idRequester) {
        return requesterRepository.findById(idRequester).orElseThrow(() -> new RuntimeException("Autor não encontrado!"));
    }
}
