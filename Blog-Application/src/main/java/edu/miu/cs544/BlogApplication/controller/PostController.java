package edu.miu.cs544.BlogApplication.controller;

import edu.miu.cs544.BlogApplication.dto.CommentDto;
import edu.miu.cs544.BlogApplication.dto.PostDto;
import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.services.Impl.UaaServiceImpl;
import edu.miu.cs544.BlogApplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("")
    public Object save(@RequestBody Post post) {
        Long savedPostId = postService.save(post);
        return savedPostId != null ? new RedirectView("/api/posts/" + savedPostId)
                : ResponseEntity.accepted().body("Failed to create a post. Post with this id does not exists");
    }

    @GetMapping("")
    public List<PostDto> getAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        Post post = postService.findById(id);
        if(post != null)
            return ResponseEntity.accepted().body(modelMapper.map(post, PostDto.class));
        return ResponseEntity.accepted().body("Post with id " + id + " does not exists");
    }

    @GetMapping("/user/{username}")
    public List<PostDto> getById(@PathVariable String username) {
        return postService.findAllByUsername(username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Post post) {

        Post existingPost = postService.findById(id);
        if(existingPost == null)
            return ResponseEntity.accepted().body("Post with Id: " + id + " does not exist.");

        if(existingPost.getUser().getUsername().compareTo(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()) != 0)
            return ResponseEntity.accepted().body("User is not authorized to update a post with Id: " + id);

        post.setId(id);
        postService.update(post);
        return ResponseEntity.accepted().body("Post has successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Post post = postService.findById(id);
        if(post == null)
            return ResponseEntity.accepted().body("Post with Id: " + id + " does not exist.");

        if(post.getUser().getUsername().compareTo(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()) != 0)
            return ResponseEntity.accepted().body("User is not authorized to delete a post with Id: " + id);

        postService.deleteById(id);
        return ResponseEntity.accepted().body("Post with id " + id + " has successfully deleted");
    }
}
