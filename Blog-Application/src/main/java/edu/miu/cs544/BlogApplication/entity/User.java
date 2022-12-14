package edu.miu.cs544.BlogApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql="UPDATE users SET is_active=false WHERE id=?")
//@Where(clause = "is_active=true")
public class User {
    @Id
    private Long id;
//    @Column(unique = true)
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;
}
