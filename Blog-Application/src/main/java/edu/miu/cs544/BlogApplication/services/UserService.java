package edu.miu.cs544.BlogApplication.services;

import edu.miu.cs544.BlogApplication.entity.User;

import java.util.List;

public interface UserService {
    public long save(User user);
    public List<User> findAll();
    public User findById(long id);
    public void update(User user);
    public void deleteById(long id);
}
