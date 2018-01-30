package com.sinjakovic.repositories.DocList;

import com.sinjakovic.models.Core.DocList;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Brandon on 4/20/2017.
 */
public interface DocListRepositoryMongo extends MongoRepository<DocList, String>, DocListRepository {
    public DocList findById(String id);
    //public DocList findByUserListUser(User user);
    //public List<DocList> findAll();

}
