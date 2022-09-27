package edu.miu.cs544.BlogApplication.dto;

import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    private String body;
}
