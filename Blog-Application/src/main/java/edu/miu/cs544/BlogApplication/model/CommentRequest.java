package edu.miu.cs544.BlogApplication.model;

import edu.miu.cs544.BlogApplication.dto.CommentDto;
import edu.miu.cs544.BlogApplication.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long postId;
    private CommentDto comment;
}
