package com.iotanesia.jamkrindocms.service;

import com.iotanesia.jamkrindocms.dto.AppResponeDto;
import com.iotanesia.jamkrindocms.dto.DetailUserDto;
import com.iotanesia.jamkrindocms.dto.UsersDto;
import com.iotanesia.jamkrindocms.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UsersService {

	Users findByIdUser(Integer idUser);

	UsersDto findByEmail(String email);

	Page<Users> findByEmailAndStatus(String email, Boolean status, Pageable pageable);

	AppResponeDto save(DetailUserDto request);

	void save(Users user);

	AppResponeDto edit(DetailUserDto request);

	AppResponeDto detail(String idUser);

	AppResponeDto inactive(String idUser);

	AppResponeDto active(String idUser);

	AppResponeDto loginInformation(String idUser);

	Users findByRememberToken(String rememberToken);

	AppResponeDto changePassword(Integer idUser, String newPassword);
	public Users getUserFromAuthentication(Authentication authentication);
}
