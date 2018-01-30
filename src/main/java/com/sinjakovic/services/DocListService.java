package com.sinjakovic.services;

import com.sinjakovic.models.Core.DocList;
import com.sinjakovic.models.Users.User;
import com.sinjakovic.repositories.DocList.DocListRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Brandon on 4/20/2017.
 */
@Service
public class DocListService {

    @Autowired
    private DocListRepositoryMongo docListRepositoryMongo;

    public List<DocList> findAll() {
        return docListRepositoryMongo.findAll();
    }

    public DocList findById(String id) {
        return docListRepositoryMongo.findById(id);
    }

    public List<String> getDocIds(User user) {
        List<String> docIds = new LinkedList<String>();
        List<DocList> docLists = docListRepositoryMongo.findByUser(user);

        for(DocList list : docLists){
            for(String docId : list.getDocIds() ){
                docIds.add(docId);
            }
        }

        return docIds;
    }

    public DocList save(DocList docList) {
        return docListRepositoryMongo.save(docList);
    }

    public void update(DocList docList) {
        docListRepositoryMongo.update(docList);
    }
}
