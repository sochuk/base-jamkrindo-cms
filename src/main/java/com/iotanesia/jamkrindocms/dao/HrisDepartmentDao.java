package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisDepartment;
import com.iotanesia.jamkrindocms.model.HrisManagement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisDepartmentDao extends CrudRepository<HrisDepartment, Integer> {

    Optional<HrisDepartment> findByName(String name);

    HrisDepartment findByCode(String code);

    List<HrisDepartment> findAll();
}