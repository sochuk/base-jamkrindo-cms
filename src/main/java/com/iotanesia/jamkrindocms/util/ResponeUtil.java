package com.iotanesia.jamkrindocms.util;

import com.iotanesia.jamkrindocms.dto.AppResponeDto;

public class ResponeUtil {

	public static AppResponeDto constructRespone(String status, Object data, String msg) {
		AppResponeDto appResponeDto = new AppResponeDto();
		appResponeDto.setStatus(status);
		appResponeDto.setData(data);
		appResponeDto.setMessage(msg);
		return appResponeDto;
	}
}
