package edu.mum.cs544.Controller;

import edu.mum.cs544.Entity.Post;
import edu.mum.cs544.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

//    @PostMapping("")
//    public Post save(@RequestBody Post post, @PathVariable String ) {return postService.save(post);}

    @PostMapping("/{username}")
    public RedirectView addPost(@RequestBody Post post, @PathVariable String username){
        String saveduser = postService.save(post, username).getUser().getUsername();
        return new RedirectView("/posts/" + saveduser);
    }

    @GetMapping("")
    public List<Post> findAll() {return postService.findAll();}

    @GetMapping("/{id}")
    public Optional<Post> getPost(@PathVariable long id) {return postService.findById(id);}

    @GetMapping("/user/{username}")
    public List<Post> getUserPosts(@PathVariable String username){
        return postService.getUserPosts(username);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Post post) {postService.update(post);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {postService.deleteById(id);}

}


