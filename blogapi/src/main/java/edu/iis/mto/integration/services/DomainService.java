package edu.iis.mto.integration.services;

import org.springframework.beans.factory.annotation.Autowired;

import edu.iis.mto.integration.domain.repository.BlogPostRepository;
import edu.iis.mto.integration.domain.repository.LikePostRepository;
import edu.iis.mto.integration.domain.repository.UserRepository;
import edu.iis.mto.integration.mapper.DataMapper;

public abstract class DomainService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected BlogPostRepository blogPostRepository;

    @Autowired
    protected LikePostRepository likePostRepository;

    @Autowired
    protected DataMapper mapper;
}
