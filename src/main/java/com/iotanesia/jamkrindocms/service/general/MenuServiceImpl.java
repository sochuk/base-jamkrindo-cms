package com.iotanesia.jamkrindocms.service.general;

import com.iotanesia.jamkrindocms.constant.RolesTypeConstant;
import com.iotanesia.jamkrindocms.dao.RolesDao;
import com.iotanesia.jamkrindocms.dao.UsersDao;
import com.iotanesia.jamkrindocms.dto.AppResponeDto;
import com.iotanesia.jamkrindocms.dto.response.MainMenuResponse;
import com.iotanesia.jamkrindocms.dto.response.PermissionDto;
import com.iotanesia.jamkrindocms.model.Roles;
import com.iotanesia.jamkrindocms.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private RolesDao rolesDao;

    @Override
    public AppResponeDto getMenuBySessionUser(String token) {
        AppResponeDto response = new AppResponeDto();
        Users user = usersDao.findByRememberToken(token);
        if (null == user) {
            response.setMessage("User does not exists");
            response.setStatus("500");
            return response;
        }

        Roles role = user.getRoles();
        String roleCode = role.getCode();
        String roleName = role.getDescription();

        if (null == role) {
            response.setMessage("User role does not exists " + roleCode);
            response.setStatus("500");
            return response;
        }

        if (roleName.isEmpty()) {
            response.setMessage("User role does not exists " + roleName);
            response.setStatus("500");
            return response;
        }

        List<MainMenuResponse> menuData = new ArrayList<>();

        if (RolesTypeConstant.RolesType.SUPER_ADMIN.value().equalsIgnoreCase(roleCode)) {
            menuData = getMenuSuperAdmin();
        }


        response.setData(menuData);
        response.setMessage("Success");
        response.setStatus("200");
        return response;
    }

    private List<MainMenuResponse> getMenuSuperAdmin() {
        List<MainMenuResponse> menuData = new ArrayList<>();
        menuData.add(getMenuPengaturanSuperAdmin());
        return menuData;
    }

    private MainMenuResponse getMenuPengaturanSuperAdmin() {
        MainMenuResponse menuPengaturan = createMenu(
                "Pengaturan", "settings", "settings",
                "", "CMS", true, true, true, true);

        MainMenuResponse[] pengaturanChild = new MainMenuResponse[1];

        MainMenuResponse menuKelolaUser = createMenu(
                "Kelola User", "usermanagement", "usermanagement",
                "", "CMS", true, true, true,true);
        pengaturanChild[0] = menuKelolaUser;

        menuPengaturan.setChild(pengaturanChild);
        return menuPengaturan;
    }

    private MainMenuResponse createMenu(String name, String path, String url, String icon, String category,
                                        boolean create, boolean read, boolean update,
                                        boolean delete) {
        MainMenuResponse menu = new MainMenuResponse();
        menu.setName(name);
        menu.setPath(path);
        menu.setUrl(url);
        menu.setIcon(icon);
        menu.setCategory(category);
        PermissionDto menuPermission = new PermissionDto();
        menuPermission.setCreate(create);
        menuPermission.setRead(read);
        menuPermission.setUpdate(update);
        menuPermission.setDelete(delete);
        menu.setPermission(menuPermission);
        return menu;
    }

}
