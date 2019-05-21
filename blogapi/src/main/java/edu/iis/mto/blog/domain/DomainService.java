package edu.iis.mto.blog.domain;

import java.util.function.Supplier;

import edu.iis.mto.blog.domain.errors.DomainError;
import edu.iis.mto.blog.domain.repository.BlogPostRepository;
import edu.iis.mto.blog.domain.repository.LikePostRepository;
import edu.iis.mto.blog.domain.repository.UserRepository;
import edu.iis.mto.blog.mapper.DataMapper;

public abstract class DomainService {

    protected final UserRepository userRepository;

    protected final BlogPostRepository blogPostRepository;

    protected final LikePostRepository likePostRepository;

    protected final DataMapper mapper;

    protected DomainService(UserRepository userRepository, BlogPostRepository blogPostRepository, LikePostRepository likePostRepository,
            DataMapper mapper) {
        this.userRepository = userRepository;
        this.blogPostRepository = blogPostRepository;
        this.likePostRepository = likePostRepository;
        this.mapper = mapper;
    }

    protected Supplier<RuntimeException> domainError(String msg) {

        return () -> new DomainError(msg);
    }

}
