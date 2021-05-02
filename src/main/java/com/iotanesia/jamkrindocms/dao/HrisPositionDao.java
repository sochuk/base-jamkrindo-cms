package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisDepartment;
import com.iotanesia.jamkrindocms.model.HrisPosition;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisPositionDao extends CrudRepository<HrisPosition, Integer> {

    Optional<HrisPosition> findByName(String name);

    HrisPosition findByCode(String code);

    List<HrisPosition> findAll();
}