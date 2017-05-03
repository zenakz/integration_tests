package edu.iis.mto.integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.iis.mto.integration.api.request.PostRequest;
import edu.iis.mto.integration.api.request.UserRequest;
import edu.iis.mto.integration.domain.model.BlogPost;
import edu.iis.mto.integration.domain.model.User;
import edu.iis.mto.integration.domain.repository.BlogPostRepository;
import edu.iis.mto.integration.domain.repository.LikePostRepository;
import edu.iis.mto.integration.domain.repository.UserRepository;
import edu.iis.mto.integration.mapper.DataMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class BlogManager implements BlogService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    LikePostRepository likePostRepository;

    @Autowired
    DataMapper mapper;

    @Override
    public Long createUser(UserRequest userRequest) {
        User user = mapper.mapToEntity(userRequest);
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
    public void addLikeToPost(Long userId, Long postId) {
        likePostRepository.findByUserAndPost(userId, postId);
    }

}
