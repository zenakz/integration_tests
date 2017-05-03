package edu.iis.mto.integration.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iis.mto.integration.domain.model.LikePost;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    LikePost findByUserAndPost(Long userId, Long postId);

}
