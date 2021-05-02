package com.iotanesia.jamkrindocms.util;


import com.iotanesia.jamkrindocms.dao.UsersDao;
import com.iotanesia.jamkrindocms.dto.UsersDto;
import com.iotanesia.jamkrindocms.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

	@Autowired
	private UsersDao usersDao;
	
	public static UserDetails getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserDetails))
			return null;

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return userDetails;
	}

	public static UsersDto getUser() {
		UserDetails userDetails = getUserDetails();
		if (userDetails == null)
			return null;

		if (userDetails instanceof AppUserDetails)
			return ((AppUserDetails) userDetails).getUsersDto();
		else {
			UsersDto user = new UsersDto();
			user.setEmail(userDetails.getUsername());

			return user;
		}
	}
}
