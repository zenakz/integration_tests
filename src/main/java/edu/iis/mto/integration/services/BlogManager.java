package edu.iis.mto.integration.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.iis.mto.integration.api.request.LikePostRequest;
import edu.iis.mto.integration.api.request.PostRequest;
import edu.iis.mto.integration.api.request.UserRequest;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BlogManager implements BlogService {

    @Override
    public Long createUser(UserRequest userRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long createPost(Long userId, PostRequest postRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addLikeToPost(Long postId, LikePostRequest likeRequest) {
        // TODO Auto-generated method stub

    }

}
