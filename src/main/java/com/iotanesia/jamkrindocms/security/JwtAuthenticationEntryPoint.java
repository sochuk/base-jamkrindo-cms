package com.iotanesia.jamkrindocms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotanesia.jamkrindocms.dto.AppResponeDto;
import com.iotanesia.jamkrindocms.util.ResponeUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.Serializable;

//Untuk cek jwt expired
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		AppResponeDto appResponeDto = ResponeUtil.constructRespone("401", null, "Not Authenticated");
		String jsonRespone = objectMapper.writeValueAsString(appResponeDto);
		response.setContentType(MediaType.APPLICATION_JSON);
		response.getOutputStream().print(jsonRespone);
	}

		
}
