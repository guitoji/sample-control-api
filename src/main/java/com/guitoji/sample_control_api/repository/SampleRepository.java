package com.guitoji.sample_control_api.repository;

import com.guitoji.sample_control_api.model.Requester;
import com.guitoji.sample_control_api.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SampleRepository extends JpaRepository<Sample, UUID> {
    boolean existsByRequester(Requester requester);

    List<Sample> findByRequester(Requester requester);
}