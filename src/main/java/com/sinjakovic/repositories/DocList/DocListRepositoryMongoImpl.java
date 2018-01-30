package com.sinjakovic.repositories.DocList;

import com.sinjakovic.models.Core.DocList;
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
public class DocListRepositoryMongoImpl implements DocListRepository {
    @Autowired
    private MongoOperations mongo;

    public Update getUpdate(DocList docList) {
        Update update = new Update();
        update.set("name", docList.getName());
        update.set("docIds", docList.getDocIds());
        update.set("users", docList.getUsers());
        return update;
    }


    @Override
    public void update(DocList docList) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(docList.getId()));

        mongo.updateFirst(query, getUpdate(docList), DocList.class);
    }

    @Override
    public List<DocList> findByUser(User user){
        Query query = new Query();

        query.addCriteria(Criteria.where("users").elemMatch(Criteria.where("username").is(user.getUsername())));

        return mongo.find(query, DocList.class);
    }
}
