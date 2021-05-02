package com.iotanesia.jamkrindocms.util;

public class CommonUtil {

	public static String historyDes(String field , String dataLama, String dataBaru) {
		String des = "Mengganti " + field + " dari " + dataLama + " ke " + dataBaru;
		return des;
	}

	public static String getToken(String authToken) {
		if (authToken.isEmpty()) {
			return null;
		}
		return authToken.substring(authToken.length()-50,authToken.length());
	}
}
