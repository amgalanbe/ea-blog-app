package edu.miu.cs544.BlogApplication.dao;

import edu.miu.cs544.BlogApplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostDAO extends JpaRepository<Post, Long> {
}
