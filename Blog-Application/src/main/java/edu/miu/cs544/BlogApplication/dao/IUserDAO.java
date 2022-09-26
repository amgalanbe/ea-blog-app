package edu.miu.cs544.BlogApplication.dao;

import edu.miu.cs544.BlogApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
