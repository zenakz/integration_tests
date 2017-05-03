package edu.iis.mto.integration.services;

import java.util.List;

import edu.iis.mto.integration.dto.UserData;

public interface DataFinder {

    UserData findUser(Long userId);

    List<UserData> findUsers(String searchString);

}
