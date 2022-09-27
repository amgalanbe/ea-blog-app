package edu.miu.cs544.BlogApplication.services.Impl;

import edu.miu.cs544.BlogApplication.config.MessagingConfig;
import edu.miu.cs544.BlogApplication.dto.CommentDto;
import edu.miu.cs544.BlogApplication.entity.Comment;
import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.model.CommentRequest;
import edu.miu.cs544.BlogApplication.model.ServiceRequest;
import edu.miu.cs544.BlogApplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final String comment_url = "http://localhost:8082/comments";

    private long next = 1;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public long save(Comment comment, Long postId) {
        Long newId = next++;
        comment.setId(newId);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.COMMENT_ROUTING_KEY,
                new ServiceRequest("Comment", "create", new CommentRequest(postId, modelMapper.map(comment, CommentDto.class))));
        return newId;
    }

    @Override
    public List<CommentDto> findAll() {
        ResponseEntity<List<Comment>> response = restTemplate.exchange(comment_url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {});
        List<Comment> comments = response.getBody();
        return comments.stream().map(c -> modelMapper.map(c, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public Comment findById(long id) {
        Comment comment = restTemplate.getForObject(comment_url + "/{id}", Comment.class, id);
        return comment;
    }

    @Override
    public void update(Comment comment) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.COMMENT_ROUTING_KEY,
                new ServiceRequest("Comment", "update", modelMapper.map(comment, CommentDto.class)));
    }

    @Override
    public void deleteById(long id) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.COMMENT_ROUTING_KEY,
                new ServiceRequest("Comment", "delete", id));
    }
}
