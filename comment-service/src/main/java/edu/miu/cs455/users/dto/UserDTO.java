package edu.miu.cs455.users.dto;

import edu.miu.cs455.users.entity.Comment;
import edu.miu.cs455.users.entity.Post;
import edu.miu.cs455.users.entity.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    //    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private List<Comment> comments;
    private List<Post> posts;

}
