package edu.mum.cs544.dao;

import edu.mum.cs544.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICommentDAO extends JpaRepository<Comment, Long> {

}
