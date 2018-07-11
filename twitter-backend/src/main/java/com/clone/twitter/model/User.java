package com.clone.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    /**
     * Get the user's id
     * @return Long
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the user's id
     * @param userId Long
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the user's email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user's email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user's password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user's password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get all of user's posts
     * @return List
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Set user's post
     * @param posts List
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}