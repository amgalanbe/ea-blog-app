package edu.miu.cs544.userservice.dao;

import edu.miu.cs544.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);
}
