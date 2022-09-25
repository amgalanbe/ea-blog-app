package edu.miu.cs544.BlogApplication.dto;

import edu.miu.cs544.BlogApplication.entity.Comment;
import edu.miu.cs544.BlogApplication.entity.User;

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
