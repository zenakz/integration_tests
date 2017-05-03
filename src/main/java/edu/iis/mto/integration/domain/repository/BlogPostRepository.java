package edu.iis.mto.integration.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iis.mto.integration.domain.model.BlogPost;
import edu.iis.mto.integration.domain.model.User;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findByUser(User user);

}
