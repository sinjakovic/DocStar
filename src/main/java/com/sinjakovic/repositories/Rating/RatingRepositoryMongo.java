package com.sinjakovic.repositories.Rating;


import com.sinjakovic.models.Core.Rating;
import com.sinjakovic.models.Users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Brandon on 4/19/2017.
 */
public interface RatingRepositoryMongo extends MongoRepository<Rating, String>, RatingRepository {
    public List<Rating> findByDocNumber(String docNumber);

    public List<Rating> findByUser(User user);

    public Rating findByUserAndDocNumber(User user, String doc);

    public Rating findById(String id);
}
