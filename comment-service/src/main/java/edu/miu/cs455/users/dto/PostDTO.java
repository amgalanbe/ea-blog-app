package edu.miu.cs455.users.dto;

import edu.miu.cs455.users.entity.Comment;
import edu.miu.cs455.users.entity.User;

import java.util.Collection;
import java.util.Date;

public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private Date creationDate;
    private Collection<Comment> comments;
    private User user;
}
