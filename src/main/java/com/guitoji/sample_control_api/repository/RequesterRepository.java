package com.guitoji.sample_control_api.repository;

import com.guitoji.sample_control_api.model.Department;
import com.guitoji.sample_control_api.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequesterRepository extends JpaRepository<Requester, UUID> {

    Optional<Requester> findByNameAndEmailAndDepartment(String name, String email, Department department);
}
