package edu.miu.cs544.userservice.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.userservice.config.MessagingConfig;
import edu.miu.cs544.userservice.dao.IRoleDAO;
import edu.miu.cs544.userservice.dao.IUserDAO;
import edu.miu.cs544.userservice.entity.User;
import edu.miu.cs544.userservice.model.ServiceRequest;
import edu.miu.cs544.userservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    public void update(User user) {
        if(userDAO.existsById(user.getId())) {
            save(user);
        }
    }

    @Override
    public void deleteById(long id) {
        User user = userDAO.findById(id).orElse(null);
        if(user != null) {
            user.set_active(false);
            update(user);
        }
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(ServiceRequest serviceRequest) {
        if(serviceRequest.getEntity().equals("User")) {
            ObjectMapper mapper = new ObjectMapper();

            if(serviceRequest.getAction().equals("create")) {
                User user = mapper.convertValue(serviceRequest.getObject(), new TypeReference<User>() { });
                save(user);
            }else if(serviceRequest.getAction().equals("update")) {
                User user = mapper.convertValue(serviceRequest.getObject(), new TypeReference<User>() { });
                update(user);
            } else if(serviceRequest.getAction().equals("delete")) {
                Long id = mapper.convertValue(serviceRequest.getObject(), new TypeReference<Long>() { });
                deleteById(id);
            }
        }
    }
}
