package edu.iis.mto.integration.domain.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class LikePost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @JoinColumn(name = "postId", nullable = false)
    private BlogPost post;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogPost getPost() {
        return post;
    }

    public void setPost(BlogPost post) {
        this.post = post;
    }

}
