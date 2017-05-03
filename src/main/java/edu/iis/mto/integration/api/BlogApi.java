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

import edu.iis.mto.integration.api.request.LikePostRequest;
import edu.iis.mto.integration.api.request.PostRequest;
import edu.iis.mto.integration.api.request.UserRequest;
import edu.iis.mto.integration.dto.PostData;
import edu.iis.mto.integration.dto.UserData;
import edu.iis.mto.integration.services.BlogService;
import edu.iis.mto.integration.services.DataFinder;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/blog")
public class BlogApi {

    private final static Logger logger = LoggerFactory.getLogger(BlogApi.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private DataFinder finder;

    @ApiOperation(value = "Creates new user")
    @RequestMapping(method = RequestMethod.POST, path = "/user", produces = "application/json")
    public Long createUser(@RequestBody UserRequest userRequest) {
        logger.debug("create user endpoint called for data '{}'", userRequest);
        Long userId = blogService.createUser(userRequest);
        return userId;
    }

    @ApiOperation(value = "get user info basing on user id")
    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}", produces = "application/json")
    public UserData getUser(@PathVariable("id") Long userId) {
        logger.debug("get user endpoint called for user id '{}'", userId);
        UserData newUser = finder.getUserData(userId);
        return newUser;
    }

    @ApiOperation(value = "find users basing on search string")
    @RequestMapping(method = RequestMethod.GET, path = "/user/find", produces = "application/json")
    public List<UserData> findUser(@RequestParam String searchString) {
        logger.debug("find users endpoint called for searchString '{}'", searchString);
        List<UserData> users = finder.findUsers(searchString);
        return users;
    }

    @ApiOperation(value = "Creates new blog post")
    @RequestMapping(method = RequestMethod.POST, path = "/user/{id}/post", produces = "application/json")
    public Long createPost(@PathVariable("id") Long userId, @RequestBody PostRequest postRequest) {
        logger.debug("create post endpoint called for data '{}'", postRequest);

        Long postId = blogService.createPost(userId, postRequest);
        return postId;
    }

    @ApiOperation(value = "Add like to blog post")
    @RequestMapping(method = RequestMethod.POST, path = "/post/{id}/like", produces = "application/json")
    public Long createPost(@PathVariable("id") Long postId, @RequestBody LikePostRequest likeRequest) {
        logger.debug("add like to post endpoint called for data '{}'", likeRequest);

        blogService.addLikeToPost(postId, likeRequest);
        return postId;
    }

    @ApiOperation(value = "get user posts basing on user id")
    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}/post", produces = "application/json")
    public List<PostData> getUserPosts(@PathVariable("id") Long userId) {
        logger.debug("get user posts endpoint called for user id '{}'", userId);
        List<PostData> posts = finder.getUserPosts(userId);
        return posts;
    }
}
