package edu.miu.cs544.BlogApplication.services.Impl;

import edu.miu.cs544.BlogApplication.config.MessagingConfig;
import edu.miu.cs544.BlogApplication.dto.PostDto;
import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.model.ServiceRequest;
import edu.miu.cs544.BlogApplication.services.PostService;
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
public class PostServiceImpl implements PostService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    private long next = 6;
    private final String post_url = "http://localhost:8081/posts";
    @Override
    public long save(Post post) {
        Long newId = next++;
        post.setId(newId);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.POST_ROUTING_KEY,
                new ServiceRequest("Post", "create", post));
        return newId;
    }

    @Override
    public List<PostDto> findAll() {
        ResponseEntity<List<Post>> response = restTemplate.exchange(post_url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
        List<Post> posts = response.getBody();
        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findAllByUsername(String username) {
        ResponseEntity<List<Post>> response = restTemplate.exchange(post_url + "/user/" + username, HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {});
        List<Post> posts = response.getBody();
        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public Post findById(long id) {
        return restTemplate.getForObject(post_url + "/{id}", Post.class, id);
    }


    @Override
    public void update(Post post) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.POST_ROUTING_KEY,
                new ServiceRequest("Post", "update", post));
    }

    @Override
    public void deleteById(long id) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.POST_ROUTING_KEY,
                new ServiceRequest("Post", "delete", id));
    }
}
