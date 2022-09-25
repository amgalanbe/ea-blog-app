package edu.miu.cs544.BlogApplication.services.Impl;

import edu.miu.cs544.BlogApplication.entity.User;
import edu.miu.cs544.BlogApplication.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final String user_url = "http://localhost:8083/users";
    @Override
    public long save(User user) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(long id) {

    }
}
