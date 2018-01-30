package com.sinjakovic.repositories.User;

import com.sinjakovic.models.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by Brandon on 4/19/2017.
 */
public class UserRepositoryMongoImpl implements UserRepository {
    @Autowired
    private MongoOperations mongo;

    private Update getUpdate(User existing, User user) {
        Update update = new Update();
                
        if(user.getPassword() == null || user.getPassword().equals("")){
            update.set("password", existing.getPassword());

        }else{
            update.set("password", user.getPassword());

        }
        update.set("firstName", user.getFirstName());
        update.set("lastName", user.getLastName());
        update.set("email", user.getEmail());
        update.set("phoneNumber", user.getPhoneNumber());
        update.set("username", user.getUsername());
        update.set("isEnabled", user.getIsEnabled());
        update.set("roles", user.getRoles());
        update.set("isAccountNonExpired", existing.getIsAccountNonExpired());
        update.set("isAccountNonLocked", existing.getIsAccountNonLocked());
        update.set("isCredentialsNonExpired", existing.getIsCredentialsNonExpired());

        return update;
    }

    @Override
    public User update(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        User existing = mongo.findOne(query, User.class);
        
        mongo.updateFirst(query, getUpdate(existing, user), User.class);
        return user;
    }
}
