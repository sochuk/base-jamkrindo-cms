package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RolesDao extends CrudRepository<Roles, Integer>{

	Optional<Roles> findByDescription(String description);

	Roles findByCode(String code);

	List<Roles> findAll();
}
