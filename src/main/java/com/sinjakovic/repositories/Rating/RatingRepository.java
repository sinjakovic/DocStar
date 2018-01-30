package com.sinjakovic.repositories.Rating;

import com.sinjakovic.models.Core.Rating;
import com.sinjakovic.models.Users.User;

import java.util.List;

/**
 * Created by Brandon on 4/20/2017.
 */
public interface RatingRepository {
    public void update(Rating rating);

    public List<Rating> findFiltered(User user, String title, String location, String type, String signif);
}
