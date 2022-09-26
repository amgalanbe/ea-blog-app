package edu.miu.cs544.userservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.userservice.config.MessagingConfig;
import edu.miu.cs544.userservice.entity.User;
import edu.miu.cs544.userservice.model.ServiceRequest;
import edu.miu.cs544.userservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(ServiceRequest serviceRequest) {
        if(serviceRequest.getEntity().equals("User")) {
            ObjectMapper mapper = new ObjectMapper();

            if(serviceRequest.getAction().equals("create")) {
                User user = mapper.convertValue(serviceRequest.getObject(), new TypeReference<User>() { });
                userService.save(user);
            }else if(serviceRequest.getAction().equals("update")) {
                User user = mapper.convertValue(serviceRequest.getObject(), new TypeReference<User>() { });
                userService.update(user);
            } else if(serviceRequest.getAction().equals("delete")) {
                Long id = mapper.convertValue(serviceRequest.getObject(), new TypeReference<Long>() { });
                userService.deleteById(id);
            }
        }
    }
}
