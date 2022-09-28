package edu.mum.cs544.dao;

import edu.mum.cs544.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserDAO extends JpaRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);
}
