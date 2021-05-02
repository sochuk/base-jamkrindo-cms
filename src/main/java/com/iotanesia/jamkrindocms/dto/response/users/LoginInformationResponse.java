package com.iotanesia.jamkrindocms.dto.response.users;

public class LoginInformationResponse {
    private UserDataResponse userData;

    public UserDataResponse getUserData() {
        return userData;
    }

    public void setUserData(UserDataResponse userData) {
        this.userData = userData;
    }
}
