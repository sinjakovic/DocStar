package com.sinjakovic.models.Users;
import com.fasterxml.jackson.annotation.JsonView;
import com.sinjakovic.Controllers.JsonViews;
import org.springframework.security.core.GrantedAuthority;

//import com.fasterxml.jackson.annotation.JsonView;

@SuppressWarnings("serial")
public class Role implements GrantedAuthority {
    @JsonView( JsonViews.Anyone.class)
    private String name;

    public Role() {
    }

    public Role( String name ) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}