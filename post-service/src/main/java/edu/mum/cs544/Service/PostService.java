package edu.mum.cs544.Service;

import edu.mum.cs544.Entity.Post;
import edu.mum.cs544.Entity.User;
import edu.mum.cs544.dao.IPostDAO;
import edu.mum.cs544.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    @Autowired
    IPostDAO iPostDAO;
    @Autowired
    IUserDAO iUserDAO;
    public Post save(Post post, String username) {
        User user = iUserDAO.findUserByUsername(username).orElse(null);
        if(user != null) {
            post.setUser(user);
            post.setCreationDate(Date.from(Instant.now()));
            iPostDAO.save(post);
            return post;
        }
        return null;
    }
    public List<Post> findAll() {
        return iPostDAO.findAll();
    }

    public List<Post> getUserPosts(String username){
        User user = iUserDAO.findUserByUsername(username).orElse(null);
        if(user != null) {
            return iPostDAO.findPostsByUser(user);
        }
        return null;
    }

    public Optional<Post> findById(long id) {
        return iPostDAO.findById(id);
    }

    public void update(Post post) {
        Post updatingPost = iPostDAO.findById(post.getId()).orElse(null);
        if(updatingPost != null) {
            if(post.getTitle() != null) updatingPost.setTitle(post.getTitle());
            if(post.getBody() != null) updatingPost.setBody(post.getBody());
            iPostDAO.save(updatingPost);
        }
    }


    public void deleteById(long id) {
        Post post = iPostDAO.findById(id).orElse(null);
        if(post != null) {
            iPostDAO.deleteById(id);
        }
    }
}
