package edu.miu.cs544.BlogApplication.dto;

import edu.miu.cs544.BlogApplication.entity.Comment;
import edu.miu.cs544.BlogApplication.entity.User;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String body;
}
