package com.sinjakovic.models.Core;

import com.fasterxml.jackson.annotation.JsonView;
import com.sinjakovic.Controllers.JsonViews;
import com.sinjakovic.models.Users.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Brandon on 4/19/2017.
 */
@Document
public class Rating {
    @Id
    @JsonView(JsonViews.Anyone.class)
    private String id;

    @DBRef
    @JsonView(JsonViews.Anyone.class)
    private User user;

    @JsonView(JsonViews.Anyone.class)
    private String docNumber;

    @JsonView(JsonViews.Anyone.class)
    private int rating;

    @JsonView(JsonViews.Anyone.class)
    private Date date;

    public Rating() {
    }

    private Rating(Rating.Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.docNumber = builder.docNumber;
        this.rating = builder.rating;
        this.date = builder.date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class Builder {
        private String id;
        private User user;
        private String docNumber;
        private int rating;
        private Date date;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder docNumber(String docNumber) {
            this.docNumber = docNumber;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Rating build() {
            return new Rating(this);
        }

    }
}
