package com.sinjakovic.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinjakovic.models.Users.User;
import com.sinjakovic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Brandon on 4/19/2017.
 */
@Component
public class DocstarAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws JsonProcessingException, IOException {
        User user = userService.loadUserByUsername(authentication.getName());

        new Jackson2ObjectMapperBuilder();
        ObjectMapper mapper = Jackson2ObjectMapperBuilder
                .json().build();
        response.setStatus( HttpServletResponse.SC_OK );
        response.getWriter().print( mapper.writeValueAsString( user ) );
        response.getWriter().flush();
    }
}
