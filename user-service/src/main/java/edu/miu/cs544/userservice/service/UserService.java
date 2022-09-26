package edu.miu.cs544.userservice.service;

import edu.miu.cs544.userservice.entity.User;

import java.util.List;

public interface UserService {
    public long save(User user);
    public List<User> findAll();
    public User findById(long id);
    public User findByUsername(String username);
    public void update(User user);
    public void deleteById(long id);
}
