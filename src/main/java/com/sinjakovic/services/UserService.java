package com.sinjakovic.services;

import com.sinjakovic.models.Users.User;
import com.sinjakovic.repositories.User.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Brandon on 4/19/2017.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepositoryMongo userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if( user == null ) {
            throw new UsernameNotFoundException("no such user");
        }else {
            return user;
        }
    }

    public List<User> findOtherUsers(String username){
        return userRepository.findByUsernameNot(username);
    }

    public User save(User user){
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById( String id ){
        return userRepository.findById(id);
    }

    public User update(User user){
        return userRepository.update(user);
    }
}
