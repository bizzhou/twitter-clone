package com.clone.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId", nullable = false)
    private Long postId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "post_content", nullable = false)
    String postContent;

    @Column(name = "post_time", nullable = false)
    Calendar postTime;

    public Post() {
    }


    /**
     * Get the post id of a post
     * @return postId
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * Set the post id of a post
     * @param postId: Long
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * Get the author of post
     * @return post author
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the author of the post
     * @param user: User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the post content
     * @return String
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * Set the post content
     * @param postContent String
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    /**
     * Get the post time
     * @return Calendar
     */
    public Calendar getPostTime() {
        return postTime;
    }

    /**
     * Set the post time for a post
     * @param postTime Calendar
     */
    public void setPostTime(Calendar postTime) {
        this.postTime = postTime;
    }
}