package edu.iis.mto.integration.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import edu.iis.mto.integration.api.request.PostRequest;
import edu.iis.mto.integration.api.request.UserRequest;
import edu.iis.mto.integration.domain.model.BlogPost;
import edu.iis.mto.integration.domain.model.User;
import edu.iis.mto.integration.dto.PostData;
import edu.iis.mto.integration.dto.UserData;

@Component
public class BlogDataMapperImpl implements DataMapper {

    private ModelMapper mapper;

    @Override
    public User mapToEntity(UserRequest userRequest) {
        return mapper.map(userRequest, User.class);
    }

    @Override
    public BlogPost mapToEntity(PostRequest postRequest) {
        return mapper.map(postRequest, BlogPost.class);
    }

    @Override
    public UserData mapToDto(User user) {
        return mapper.map(user, UserData.class);
    }

    @Override
    public PostData mapToDto(BlogPost blogPost) {
        return mapper.map(blogPost, PostData.class);
    }
}
