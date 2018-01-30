package com.sinjakovic.repositories.DocList;

import com.sinjakovic.models.Core.DocList;
import com.sinjakovic.models.Users.User;

import java.util.List;

/**
 * Created by Brandon on 4/20/2017.
 */
public interface DocListRepository {
    public void update(DocList docList);
    public List<DocList> findByUser(User user);
}
