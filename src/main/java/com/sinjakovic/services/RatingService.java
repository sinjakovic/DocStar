package com.sinjakovic.services;


import com.sinjakovic.models.Core.Rating;
import com.sinjakovic.models.Users.User;
import com.sinjakovic.repositories.Rating.RatingRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Brandon on 4/20/2017.
 */
@Service
public class RatingService {

    @Autowired
    private RatingRepositoryMongo ratingRepositoryMongo;

    public Rating save(Rating rating){
        return ratingRepositoryMongo.save(rating);
    }

    public Rating findOne(String ratingId){
        return ratingRepositoryMongo.findOne(ratingId);
    }

    public void delete( Rating rating ){
        ratingRepositoryMongo.delete( rating );
    }

    public void update( Rating rating ) {
        ratingRepositoryMongo.update(rating);
    }

    public Rating createRating(User user, String docNumber, int rating){
        Rating userRating = new Rating.Builder()
                .user(user)
                .docNumber(docNumber)
                .rating(rating)
                .date( new Date() )
                .build();
        return ratingRepositoryMongo.save(userRating);
    }

    public Rating findByUserAndDocNumber(User user, String docNumber){
        return ratingRepositoryMongo.findByUserAndDocNumber(user, docNumber);
    }

    public List<Rating> findAll() {
        return ratingRepositoryMongo.findAll();
    }


    public List<Rating> findFiltered(User user, String title, String location, String type, String signif) {

        return ratingRepositoryMongo.findFiltered(user, title, location, type, signif);
    }

    public List<Rating> findByDocNumber(String docId){
        return ratingRepositoryMongo.findByDocNumber(docId);
    }

    public List<Rating> findByUser(User user){
        return ratingRepositoryMongo.findByUser(user);
    }
}
