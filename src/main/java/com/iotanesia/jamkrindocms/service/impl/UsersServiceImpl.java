package com.iotanesia.jamkrindocms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotanesia.jamkrindocms.dao.*;
import com.iotanesia.jamkrindocms.dto.AppResponeDto;
import com.iotanesia.jamkrindocms.dto.DetailUserDto;
import com.iotanesia.jamkrindocms.dto.UsersDto;
import com.iotanesia.jamkrindocms.dto.response.users.UserDataResponse;
import com.iotanesia.jamkrindocms.model.*;
import com.iotanesia.jamkrindocms.security.AppUserDetails;
import com.iotanesia.jamkrindocms.service.UsersService;
import com.iotanesia.jamkrindocms.util.ResponeUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private HrisManagementDao hrisManagementDao;
	@Autowired
	private RolesDao rolesDao;
	@Autowired
	private HrisDepartmentDao hrisDepartmentDao;
	@Autowired
	private HrisSubDepartmentDao hrisSubDepartmentDao;
	@Autowired
	private HrisDivisionDao hrisDivisionDao;
	@Autowired
	private HrisPositionDao hrisPositionDao;
	@Autowired
	private HrisSectionDao hrisSectionDao;
	@Autowired
	private HrisSubSectionDao hrisSubSectionDao;
	@Autowired
	private HrisBranchDao hrisBranchDao;
	@Autowired
	private HrisFunctionalDao hrisFunctionalDao;

	@Override
	public Users findByIdUser(Integer id) {
		// TODO Auto-generated method stub
		Optional<Users> usr = usersDao.findById(id);
		if (usr.isPresent()) {
			return usr.get();
		}
		return null;
	}

	@Override
	public UsersDto findByEmail(String email) {

		Optional<Users> user = usersDao.findByEmail(email);
		if (!user.isPresent()) {
			return null;
		}
		UsersDto userDto = new UsersDto();
		userDto.setIdUser(user.get().getId().intValue());
		userDto.setEmail(user.get().getEmail());
		userDto.setPasswordUser(user.get().getPassword());
		userDto.setRememberToken(user.get().getRememberToken());

		return userDto;
	}

	@Override
	public Page<Users> findByEmailAndStatus(String email, Boolean status, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Users> pageUser = null;
		if (email != null && status != null) {
			pageUser = usersDao.findAllByEmailContainingAndStatus(email, status, pageable);
		} else if (email != null && status == null) {
			pageUser = usersDao.findAllByEmailContaining(email, pageable);
		} else if (email == null && status != null) {
			pageUser = usersDao.findAllByStatus(status, pageable);
		} else {
			pageUser = usersDao.findAll(pageable);
		}

		return pageUser;
	}

	@Override
	public AppResponeDto save(DetailUserDto request) {
		// TODO Auto-generated method stub
		// Guard
//		if (!(request.getNik().length() == 18)) {
//			return ResponeUtil.constructRespone("400", null, "Nik tidak valid. Silahkan cek Kembali.");
//		}
		Optional<Users> user = usersDao.findByEmail(request.getEmail());
		if (user.isPresent()) {
			return ResponeUtil.constructRespone("400", null, "Email Telah Terdaftar, gunakan Email yang Lain");
		}
		if (!request.getPassword().equals(request.getPasswordConfirmation())) {
			return ResponeUtil.constructRespone("400", null, "Password dan Konfirmasi Password Tidak Sama");
		}

		HrisManagement hrisManagement = hrisManagementDao.findByCode(request.getManagementCode());
		HrisDepartment hrisDepartment = hrisDepartmentDao.findByCode(request.getDepartementCode());
		HrisSubDepartment hrisSubDepartment = hrisSubDepartmentDao.findByCode(request.getSubDepartementCode());
		HrisDivision hrisDivision = hrisDivisionDao.findByCode(request.getDivisionCode());
		HrisPosition hrisPosition = hrisPositionDao.findByCode(request.getPositionCode());
		HrisSection hrisSection = hrisSectionDao.findByCode(request.getSectionCode());
		HrisSubSection hrisSubSection = hrisSubSectionDao.findByCode(request.getSubSectionCode());
		HrisBranch hrisBranch = hrisBranchDao.findByCode(request.getBranchCode());
		HrisFunctional hrisFunctional = hrisFunctionalDao.findByCode(request.getFunctionalCode());
		Roles roles = rolesDao.findByCode(request.getRoleCode());

		Users usr = new Users();
		usr.setCreationDate(new Date());
		usr.setNik(request.getNik());
		usr.setName(request.getName());
		usr.setEmail(request.getEmail());
		usr.setHrisManagement(hrisManagement);
		usr.setRoles(roles);
		usr.setHrisDepartment(hrisDepartment);
		usr.setHrisSubDepartment(hrisSubDepartment);
		usr.setHrisDivision(hrisDivision);
		usr.setHrisPosition(hrisPosition);
		usr.setHrisSection(hrisSection);
		usr.setHrisSubSection(hrisSubSection);
		usr.setHrisBranch(hrisBranch);
		usr.setHrisFunctional(hrisFunctional);
		usr.setGradeCode(request.getGradeCode());
		//Bank code

		usr.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
		usr.setStatus(true);
		usr.setDelete(false);
		usersDao.save(usr);
		return ResponeUtil.constructRespone("200", request.getEmail(), null);
	}

	@Override
	public AppResponeDto edit(DetailUserDto request) {
		// TODO Auto-generated method stub
		Optional<Users> optionalUsers = usersDao.findById(Integer.valueOf(request.getId()));
		if (optionalUsers.isPresent()) {
			Users user = optionalUsers.get();

			if (null != request.getNik() || !request.getNik().equals("")) {
				user.setNik(request.getNik());
			}
			if (!request.getName().equals(user.getName())) {
				user.setName(request.getName());
			}
			if (null != request.getManagementCode() || !request.getManagementCode().equals("")) {
				HrisManagement hrisManagement = hrisManagementDao.findByCode(request.getManagementCode());
				if(null != hrisManagement){
					if(hrisManagement != user.getHrisManagement()){
						user.setHrisManagement(hrisManagement);
					}
				}
			}

			if (null != request.getRoleCode() || !request.getRoleCode().equals("")) {
				Roles roles = rolesDao.findByCode(request.getRoleCode());
				if(null != roles){
					if(roles != user.getRoles()){
						user.setRoles(roles);
					}
				}
			}

			if (null != request.getDepartementCode() || !request.getDepartementCode().equals("")) {
				HrisDepartment hrisDepartment = hrisDepartmentDao.findByCode(request.getDepartementCode());
				if(null != hrisDepartment){
					if(hrisDepartment != user.getHrisDepartment()){
						user.setHrisDepartment(hrisDepartment);
					}
				}
			}

			if (null != request.getSubDepartementCode() || !request.getSubDepartementCode().equals("")) {
				HrisSubDepartment hrisSubDepartment = hrisSubDepartmentDao.findByCode(request.getSubDepartementCode());
				if(null != hrisSubDepartment){
					if(hrisSubDepartment != user.getHrisSubDepartment()){
						user.setHrisSubDepartment(hrisSubDepartment);
					}
				}
			}

			if (null != request.getDivisionCode() || !request.getDivisionCode().equals("")) {
				HrisDivision hrisDivision = hrisDivisionDao.findByCode(request.getDivisionCode());
				if(null != hrisDivision){
					if(hrisDivision != user.getHrisDivision()){
						user.setHrisDivision(hrisDivision);
					}
				}
			}

			if (null != request.getPositionCode() || !request.getPositionCode().equals("")) {
				HrisPosition hrisPosition = hrisPositionDao.findByCode(request.getPositionCode());
				if(null != hrisPosition){
					if(hrisPosition != user.getHrisPosition()){
						user.setHrisPosition(hrisPosition);
					}
				}
			}

			if (null != request.getSectionCode() || !request.getSectionCode().equals("")) {
				HrisSection hrisSection = hrisSectionDao.findByCode(request.getSectionCode());
				if(null != hrisSection){
					if(hrisSection != user.getHrisSection()){
						user.setHrisSection(hrisSection);
					}
				}
			}

			if (null != request.getSubSectionCode() || !request.getSubSectionCode().equals("")) {
				HrisSubSection hrisSubSection = hrisSubSectionDao.findByCode(request.getSubSectionCode());
				if(null != hrisSubSection){
					if(hrisSubSection != user.getHrisSubSection()){
						user.setHrisSubSection(hrisSubSection);
					}
				}
			}

			if (null != request.getBranchCode() || !request.getBranchCode().equals("")) {
				HrisBranch hrisBranch = hrisBranchDao.findByCode(request.getBranchCode());
				if(null != hrisBranch){
					if(hrisBranch != user.getHrisBranch()){
						user.setHrisBranch(hrisBranch);
					}
				}
			}

			if (null != request.getFunctionalCode() || !request.getFunctionalCode().equals("")) {
				HrisFunctional hrisFunctional = hrisFunctionalDao.findByCode(request.getFunctionalCode());
				if(null != hrisFunctional){
					if(hrisFunctional != user.getHrisFunctional()){
						user.setHrisFunctional(hrisFunctional);
					}
				}
			}

			// Third Edit page section
			if (!user.getEmail().equals(request.getEmail())) {
				Optional<Users> users = usersDao.findByEmail(request.getEmail());
				if (users.isPresent() && !users.get().getId().equals(Integer.valueOf(request.getId()))) {
					return ResponeUtil.constructRespone("401", null,
							"User Name Telah Terdaftar, gunakan User Name yang Lain");
				}
			}
			// edit pass
			if (request.getPasswordNew() != null) {
				if (!(new BCryptPasswordEncoder().matches(request.getPasswordOld(), user.getPassword()))) {
					return ResponeUtil.constructRespone("401", null, "Password lama salah");
				} else if (!request.getPasswordNew().equals(request.getPasswordConfirmation())) {
					return ResponeUtil.constructRespone("401", null, "Konfirmasi Password baru salah");
				} else {
					user.setPassword(new BCryptPasswordEncoder().encode(request.getPasswordNew()));
				}
			}

			user.setEmail(request.getEmail());
			user.setModificationDate(new Date());
			usersDao.save(user);
			return ResponeUtil.constructRespone("200", request.getEmail(), null);
		}
		return ResponeUtil.constructRespone("500", request.getEmail(), "Error");
	}

	@Override
	public AppResponeDto detail(String idUser) {
		// TODO Auto-generated method stub
		Users usr = findByIdUser(Integer.valueOf(idUser));
		DetailUserDto usrDto = new DetailUserDto();
		if (usr != null) {
			usrDto.setEmail(usr.getEmail());
			usrDto.setId(usr.getId().toString());
			usrDto.setNik(usr.getNik());
			usrDto.setName(usr.getName());
			usrDto.setManagementCode(usr.getHrisManagement().getCode());
			usrDto.setRoleCode(usr.getRoles().getCode());
			usrDto.setDepartementCode(usr.getHrisDepartment().getCode());
			usrDto.setSubDepartementCode(usr.getHrisSubDepartment().getCode());
			usrDto.setBranchCode(usr.getHrisBranch().getCode());
			usrDto.setFunctionalCode(usr.getHrisFunctional().getCode());
			usrDto.setSectionCode(usr.getHrisSection().getCode());
			usrDto.setSubSectionCode(usr.getHrisSubSection().getCode());
			usrDto.setDivisionCode(usr.getHrisDivision().getCode());
			usrDto.setGradeCode(usr.getGradeCode());

			return ResponeUtil.constructRespone("200", usrDto, null);
		}

		return null;
	}

	@Override
	public AppResponeDto inactive(String idUser) {
		// TODO Auto-generated method stub
		Users usr = findByIdUser(Integer.valueOf(idUser));
		usr.setStatus(false);
		usr.setModificationDate(new Date());
		usersDao.save(usr);
		return ResponeUtil.constructRespone("200", null, "Success");
	}

	@Override
	public AppResponeDto active(String idUser) {
		// TODO Auto-generated method stub
		Users usr = findByIdUser(Integer.valueOf(idUser));
		usr.setStatus(true);
		usr.setModificationDate(new Date());
		usersDao.save(usr);
		return ResponeUtil.constructRespone("200", null, "Success");
	}

	@Override
	public AppResponeDto loginInformation(String idUser) {
		Optional<Users> optionalUsers = usersDao.findById(Integer.valueOf(idUser));
		if (!optionalUsers.isPresent()) {
			return ResponeUtil.constructRespone("404", null, "User Not Found");
		}
		Users users = optionalUsers.get();
		UserDataResponse userData = new UserDataResponse();
		userData.setIdUser(users.getId().toString());
		userData.setUserName(users.getEmail());
		userData.setRole(users.getRoles().getCode());
		return ResponeUtil.constructRespone("200", userData, "Success");
	}

	@Override
	public void save(Users user) {
		usersDao.save(user);

	}

	@Override
	public Users findByRememberToken(String apiToken) {
		// TODO Auto-generated method stub
		return usersDao.findByRememberToken(apiToken);
	}

	@Override
	public Users getUserFromAuthentication(Authentication authentication) {
		final AppUserDetails appUserDetails = (AppUserDetails) authentication.getPrincipal();
		final Optional<Users> optionalUsers = usersDao.findByEmail(appUserDetails.getUsername());
		return optionalUsers.get();
	}


	@Override
	public AppResponeDto changePassword(Integer idUser, String newPassword) {
		Optional<Users> optionalUsers = usersDao.findById(Integer.valueOf(idUser));
		if (optionalUsers.isPresent()) {
			Users user = optionalUsers.get();
			user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			usersDao.save(user);

			return ResponeUtil.constructRespone("200", null, "Success");
		} else {
			return ResponeUtil.constructRespone("400", null, "User atau Password Salah");
		}
	}

}
