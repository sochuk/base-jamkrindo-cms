package com.iotanesia.jamkrindocms.security;

import com.iotanesia.jamkrindocms.dto.UsersDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AppUserDetails implements UserDetails {

	private UsersDto usersDto;
	private Collection<GrantedAuthority> authorities;
	
	public AppUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public AppUserDetails(UsersDto usersDto, Collection<GrantedAuthority> authorities) {
		super();
		this.usersDto = usersDto;
		this.authorities = authorities;
	}
	
	
	public UsersDto getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(UsersDto usersDto) {
		this.usersDto = usersDto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usersDto.getPasswordUser();
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usersDto.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
