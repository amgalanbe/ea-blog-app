package edu.miu.cs544.BlogApplication.services;

import edu.miu.cs544.BlogApplication.entity.User;
import edu.miu.cs544.BlogApplication.model.LoginRequest;
import edu.miu.cs544.BlogApplication.model.LoginResponse;

public interface UaaService {
    public LoginResponse login(LoginRequest loginRequest);
    public User signup(User user);
}
