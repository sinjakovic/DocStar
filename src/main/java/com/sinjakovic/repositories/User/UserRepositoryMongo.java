package com.sinjakovic.repositories.User;

import com.sinjakovic.models.Users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Brandon on 4/19/2017.
 */
public interface UserRepositoryMongo extends MongoRepository<User, String>, UserRepository {
    public User findById(String id);
    public User findByUsername(String username);
    public List<User> findByUsernameNot(String username);
}
