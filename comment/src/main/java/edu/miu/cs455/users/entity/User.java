package edu.miu.cs455.users.entity;

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
@SQLDelete(sql="UPDATE users SET active=true WHERE id=?")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    @JsonIgnore
    private boolean is_active = true;
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts;

//    public boolean addComment(Comment comment) {
//        boolean success = false;
//        if(comment != null && comments.add(comment)) {
//            success = true;
//        }
//        return success;
//    }
//
//    public boolean removeComment(Comment comment) {
//        boolean success = false;
//        if(comments.remove(comment)) {
//            success = true;
//        }
//        return success;
//    }

}
