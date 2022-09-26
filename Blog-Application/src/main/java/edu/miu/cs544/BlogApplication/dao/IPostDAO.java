package edu.miu.cs544.BlogApplication.dao;

import edu.miu.cs544.BlogApplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostDAO extends JpaRepository<Post, Long> {
}
