package edu.miu.cs455.users.dao;

import edu.miu.cs455.users.entity.Post;
import edu.miu.cs455.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostDAO extends JpaRepository<Post, Long> {
}
