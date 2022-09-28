package edu.miu.cs455.users.dto;

import edu.miu.cs455.users.entity.Post;
import edu.miu.cs455.users.entity.User;

import java.util.Date;

public class CommentDTO {
    private Long id;
    private String body;
    private Date creationDate;
    private User user;
    private Post post;
}

