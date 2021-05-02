package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisDepartment;
import com.iotanesia.jamkrindocms.model.HrisSubDepartment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisSubDepartmentDao extends CrudRepository<HrisSubDepartment, Integer> {

    Optional<HrisSubDepartment> findByName(String name);

    HrisSubDepartment findByCode(String code);

    List<HrisSubDepartment> findAll();
}