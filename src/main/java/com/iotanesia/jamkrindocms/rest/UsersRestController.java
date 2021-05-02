package com.iotanesia.jamkrindocms.rest;

import com.iotanesia.jamkrindocms.constant.CommonConstant;
import com.iotanesia.jamkrindocms.dao.UsersDao;
import com.iotanesia.jamkrindocms.dto.DetailUserDto;
import com.iotanesia.jamkrindocms.dto.UserChangePasswordDto;
import com.iotanesia.jamkrindocms.dto.UserListDto;
import com.iotanesia.jamkrindocms.dto.UsersDto;
import com.iotanesia.jamkrindocms.model.Users;
import com.iotanesia.jamkrindocms.security.AppUserDetails;
import com.iotanesia.jamkrindocms.security.JwtTokenUtil;
import com.iotanesia.jamkrindocms.security.service.JwtUserDetailsService;
import com.iotanesia.jamkrindocms.service.UsersService;
import com.iotanesia.jamkrindocms.util.ResponeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class UsersRestController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> signin(@RequestBody Map<String, Object> map) throws Exception {
        try {
            authenticate(map.get("username").toString(), map.get("password").toString());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponeUtil.constructRespone("400", null, ex.getMessage()));
        }

        final Optional<Users> optionalUsers = usersDao.findByEmail(map.get("username").toString());
        final Users users = optionalUsers.get();

        if(!users.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponeUtil.constructRespone("400", null,
                    "user tidak aktif"));
        }


        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(map.get("username").toString());
        UsersDto usersDto = usersService.findByEmail(map.get("username").toString());
        Users usr = usersService.findByIdUser(usersDto.getIdUser());
        // usersDto.setRole(usr.getJabatan().getRoles().getName());
        final String token = jwtTokenUtil.generateToken(userDetails, usersDto);
        String tk = token.substring(token.length() - 50, token.length());
        usr.setRememberToken(tk);
        usersService.save(usr);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("usersData", usersDto);
        maps.put("token", token);
        return ResponseEntity.ok(ResponeUtil.constructRespone("200", maps, "Succes"));
    }

    @PostMapping(value = "/listusers" , produces = MediaType.APPLICATION_JSON , consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getListUsers(
            @RequestBody Map request,
            Authentication authentication) {

        String username = (String) request.get("username");
        Boolean status = (Boolean) request.get("status");

        List<Users> usersList = new ArrayList<Users>();
        List<UserListDto> usersListDto = new ArrayList<UserListDto>();
        Integer pageSize = request.get("pageSize").equals(CommonConstant.allContentPageValue)
                ? Integer.MAX_VALUE : (Integer) request.get("pageSize");
        Pageable paging = PageRequest.of((Integer) request.get("page") - 1,  pageSize);

        Page<Users> pageUsers = usersService.findByEmailAndStatus(username, status, paging);

        usersList = pageUsers.getContent();

        if (usersList != null && usersList.size() > 0) {
            for (Users usr : usersList) {
                UserListDto usrList = new UserListDto();
                usrList.setId(usr.getId().toString());
                usrList.setEmail(usr.getEmail());
                usrList.setNik(usr.getNik());
                usrList.setRole(usr.getRoles().getCode());
                usersListDto.add(usrList);
            }
        }
        Map mapLists = new HashMap();
        mapLists.put("list", usersListDto);
        mapLists.put("currentPage", pageUsers.getNumber() + 1);
        mapLists.put("totalItem", pageUsers.getTotalElements());
        mapLists.put("pageSize", 10);
        mapLists.put("totalPages", pageUsers.getTotalPages());

        Map map = new HashMap();
        map.put("data", mapLists);
        map.put("status", "200");
        map.put("message", "Success");

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping(value = "/save" , produces = MediaType.APPLICATION_JSON , consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> saveUser(@RequestBody DetailUserDto request, Authentication authentication){
        final AppUserDetails appUserDetails = (AppUserDetails) authentication.getPrincipal();

        return new ResponseEntity<>(usersService.save(request), HttpStatus.OK);
    }

    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> editUser(@RequestBody DetailUserDto request) {
        return new ResponseEntity<>(usersService.edit(request), HttpStatus.OK);
    }

    @PostMapping(value = "/detail", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> detailUser(@RequestBody Map map) {
        String idUser = (String) map.get("id");
        return new ResponseEntity<>(usersService.detail(idUser), HttpStatus.OK);
    }

    @PostMapping(value = "/cekusername", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> cekusername(@RequestBody Map map) {
        String username = (String) map.get("username");
        UsersDto usersDto = usersService.findByEmail(username);
        if (usersDto != null) {
            return new ResponseEntity<>("User Name sudah ada !!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User Name Bisa Di gunakan !!!", HttpStatus.OK);
    }

    @PostMapping(value = "/inactiveuser", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> inactiveUser(@RequestBody Map map) {
        String idUser = (String) map.get("idUser");
        return new ResponseEntity<>(usersService.inactive(idUser), HttpStatus.OK);
    }

    @PostMapping(value = "/activeuser", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> activeUser(@RequestBody Map map) {
        String idUser = (String) map.get("idUser");
        return new ResponseEntity<>(usersService.active(idUser), HttpStatus.OK);
    }

    @PostMapping(value = "/logininformation", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> logininformation(@RequestBody Map map) {
        String idUser = (String) map.get("idUser");
        return new ResponseEntity<>(usersService.loginInformation(idUser), HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> create(@RequestBody DetailUserDto request, Authentication authentication) {
        final AppUserDetails appUserDetails = (AppUserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(usersService.save(request), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User Tidak Aktif");
        } catch (BadCredentialsException e) {
            throw new Exception("User atau Password Salah");
        }
    }

    /**
     * untuk mengganti password
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> changePassword(@RequestBody UserChangePasswordDto request) {
        // cek password
        if (request.getNewPassword().equals(request.getConfirmPassword())) {
            UsersDto user = usersService.findByEmail(request.getUsername());
            // cek user ada atau tidak
            if (user != null) {
                // cek password lama valid atau tidak
                try {
                    authenticate(request.getUsername(), request.getOldPassword());
                } catch (Exception ex) {
                    // username atau password tidak sama
                    return new ResponseEntity<>(ResponeUtil.constructRespone("400", null, ex.getMessage()),
                            HttpStatus.OK);
                }
                return new ResponseEntity<>(usersService.changePassword(user.getIdUser(), request.getNewPassword()),
                        HttpStatus.OK);

            } else {
                return new ResponseEntity<>(ResponeUtil.constructRespone("400", null, "User Name Tidak Terdaftar"),
                        HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(
                    ResponeUtil.constructRespone("400", null, "Password dan Konfirmasi Password Tidak Sama"),
                    HttpStatus.OK);
        }

    }
}

