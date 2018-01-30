package com.sinjakovic.models.Core;

import com.fasterxml.jackson.annotation.JsonView;
import com.sinjakovic.Controllers.JsonViews;
import com.sinjakovic.models.Users.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Brandon on 4/19/2017.
 */
@Document
public class DocList {
    @Id
    @JsonView( JsonViews.Anyone.class )
    private String id;

    @JsonView( { JsonViews.Anyone.class  } )
    private String name;

    @DBRef
    @JsonView( { JsonViews.Anyone.class  } )
    private List<User> users;

    @JsonView( { JsonViews.Anyone.class  } )
    private List<String> docIds;

    public DocList() {
    }

    private DocList(DocList.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.users = builder.users;
        this.docIds = builder.docIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<String> getDocIds() {
        return docIds;
    }

    public void setDocIds(List<String> docIds) {
        this.docIds = docIds;
    }

    public static class Builder {
        private String id;
        private String name;
        private List<User> users;
        private List<String> docIds;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder users(List<User> users){
            this.users = users;
            return this;
        }

        public Builder docIds(List<String> docIds){
            this.docIds = docIds;
            return this;
        }

        public DocList build(){
            return new DocList(this);
        }
    }
}
