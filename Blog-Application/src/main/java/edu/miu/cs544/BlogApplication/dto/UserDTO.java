package edu.miu.cs544.BlogApplication.dto;

import edu.miu.cs544.BlogApplication.entity.Comment;
import edu.miu.cs544.BlogApplication.entity.Post;
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
