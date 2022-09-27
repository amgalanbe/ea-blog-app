package edu.miu.cs544.BlogApplication.services;

import edu.miu.cs544.BlogApplication.dto.CommentDto;
import edu.miu.cs544.BlogApplication.entity.Comment;

import java.util.List;

public interface CommentService {
    public long save(Comment comment, Long postId);
    public List<CommentDto> findAll();
    public Comment findById(long id);
    public void update(Comment comment);
    public void deleteById(long id);
}
