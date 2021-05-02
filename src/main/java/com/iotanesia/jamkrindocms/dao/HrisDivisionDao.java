package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisDepartment;
import com.iotanesia.jamkrindocms.model.HrisDivision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisDivisionDao extends CrudRepository<HrisDivision, Integer> {

    Optional<HrisDivision> findByName(String name);

    HrisDivision findByCode(String code);

    List<HrisDivision> findAll();
}