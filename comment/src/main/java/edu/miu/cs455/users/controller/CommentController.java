package edu.miu.cs455.users.controller;

import edu.miu.cs455.users.entity.Comment;
import edu.miu.cs455.users.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/comments/{Id}")
    public Comment getComment(@PathVariable Long Id){
        return commentService.findById(Id);
    }

    @GetMapping("/comments")
    public List<Comment> getComments(){
        return commentService.findAll();
    }

    @PostMapping("/comments/post/{id}")
    public RedirectView addComment(@RequestBody Comment comment, @PathVariable Long id){
        Long savedId = commentService.save(comment, id).getId();
         return new RedirectView("/comments/"+savedId);
    }

    @PutMapping("/comments/update")
    public Comment updateComment(@RequestBody Comment comment){
        return commentService.update(comment);
    }

    @DeleteMapping("/comments/delete/{id}")
    public void deleteComment(@PathVariable long id){
        commentService.deleteById(id);
    }
}
