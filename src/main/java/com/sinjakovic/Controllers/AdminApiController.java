package com.sinjakovic.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sinjakovic.models.Core.DocList;
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

/**
 * Created by Brandon on 4/19/2017.
 */
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private DocListService docListService;

    @Autowired
    private FederalGovService federalGovService;

    @Autowired
    private RatingService ratingService;


    @JsonView(JsonViews.Admin.class)
    @RequestMapping(value = "/profile", method= RequestMethod.GET)
    public User profile(@AuthenticationPrincipal User user){
        return user;
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping(value = "/profile", method= RequestMethod.PUT)
    public void updateProfile(@AuthenticationPrincipal User user,
                              @RequestBody User updatedUser ){
        userService.update(updatedUser);
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/lists", method= RequestMethod.GET )
    public List<DocList> getLists() {
        return docListService.findAll();
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/lists", method= RequestMethod.POST )
    public void createList( @RequestBody DocList docList ){
        docListService.save( docList );
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/lists/{dlID}", method= RequestMethod.GET )
    public DocList getListDocs(@PathVariable String dlID ) {
        return docListService.findById(dlID);
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/lists/{dlID}", method= RequestMethod.PUT )
    public void updateList(@PathVariable String dlID,
                              @RequestBody DocList docList ) {
        docListService.update(docList);
    }


    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/ratings", method= RequestMethod.GET )
    public List<Rating> getRatings() {
        return ratingService.findAll();
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/documents/{dID}/ratings", method= RequestMethod.GET )
    public List<Rating> getDocRatings(@PathVariable String dID ) {
        return ratingService.findByDocNumber(dID);
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/documents", method= RequestMethod.GET )
    public String getDocuments(@RequestParam( required=false, defaultValue="" ) String title, @RequestParam( required=false, defaultValue="" ) String zip,
                               @RequestParam( required=false, defaultValue="" ) String type, @RequestParam( required=false, defaultValue="" ) String significance) {
        return federalGovService.findFiltered(title.trim(), zip.trim(), type, significance );
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/documents/{dID}", method= RequestMethod.GET )
    public String getDocument( @PathVariable String dID ) {
        return federalGovService.findById(dID);
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/users", method= RequestMethod.GET )
    public List<User> getUsers(@AuthenticationPrincipal User user) {
        return userService.findOtherUsers(user.getUsername());
    } //minus the current user

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/users", method= RequestMethod.POST )
    public void createUsers( @RequestBody User newUser  ) {
        userService.save(newUser);
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/users/{uid}", method= RequestMethod.GET )
    public User getUser(@PathVariable String uid ) {
        return userService.findById(uid);
    }

    @JsonView(JsonViews.Admin.class)
    @RequestMapping( value="/users/{uid}", method= RequestMethod.PUT )
    public void updateUser(@PathVariable String uid, @RequestBody User updatedUser  ) {
        userService.update( updatedUser);
    }
}