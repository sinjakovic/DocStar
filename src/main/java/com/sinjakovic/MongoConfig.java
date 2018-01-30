package com.sinjakovic;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

/**
 * Created by Brandon on 4/20/2017.
 */
@Configuration
public class MongoConfig {
    public @Bean Mongo mongo() throws UnknownHostException {
        return new Mongo("localhost");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate( mongo(), "docstar");
    }
}