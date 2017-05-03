package edu.iis.mto.integration.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BlogPost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(columnDefinition = "CLOB NOT NULL")
    @Lob
    private String entry;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "likeId", nullable = false)
    private List<LikePost> likes;

    public BlogPost() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setLikes(List<LikePost> likes) {
        this.likes = likes;
    }

    public List<LikePost> getLikes() {
        return likes;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
