package edu.iis.mto.integration.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.iis.mto.integration.api.request.PostRequest;
import edu.iis.mto.integration.api.request.UserRequest;
import edu.iis.mto.integration.domain.model.AccountStatus;
import edu.iis.mto.integration.domain.model.BlogPost;
import edu.iis.mto.integration.domain.model.LikePost;
import edu.iis.mto.integration.domain.model.User;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BlogManager extends DomainService implements BlogService {

    @Override
    public Long createUser(UserRequest userRequest) {
        User user = mapper.mapToEntity(userRequest);
        user.setAccountStatus(AccountStatus.NEW);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long createPost(Long userId, PostRequest postRequest) {
        User user = userRepository.findOne(userId);
        BlogPost post = mapper.mapToEntity(postRequest);
        post.setUser(user);
        blogPostRepository.save(post);
        return post.getId();
    }

    @Override
    public boolean addLikeToPost(Long userId, Long postId) {
        User user = userRepository.findOne(userId);
        BlogPost post = blogPostRepository.findOne(postId);
        Optional<LikePost> existingLikeForPost = likePostRepository.findByUserAndPost(user, post);
        if (existingLikeForPost.isPresent()) {
            return false;
        }
        LikePost likePost = new LikePost();
        likePost.setUser(user);
        likePost.setPost(post);
        likePostRepository.save(likePost);
        return true;
    }

}
