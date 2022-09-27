package edu.miu.cs455.users.dao;

import edu.miu.cs455.users.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDAO extends JpaRepository<Comment, Long> {

}
