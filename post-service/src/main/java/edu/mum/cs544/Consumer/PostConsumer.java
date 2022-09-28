package edu.mum.cs544.Consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs544.Entity.Post;
import edu.mum.cs544.MessagingConfig;
import edu.mum.cs544.Model.ServiceRequest;
import edu.mum.cs544.Service.PostService;
import edu.mum.cs544.dao.IUserDAO;
import edu.mum.cs544.Entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConsumer {
    @Autowired
    PostService postservice;
    @Autowired
    IUserDAO userDAO;

    @RabbitListener(queues= MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(ServiceRequest servicerequest){
        ObjectMapper om = new ObjectMapper();
        User user = userDAO.findUserByUsername(servicerequest.getUsername()).orElse(null);

        if(servicerequest.getAction().equals("create")){
            Post post = om.convertValue(servicerequest.getObject(), new TypeReference<Post>() { });
           // post.setUser(user);
            postservice.save(post, user.getUsername());//, postRequest.getPost());
        }

        else if(servicerequest.getAction().equals("update")){
            Post post = om.convertValue(servicerequest.getObject(), new TypeReference<Post>() { });
            postservice.update(post);
            System.out.println("Message received from queue : "+servicerequest.getAction());
        }
        else if(servicerequest.getAction().equals("delete")){
            Post post = om.convertValue(servicerequest.getObject(), new TypeReference<Post>() { });
            postservice.deleteById(post.getId());
        }
        System.out.println("The post was received from queue " +servicerequest );
    }
}
