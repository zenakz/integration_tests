package edu.iis.mto.blog.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.iis.mto.blog.api.request.PostRequest;
import edu.iis.mto.blog.domain.errors.DomainError;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.repository.BlogPostRepository;
import edu.iis.mto.blog.domain.repository.LikePostRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.iis.mto.blog.api.request.UserRequest;
import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.User;
import edu.iis.mto.blog.domain.repository.UserRepository;
import edu.iis.mto.blog.mapper.BlogDataMapper;
import edu.iis.mto.blog.services.BlogService;

import java.util.Optional;

@RunWith(SpringRunner.class) @SpringBootTest public class BlogManagerTest {

    @MockBean private UserRepository userRepository;

    @MockBean private BlogPostRepository blogPostRepository;

    @MockBean private LikePostRepository likePostRepository;

    @Autowired private BlogDataMapper dataMapper;

    @Autowired private BlogService blogService;

    @Captor private ArgumentCaptor<User> userParam;

    @Captor private ArgumentCaptor<BlogPost> blogPostParam;

    private static User user;

    @Before public void setUp() {
        user = new User();
        Long userId = 22L;
        user.setId(userId);
        user.setAccountStatus(AccountStatus.NEW);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

    @Test public void creatingNewUserShouldSetAccountStatusToNEW() {
        blogService.createUser(new UserRequest("John", "Steward", "john@domain.com"));
        verify(userRepository).save(userParam.capture());
        User user = userParam.getValue();
        assertThat(user.getAccountStatus(), Matchers.equalTo(AccountStatus.NEW));
    }

    @Test public void userWithNewAccountShouldNotBeAbleToLikePost() {
        User postCreator = prepUser();
        postCreator.setAccountStatus(AccountStatus.CONFIRMED);
        blogService.createPost(postCreator.getId(), new PostRequest());

        verify(blogPostRepository).save(blogPostParam.capture());
        BlogPost post = blogPostParam.getValue();
        when(blogPostRepository.findById(post.getId())).thenReturn(Optional.of(post));
        assertThrows(DomainError.class, () -> {
            blogService.addLikeToPost(user.getId(), post.getId());
        });
    }

    private User prepUser() {
        blogService.createUser(new UserRequest("John", "Steward", "john@domain.com"));
        verify(userRepository).save(userParam.capture());
        User user = userParam.getValue();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        return user;
    }

}
