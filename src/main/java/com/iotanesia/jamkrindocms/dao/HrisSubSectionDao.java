package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisDepartment;
import com.iotanesia.jamkrindocms.model.HrisSubSection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisSubSectionDao extends CrudRepository<HrisSubSection, Integer> {

    Optional<HrisSubSection> findByName(String name);

    HrisSubSection findByCode(String code);

    List<HrisSubSection> findAll();
}