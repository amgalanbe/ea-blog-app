package edu.miu.cs544.BlogApplication.services.Impl;

import edu.miu.cs544.BlogApplication.entity.Comment;
import edu.miu.cs544.BlogApplication.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final String comment_url = "http://localhost:8082/comments";
    @Override
    public long save(Comment comment) {
        return 0;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment findById(long id) {
        return null;
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void deleteById(long id) {

    }
}