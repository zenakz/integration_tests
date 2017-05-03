package edu.iis.mto.integration.services;

import edu.iis.mto.integration.api.request.PostRequest;
import edu.iis.mto.integration.api.request.UserRequest;

public interface BlogService {

    Long createUser(UserRequest userRequest);

    Long createPost(Long userId, PostRequest postRequest);

}
