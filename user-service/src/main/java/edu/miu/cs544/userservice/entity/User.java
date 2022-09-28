package edu.miu.cs544.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql="UPDATE users SET is_active=false WHERE id=?")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    @JsonIgnore
    private boolean is_active = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
        private List<Role> roles;

    public boolean addRole(Role role) {
        boolean success = false;
        if(roles != null && roles.add(role)) {
            success = true;
        }
        return success;
    }

    public boolean removeRole(Role role) {
        boolean success = false;
        if(roles.remove(role)) {
            success = true;
        }
        return success;
    }
}
