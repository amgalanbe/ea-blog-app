package edu.miu.cs544.BlogApplication.controller;

import edu.miu.cs544.BlogApplication.entity.Post;
import edu.miu.cs544.BlogApplication.services.Impl.UaaServiceImpl;
import edu.miu.cs544.BlogApplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("")
    public RedirectView save(@RequestBody Post post) {
        long savedPostId = postService.save(post);
        return new RedirectView("api/posts/" + savedPostId);
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

        Post existingPost = postService.findById(id);
        if(existingPost == null)
            return ResponseEntity.accepted().body("Post with Id: " + id + " does not exist.");

        if(existingPost.getUser().getId() != UaaServiceImpl.currentUser.getId())
            return ResponseEntity.accepted().body("User is not authorized to update a post with Id: " + id);

        post.setId(id);
        postService.update(post);
//        return new RedirectView("api/posts/" + id);
        return ResponseEntity.accepted().body("Post has successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Post post = postService.findById(id);
        if(post == null)
            return ResponseEntity.accepted().body("Post with Id: " + id + " does not exist.");

        if(post.getUser().getId() != UaaServiceImpl.currentUser.getId())
            return ResponseEntity.accepted().body("User is not authorized to delete a post with Id: " + id);

        postService.deleteById(id);
        return ResponseEntity.accepted().body("Post has successfully deleted");
    }
}
