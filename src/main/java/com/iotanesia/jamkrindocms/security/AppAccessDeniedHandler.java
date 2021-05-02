package com.iotanesia.jamkrindocms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotanesia.jamkrindocms.dto.AppResponeDto;
import com.iotanesia.jamkrindocms.util.ResponeUtil;
import com.iotanesia.jamkrindocms.util.StatusUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

//Buat Cek Role Access
@Component
public class AppAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		AppResponeDto appResponeDto = ResponeUtil.constructRespone(StatusUtil.FAILED, null, "Forbidden Access to this url");
		String jsonRespone = objectMapper.writeValueAsString(appResponeDto);
		response.setContentType(MediaType.APPLICATION_JSON);
		response.getOutputStream().print(jsonRespone);
	}

}
