package edu.miu.cs455.users.dao;

import edu.miu.cs455.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDAO extends JpaRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);
}
