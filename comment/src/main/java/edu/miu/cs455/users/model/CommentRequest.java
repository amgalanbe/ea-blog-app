package edu.miu.cs455.users.model;

import edu.miu.cs455.users.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long postId;
    private Comment comment;
}
