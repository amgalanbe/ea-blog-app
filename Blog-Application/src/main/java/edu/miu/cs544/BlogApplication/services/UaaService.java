package edu.miu.cs544.BlogApplication.services;

import edu.miu.cs544.BlogApplication.dto.UserDto;
import edu.miu.cs544.BlogApplication.entity.User;
import edu.miu.cs544.BlogApplication.model.LoginRequest;
import edu.miu.cs544.BlogApplication.model.LoginResponse;
import edu.miu.cs544.BlogApplication.model.SignupResponse;

public interface UaaService {
    public LoginResponse login(LoginRequest loginRequest);
    public SignupResponse signup(User user);
}
