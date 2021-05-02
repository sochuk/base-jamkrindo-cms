package com.iotanesia.jamkrindocms.security.service;

import com.iotanesia.jamkrindocms.dto.UsersDto;
import com.iotanesia.jamkrindocms.security.AppUserDetails;
import com.iotanesia.jamkrindocms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UsersService usersService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UsersDto usersDto = usersService.findByEmail(username);
		if(usersDto == null) {
			throw new UsernameNotFoundException("User not found with : " + username);
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		return new AppUserDetails(usersDto, authorities);
	}

}
