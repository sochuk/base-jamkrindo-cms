package com.iotanesia.jamkrindocms.service.general;


import com.iotanesia.jamkrindocms.dto.AppResponeDto;

public interface MenuService {

    public AppResponeDto getMenuBySessionUser(String token);
}
