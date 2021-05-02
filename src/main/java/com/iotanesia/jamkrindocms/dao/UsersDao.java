package com.iotanesia.jamkrindocms.dao;

import com.iotanesia.jamkrindocms.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsersDao extends PagingAndSortingRepository<Users, Integer>{

	Optional<Users> findById(Integer id);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByName(String name);
    
    Users findByRememberToken(String rememberToken);
	
	Page<Users> findAllByEmailContaining(String email, Pageable pageable);

    Page<Users> findAllByStatus(Boolean status, Pageable pageable);

    Page<Users> findAllByEmailContainingAndStatus(String email, Boolean status, Pageable pageable);

}
