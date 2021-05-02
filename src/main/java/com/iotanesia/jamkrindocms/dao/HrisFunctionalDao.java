package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisBranch;
import com.iotanesia.jamkrindocms.model.HrisFunctional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisFunctionalDao extends CrudRepository<HrisFunctional, Integer> {

    Optional<HrisFunctional> findByName(String name);

    HrisFunctional findByCode(String code);

    List<HrisFunctional> findAll();
}