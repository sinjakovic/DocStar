package com.sinjakovic.models.Users;

import com.fasterxml.jackson.annotation.JsonView;
import com.sinjakovic.Controllers.JsonViews;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by Brandon on 4/18/2017.
 */
@SuppressWarnings("serial")
@Document
public class User implements UserDetails {
    @Id
    @JsonView(JsonViews.Admin.class)
    private String id;
    @JsonView(JsonViews.Anyone.class)
    private String firstName;
    @JsonView(JsonViews.Anyone.class)
    private String lastName;
    @JsonView(JsonViews.Admin.class)
    private List<Role> roles;
    @JsonView(JsonViews.Admin.class)
    private String email;

    @Indexed(unique = true)
    @JsonView(JsonViews.Admin.class)
    private String username;
    
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    @JsonView(JsonViews.Admin.class)
    private boolean isEnabled;
    
    private boolean isCredentialsNonExpired;
    private String password;
    private String phoneNumber;


    public User() {
    }

    private User(User.Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.username = builder.username;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isEnabled = true;
        this.roles = builder.roles;
        this.isCredentialsNonExpired = true;
        this.phoneNumber = builder.phoneNumber;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIsEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setIsAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public boolean getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public static class Builder {
        private String id;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private String username;
        private List<Role> roles;
        private String phoneNumber;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder roles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
