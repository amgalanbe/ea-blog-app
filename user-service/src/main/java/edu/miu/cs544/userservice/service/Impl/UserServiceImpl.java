package edu.miu.cs544.userservice.service.Impl;

import edu.miu.cs544.userservice.dao.IRoleDAO;
import edu.miu.cs544.userservice.dao.IUserDAO;
import edu.miu.cs544.userservice.entity.User;
import edu.miu.cs544.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IRoleDAO roleDAO;

    @Override
    public long save(User user) {
        user.setRoles(Arrays.asList(roleDAO.findRoleByRole("USER")));
        User created = userDAO.save(user);
        return created.getId();
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll().stream().filter(u -> u.is_active()).collect(Collectors.toList());
    }

    @Override
    public User findById(long id) {
        User user = userDAO.findById(id).orElse(null);
        return user == null || !user.is_active() ? null : user ;
    }

    @Override
    public User findByUsername(String username) {
        User user = userDAO.findUserByUsername(username).orElse(null);
        return user == null || !user.is_active() ? null : user;
    }

    @Override
    public void update(User user) {
        User updatingUser = userDAO.findById(user.getId()).orElse(null);
        if(updatingUser != null) {
            System.out.println("updating user " + user.getId());
            if(user.getUsername() != null) updatingUser.setUsername(user.getUsername());
            if(user.getPassword() != null) updatingUser.setPassword(user.getPassword());
            if(user.getEmail() != null) updatingUser.setEmail(user.getEmail());
            if(user.getFirstname() != null) updatingUser.setFirstname(user.getFirstname());
            if(user.getLastname() != null) updatingUser.setLastname(user.getLastname());
            save(updatingUser);
        }
    }

    @Override
    public void deleteById(long id) {
        User user = userDAO.findById(id).orElse(null);
        if (user != null) {
//            user.set_active(false);
//            update(user);
            userDAO.deleteById(id);
        }
    }
}
