package edu.miu.cs455.users.service;

import edu.miu.cs455.users.dao.IPostDAO;
import edu.miu.cs455.users.entity.Comment;
import edu.miu.cs455.users.dao.ICommentDAO;
import edu.miu.cs455.users.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class CommentService {

    //private final String comment_url = "http://localhost:8082/comments";
    @Autowired
    ICommentDAO commentDAO;

    @Autowired
    IPostDAO postDAO;

    public Comment findById(Long id){
        return commentDAO.findById(id).orElse(null);
    }

    public List<Comment> findAll(){
       return commentDAO.findAll();
    }

    public Comment save(Comment comment, Long postId){
        Post post = postDAO.findById(postId).orElse(null);
        if(post != null) {
            comment.setPost(post);
            comment.setCreationDate(Date.from(Instant.now()));
            commentDAO.save(comment);
            return comment;
        }
        return null;
    }

    public void update(Comment comment){
        Comment updatingComment = commentDAO.findById(comment.getId()).orElse(null);
        if(updatingComment != null) {
            if(comment.getBody() != null) updatingComment.setBody(comment.getBody());
            commentDAO.save(updatingComment);
        }
    }

    public void deleteById(long id){
        Comment comment = commentDAO.findById(id).orElse(null);
        if(comment != null) {
            commentDAO.deleteById(id);
        }
    }
}
