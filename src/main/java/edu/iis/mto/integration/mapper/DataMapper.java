package edu.iis.mto.integration.mapper;

import edu.iis.mto.integration.api.request.PostRequest;
import edu.iis.mto.integration.api.request.UserRequest;
import edu.iis.mto.integration.domain.model.BlogPost;
import edu.iis.mto.integration.domain.model.User;

public interface DataMapper {

    User mapToEntity(UserRequest userRequest);

    BlogPost mapToEntity(PostRequest postRequest);

}
