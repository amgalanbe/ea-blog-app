package edu.miu.cs544.BlogApplication.services;

import edu.miu.cs544.BlogApplication.dto.UserDto;
import edu.miu.cs544.BlogApplication.entity.User;

import java.util.List;

public interface UserService {

    public Long save(User user);
    public List<UserDto> findAll();
    public UserDto findById(long id);
    public void update(User user);
    public void deleteById(long id);
}
