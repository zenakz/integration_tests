package edu.iis.mto.integration.api.response;

import java.util.List;

import edu.iis.mto.integration.dto.UserData;

public interface ResponseFactory {

    UserResponse userResponse(UserData newUser);

    UserResponse usersResponse(List<UserData> users);

}
