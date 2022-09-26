package edu.miu.cs544.BlogApplication.dao;

import edu.miu.cs544.BlogApplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDAO extends JpaRepository<Comment, Long> {
}
