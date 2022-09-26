package edu.miu.cs544.BlogApplication.dto;

import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.entity.User;

import java.util.Date;

public class CommentDto {
    private Long id;
    private String body;
    private Date publishedDate;
    private User user;
    private Post post;
}
