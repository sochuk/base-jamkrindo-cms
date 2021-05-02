package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisDepartment;
import com.iotanesia.jamkrindocms.model.HrisSection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisSectionDao extends CrudRepository<HrisSection, Integer> {

    Optional<HrisSection> findByName(String name);

    HrisSection findByCode(String code);

    List<HrisSection> findAll();
}