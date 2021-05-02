package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.HrisBranch;
import com.iotanesia.jamkrindocms.model.HrisDepartment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HrisBranchDao extends CrudRepository<HrisBranch, Integer> {

    Optional<HrisBranch> findByName(String name);

    HrisBranch findByCode(String code);

    List<HrisBranch> findAll();
}