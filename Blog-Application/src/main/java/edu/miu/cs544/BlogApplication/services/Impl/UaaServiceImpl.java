package edu.miu.cs544.BlogApplication.services.Impl;

import edu.miu.cs544.BlogApplication.dao.IRoleDAO;
import edu.miu.cs544.BlogApplication.dao.IUserDAO;
import edu.miu.cs544.BlogApplication.entity.User;
import edu.miu.cs544.BlogApplication.model.LoginRequest;
import edu.miu.cs544.BlogApplication.model.LoginResponse;
import edu.miu.cs544.BlogApplication.security.JwtHelper;
import edu.miu.cs544.BlogApplication.services.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtHelper jwtHelper;
    @Autowired
    private final IUserDAO userDAO;
    @Autowired
    private final IRoleDAO roleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication result = authenticationManager.authenticate(authenticationToken);
            final String accessToken = jwtHelper.generateToken(loginRequest.getUsername());
            LoginResponse loginResponse = new LoginResponse(accessToken);
            return loginResponse;
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
        }
        return null;
    }

    @Override
    public User signup(User user) {
        //check if username exist or not
        //send success or request fail message
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleDAO.findByRole("USER")));
        userDAO.save(user);
        return user;
    }
}
