package com.sinjakovic.repositories.Rating;

//import com.sinjakovic.models.Core.Doc;

import com.sinjakovic.models.Core.Rating;
import com.sinjakovic.models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by Brandon on 4/20/2017.
 */
public class RatingRepositoryMongoImpl implements RatingRepository {
    @Autowired
    private MongoOperations mongo;

    public Update getUpdate(Rating rating) {
        Update update = new Update();
        update.set("rating", rating.getRating());
        update.set("date", rating.getDate());
        update.set("docNumber", rating.getDocNumber());
        update.set("user",rating.getUser());

        return update;
    }

    @Override
    public void update(Rating rating) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(rating.getId()));

        mongo.updateFirst(query, getUpdate(rating), Rating.class);
    }

    @SuppressWarnings("null")
	public List<Rating> findFiltered(User user, String title, String location, String type, String signif) {
        Query query = null;

        if (title != null && !title.equals("") && title.length() > 1) {
            query.addCriteria(Criteria.where("doc.title").regex(title));
        }
        if (location != null && !location.equals("") && location.length() == 5) {

        }
        if (type != null && !type.equals("")) {
            query.addCriteria(Criteria.where("doc.type").is(type));
        }
        if (signif != null && !signif.equals("")) {
            query.addCriteria(Criteria.where("doc.significant").is(signif));
        }

        return mongo.find(query, Rating.class);

    }
}
