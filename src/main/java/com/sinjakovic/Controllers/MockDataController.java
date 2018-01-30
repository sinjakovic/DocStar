package com.sinjakovic.Controllers;

import com.sinjakovic.models.Users.Role;
import com.sinjakovic.models.Users.User;
import com.sinjakovic.repositories.User.UserRepositoryMongo;
import com.sinjakovic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.*;
/**
 * Created by Brandon on 4/21/2017.
 */

@Controller
public class MockDataController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    @PostConstruct
    private void createMockData() {
        createUsers();
    }

    private void createUsers(){
        if( userRepositoryMongo.count() > 0){
            return;
        }
        List<String> firsts = Arrays.asList(new String[] {"Kenny", "Jill", "Karen", "Jim", "William", "Jose", "Amanda", "Deantye", "Sheila"});
        List<String> lasts = Arrays.asList(new String[] { "Hunt", "Geringer", "Alvarez", "Hodgkins", "Murphy", "Gorski", "Doe", "White", "Jones" });
        List<Role> roles = Arrays.asList(new Role[] { new Role("ROLE_USER"), new Role("ROLE_ADMIN") });
        Set<String> userNames = new HashSet<String>();

        //random users
        for (int i = 0; i < 25; i++) {
            Collections.shuffle(roles);
            Collections.shuffle(firsts);
            Collections.shuffle(lasts);

            String firstName = firsts.get(0);
            String lastName = lasts.get(0);
            String userName = (firstName.charAt(0) + lastName).toLowerCase();
            String email = lastName + "." + firstName + "@docstar.em.org";

            User user = new User.Builder().firstName(firstName).lastName(lastName).username(userName).email(email).password("123").roles(roles).phoneNumber(generatePhoneNumber()).build();

            try {
                if (!userNames.contains(userName)) {
                    user = userService.save(user);
                    userNames.add(userName);
                }
            } catch (Exception e) {
                userNames.add(userName);
            }
        }
        //hardcoded admin
        User admin = new User.Builder().firstName("Bilbo").lastName("Baggins").username("Admin").email("bilbo@mordor.org").password("123").roles(roles).phoneNumber(generatePhoneNumber()).build();
        userService.save(admin);
        
        //hardcoded user
        User user = new User.Builder().firstName("Gandalf").lastName("Gray").username("user").email("gandalf@mordor.org").password("123").roles(Arrays.asList(new Role[] { new Role("ROLE_USER") })).phoneNumber(generatePhoneNumber()).build();
        userService.save(user);
    }

    private String generatePhoneNumber(){
        return "55555"+ Long.toString(Math.round(Math.random()*10000));
    }
}

