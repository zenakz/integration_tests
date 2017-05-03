package edu.iis.mto.integration.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iis.mto.integration.domain.model.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
