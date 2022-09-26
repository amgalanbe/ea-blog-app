package edu.miu.cs544.BlogApplication.services.Impl;

import edu.miu.cs544.BlogApplication.config.MessagingConfig;
import edu.miu.cs544.BlogApplication.entity.Comment;
import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.model.ServiceRequest;
import edu.miu.cs544.BlogApplication.services.CommentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final String comment_url = "http://localhost:8082/comments";

    private long next = 6;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public long save(Comment comment) {
        Long newId = next++;
        comment.setId(newId);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.COMMENT_ROUTING_KEY,
                new ServiceRequest("Comment", "create", comment));
        return newId;
    }

    @Override
    public List<Comment> findAll() {
        ResponseEntity<List<Comment>> response = restTemplate.exchange(comment_url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {});
        return response.getBody();
    }

    @Override
    public Comment findById(long id) {
        return restTemplate.getForObject(comment_url + "/{id}", Comment.class, id);
    }

    @Override
    public void update(Comment comment) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.COMMENT_ROUTING_KEY,
                new ServiceRequest("Comment", "udpate", comment));
    }

    @Override
    public void deleteById(long id) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.COMMENT_ROUTING_KEY,
                new ServiceRequest("Comment", "delete", id));
    }
}
