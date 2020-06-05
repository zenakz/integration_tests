package edu.iis.mto.blog.domain.repository;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.model.LikePost;
import edu.iis.mto.blog.domain.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class) @DataJpaTest public class LikePostRepositoryTest {

    @Autowired private TestEntityManager entityManager;
    @Autowired private LikePostRepository repository;

    private LikePost likePost;
    private User user;
    private BlogPost blogPost;

    @Before public void setUp() {
        repository.deleteAll();
        user = new User();
        user.setFirstName("Jan");
        user.setAccountStatus(AccountStatus.NEW);
        user.setEmail("email");
        blogPost = createPost();

        likePost = new LikePost();
        likePost.setUser(user);
        likePost.setPost(blogPost);
    }

    @Test public void shouldFindNoLikePostsIfRepoIsEmpty() {
        List<LikePost> likes = repository.findAll();
        assertThat(likes, hasSize(0));
    }

    @Test public void shouldFindOneLikeIfRepoHasOneLike() {
        entityManager.persist(user);
        entityManager.persist(blogPost);
        entityManager.persist(likePost);
        List<LikePost> likes = repository.findAll();

        assertThat(likes, hasSize(1));
        assertThat(likes.get(0).getUser(), equalTo(user));
    }

    @Test public void shouldStoreANewLike() {
        entityManager.persist(user);
        entityManager.persist(blogPost);
        LikePost like = repository.save(likePost);

        assertThat(like.getId(), notNullValue());
    }

    @Test public void shouldFindExistingLikeByUserAndPost() {
        entityManager.persist(user);
        entityManager.persist(blogPost);
        LikePost like = repository.save(likePost);

        Optional<LikePost> likes = repository.findByUserAndPost(user, blogPost);

        assertTrue(likes.isPresent());
        assertEquals(likes.get().getUser(), user);
    }

    @Test public void shouldSaveModifiedLike() {
        entityManager.persist(user);
        entityManager.persist(blogPost);
        LikePost like = repository.save(likePost);

        BlogPost differentPost = entityManager.persist(createPost());

        Optional<LikePost> likeToModify = repository.findById(likePost.getId());
        assertTrue(likeToModify.isPresent());
        likeToModify.get().setPost(differentPost);
        repository.save(likeToModify.get());
        assertEquals(like.getPost(), differentPost);
    }

    public BlogPost createPost() {
        BlogPost post = new BlogPost();
        blogPost.setUser(user);
        blogPost.setEntry("testEntry");
        return post;
    }

}
