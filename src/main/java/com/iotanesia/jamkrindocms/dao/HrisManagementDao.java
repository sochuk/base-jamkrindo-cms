package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisManagement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisManagementDao extends CrudRepository<HrisManagement, Integer> {

    Optional<HrisManagement> findByName(String name);

    HrisManagement findByCode(String departmentCode);

    List<HrisManagement> findAll();
}