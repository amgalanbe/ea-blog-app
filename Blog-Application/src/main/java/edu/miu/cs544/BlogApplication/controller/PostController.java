package edu.miu.cs544.BlogApplication.controller;

import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("")
    public RedirectView save(@RequestBody Post post) {
        long savedPostId = postService.save(post);
        return new RedirectView("api/v1/posts/" + savedPostId);
    }

    @GetMapping("")
    public List<Post> getAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Post post) {

        if(postService.findById(id) == null)
            return ResponseEntity.accepted().body("Post with Id: " + id + " does not exist.");

        post.setId(id);
        postService.update(post);

        return ResponseEntity.accepted().body("Post has successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        if(postService.findById(id) == null)
            return ResponseEntity.accepted().body("Post with Id: " + id + " does not exist.");

        postService.deleteById(id);

        return ResponseEntity.accepted().body("Post has successfully deleted");
    }
}
