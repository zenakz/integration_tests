package edu.iis.mto.blog.domain.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.User;

@RunWith(SpringRunner.class) @DataJpaTest public class UserRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private UserRepository repository;

    private User user;

    @Before public void setUp() {
        user = createUser("Jan", "", "john@domain.com");
        repository.deleteAll();
    }

    @Test public void shouldFindNoUsersIfRepositoryIsEmpty() {

        List<User> users = repository.findAll();

        assertThat(users, hasSize(0));
    }

    @Test public void shouldFindOneUsersIfRepositoryContainsOneUserEntity() {
        User persistedUser = entityManager.
                                                  persist(user);
        List<User> users = repository.findAll();

        assertThat(users, hasSize(1));
        assertThat(users.get(0).getEmail(), equalTo(user.getEmail()));
    }

    @Test public void shouldStoreANewUser() {

        User persistedUser = repository.save(user);

        assertThat(persistedUser.getId(), notNullValue());
    }

    @Test public void shouldFindAllUsersWithSameName() {
        repository.save(createUser("Jan", "", "mail@gmail.com"));
        repository.save(createUser("Jan", "", "mail2@gmail.com"));
        repository.save(createUser("Marcin", "Adamski", "user3@gmail.com"));
        List<User> list = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Jan", "NameNotInRepo",
                "MailNotInRepo");
        assertThat(list, hasSize(2));
        assertThat(list.get(0).getFirstName(), equalTo("Jan"));
    }

    @Test public void shouldFindAllGmailUsers() {
        repository.save(createUser("Jan", "Kowalski", "user1@gmail.com"));
        repository.save(createUser("Stefan", "Nowak", "user2@gmail.com"));
        repository.save(createUser("Marcin", "Adamski", "user3@gmail.com"));
        List<User> list = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("NameNotInRepo",
                "NotInRepo", "gmail");
        assertThat(list, hasSize(3));
    }

    @Test public void shouldFindNoUsers() {
        repository.save(createUser("Jan", "Kowalski", "user1@gmail.com"));
        repository.save(createUser("Stefan", "Nowak", "user2@gmail.com"));
        repository.save(createUser("Marcin", "Adamski", "user3@gmail.com"));
        List<User> list = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("NameNotInRepo",
                "NameNotInRepo", "MailNotInRepo");
        assertThat(list, hasSize(0));
    }

    public User createUser(String name, String lastName, String email) {
        user = new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAccountStatus(AccountStatus.NEW);
        return user;
    }
}
