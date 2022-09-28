package edu.mum.cs544.dao;

import edu.mum.cs544.Entity.Post;
import edu.mum.cs544.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostDAO extends JpaRepository<Post, Long> {
    public List<Post> findPostsByUser(User user);
}
