package com.clone.twitter;


import com.clone.twitter.model.Post;
import com.clone.twitter.model.User;
import com.clone.twitter.service.TwitterService;
import com.clone.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @Autowired
    private UserService userService;


    /**
     * Forwards the login request to service layer
     *
     * @param email: user's login email
     * @param password: user's login password
     * @return user in JSON format
     */
    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<?> login(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password) {
        User user = userService.login(email, password);
        return ResponseEntity.ok(user);
    }

    /**
     * Forward the post request to service layer
     *
     * @param post: post content
     * @param userId: user's id
     * @return post in JSON format
     */
    @RequestMapping(value = "/tweet", method = POST)
    public ResponseEntity<?> tweet(@RequestParam(value = "post") String post,
                                   @RequestParam(value = "id") Long userId) {
        Post result = twitterService.post(userId, post);
        return ResponseEntity.ok(result);
    }

    /**
     * Forwards the get all posts request to service layer
     *
     * @param id: user's id
     * @return list of posts in JSON format
     */
    @RequestMapping(value = "/get_tweets/{id}", method = GET)
    public ResponseEntity<?> getTweets(@PathVariable Long id) {
        return ResponseEntity.ok(twitterService.getAllPosts(id));
    }

}
