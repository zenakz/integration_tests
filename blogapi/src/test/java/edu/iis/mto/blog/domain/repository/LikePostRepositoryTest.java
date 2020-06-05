package edu.iis.mto.blog.domain.repository;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.model.LikePost;
import edu.iis.mto.blog.domain.model.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class) @DataJpaTest class LikePostRepositoryTest {
    @Autowired private TestEntityManager entityManager;
    @Autowired private LikePostRepository repository;

    private LikePost likePost;
    private User user;
    private BlogPost blogPost;

    @Before public void setUp() {
        user = new User();
        user.setFirstName("Jan");
        user.setAccountStatus(AccountStatus.NEW);

        blogPost = new BlogPost();
        blogPost.setEntry("testEntry");
        likePost = new LikePost();
        likePost.setUser(user);
        likePost.setPost(blogPost);
        repository.deleteAll();
    }

    
}
