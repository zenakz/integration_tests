package edu.iis.mto.integration.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iis.mto.integration.domain.model.LikePost;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    Optional<LikePost> findByUserAndPost(Long userId, Long postId);

}
