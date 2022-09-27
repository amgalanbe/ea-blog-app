package edu.miu.cs455.users.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs455.users.config.MessagingConfig;
import edu.miu.cs455.users.dao.IUserDAO;
import edu.miu.cs455.users.entity.Comment;
import edu.miu.cs455.users.entity.User;
import edu.miu.cs455.users.model.CommentRequest;
import edu.miu.cs455.users.model.ServiceRequest;
import edu.miu.cs455.users.service.CommentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConsumer {

    @Autowired
    CommentService commentService;

    @Autowired
    IUserDAO userDAO;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(ServiceRequest serviceRequest){
        ObjectMapper om = new ObjectMapper();
        User user = userDAO.findUserByUsername(serviceRequest.getUsername()).orElse(null);

        if(serviceRequest.getAction().equals("create")){
            CommentRequest commentRequest = om.convertValue(serviceRequest.getObject(), new TypeReference<CommentRequest>() { });
            Comment comment = commentRequest.getComment();
            comment.setUser(user);
            commentService.save(comment, commentRequest.getPostId());
        }
       else if(serviceRequest.getAction().equals("update")){
            Comment comment = om.convertValue(serviceRequest.getObject(), new TypeReference<Comment>() { });
            commentService.update(comment);
            System.out.println("Message received from queue : "+serviceRequest.getAction());
        }
        else if(serviceRequest.getAction().equals("delete")){
            Long commentId = om.convertValue(serviceRequest.getObject(), new TypeReference<Long>() { });
            commentService.deleteById(commentId);
        }

        System.out.println("Message received from queue : "+serviceRequest);
    }
}

















