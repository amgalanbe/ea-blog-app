package edu.miu.cs544.BlogApplication.services.Impl;

import edu.miu.cs544.BlogApplication.config.MessagingConfig;
import edu.miu.cs544.BlogApplication.dao.IUserDAO;
import edu.miu.cs544.BlogApplication.dto.UserDto;
import edu.miu.cs544.BlogApplication.entity.User;
import edu.miu.cs544.BlogApplication.model.ServiceRequest;
import edu.miu.cs544.BlogApplication.model.SignupResponse;
import edu.miu.cs544.BlogApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final String user_url = "http://localhost:8083/users";
    public static long next = 5;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Long save(User user) {

        User existingUser = userDAO.findByUsername(user.getUsername()).orElse(null);

        if(existingUser != null)
            return null;

        Long newId = next++;
        user.setId(newId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.USER_ROUTING_KEY,
                new ServiceRequest("User", "create", modelMapper.map(user, UserDto.class)));
        return newId;
    }

    @Override
    public List<UserDto> findAll() {
        ResponseEntity<List<User>> response = restTemplate.exchange(user_url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> users = response.getBody();
        return users.stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(long id) {
        User user = restTemplate.getForObject(user_url + "/{id}", User.class, id);
        return user != null ? modelMapper.map(user, UserDto.class) : null;
    }

    @Override
    public void update(User user) {
        if(user.getPassword() != null) user.setPassword(passwordEncoder.encode(user.getPassword()));
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.USER_ROUTING_KEY,
                new ServiceRequest("User", "update", modelMapper.map(user, UserDto.class)));
    }

    @Override
    public void deleteById(long id) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.USER_ROUTING_KEY,
                new ServiceRequest("User", "delete", id));
    }
}
