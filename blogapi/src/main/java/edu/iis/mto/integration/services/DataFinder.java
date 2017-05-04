package edu.iis.mto.integration.services;

import java.util.List;

import edu.iis.mto.integration.dto.PostData;
import edu.iis.mto.integration.dto.UserData;

public interface DataFinder {

    UserData getUserData(Long userId);

    List<UserData> findUsers(String searchString);

    PostData getPost(Long userId);

    List<PostData> getUserPosts(Long userId);

}
