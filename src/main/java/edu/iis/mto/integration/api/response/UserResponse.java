package edu.iis.mto.integration.api.response;

import edu.iis.mto.integration.dto.UserData;

public class UserResponse {

    private UserData user;

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
}
