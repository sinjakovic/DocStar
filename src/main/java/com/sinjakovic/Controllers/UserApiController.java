package com.sinjakovic.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sinjakovic.models.Core.Rating;
import com.sinjakovic.models.Users.User;
import com.sinjakovic.services.DocListService;
import com.sinjakovic.services.FederalGovService;
import com.sinjakovic.services.RatingService;
import com.sinjakovic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import com.sinjakovic.models.Core.Doc;
//import com.sinjakovic.services.DocListService;
//import com.sinjakovic.services.DocService;

/**
 * Created by Brandon on 4/19/2017.
 */
@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private DocListService docListService;

    @Autowired
    private FederalGovService federalGovService;

    @Autowired
    private RatingService ratingService;

    @JsonView(JsonViews.User.class)
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public User profile(@AuthenticationPrincipal User user) {
        return user;
    }

    @JsonView(JsonViews.User.class)
    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public User updateProfile(@AuthenticationPrincipal User user, @RequestBody User updatedUser) {
        return userService.update(updatedUser);
    }

    @JsonView(JsonViews.User.class)
    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public String getDocuments(@AuthenticationPrincipal User user) {
        return federalGovService.findMultiple(docListService.getDocIds(user));
    }

    @JsonView(JsonViews.User.class)
    @RequestMapping(value = "/documents/{docId}", method = RequestMethod.GET)
    public String getDoc(@AuthenticationPrincipal User user, @PathVariable String docId) {
        return federalGovService.findById( docId );
    }

    @JsonView(JsonViews.User.class)
    @RequestMapping(value = "/documents/{dID}/rating", method = RequestMethod.POST)
    public Rating rateDoc(@AuthenticationPrincipal User user, @PathVariable String dID,
                          @RequestBody Rating rating) {


        return ratingService.createRating(user, dID, rating.getRating());
    }

    @JsonView(JsonViews.User.class)
    @RequestMapping(value = "/documents/{dID}/rating", method = RequestMethod.GET)
    public Rating getRating(@AuthenticationPrincipal User user, @PathVariable String dID) {
        return ratingService.findByUserAndDocNumber(user, dID);
    }

    @JsonView(JsonViews.User.class)
    @RequestMapping(value = "/ratings", method = RequestMethod.GET)
    public List<Rating> getRatings(@AuthenticationPrincipal User user) {
        return ratingService.findByUser(user);
    }

//    @JsonView(JsonViews.User.class)
//    @RequestMapping(value = "/ratings/{id}", method = RequestMethod.GET)
//    public Rating getRating(@AuthenticationPrincipal User user) {
//        return ratingService.findByUser(user);
//    }

}
