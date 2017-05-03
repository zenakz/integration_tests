package edu.iis.mto.integration.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.iis.mto.integration.api.request.UserRequest;
import edu.iis.mto.integration.api.response.ResponseFactory;
import edu.iis.mto.integration.api.response.UserResponse;
import edu.iis.mto.integration.dto.UserData;
import edu.iis.mto.integration.services.DataFinder;
import edu.iis.mto.integration.services.BlogService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/blog")
public class BlogApi {

    private final static Logger logger = LoggerFactory.getLogger(BlogApi.class);

    @Autowired
    private BlogService userService;

    @Autowired
    private DataFinder finder;

    @Autowired
    private ResponseFactory responseFactory;

    @ApiOperation(value = "Creates new user")
    @RequestMapping(method = RequestMethod.POST, path = "/user", produces = "application/json")
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        logger.debug("create user endpoint called for data '{}'", userRequest);
        Long userId = userService.createUser(userRequest);
        UserData newUser = finder.findUser(userId);
        return responseFactory.userResponse(newUser);
    }

    @ApiOperation(value = "get user info basing on user id")
    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}", produces = "application/json")
    public UserResponse getUser(@PathVariable Long userId) {
        logger.debug("get user endpoint called for user id '{}'", userId);
        UserData newUser = finder.findUser(userId);
        return responseFactory.userResponse(newUser);
    }

    @ApiOperation(value = "find users basing on search string")
    @RequestMapping(method = RequestMethod.GET, path = "/user/find", produces = "application/json")
    public UserResponse findUser(@RequestParam String searchString) {
        logger.debug("find users endpoint called for searchString '{}'", searchString);
        List<UserData> users = finder.findUsers(searchString);
        return responseFactory.usersResponse(users);
    }
}
