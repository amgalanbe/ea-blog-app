package edu.miu.cs544.userservice.dao;

import edu.miu.cs544.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {

}
