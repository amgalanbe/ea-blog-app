package edu.miu.cs544.BlogApplication.dao;

import edu.miu.cs544.BlogApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
