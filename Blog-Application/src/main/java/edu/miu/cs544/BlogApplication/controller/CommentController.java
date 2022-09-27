package edu.miu.cs544.BlogApplication.controller;

import edu.miu.cs544.BlogApplication.dto.CommentDto;
import edu.miu.cs544.BlogApplication.entity.Comment;
import edu.miu.cs544.BlogApplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/post/{id}")
    public Object save(@RequestBody Comment comment, @PathVariable long id) {
        Long savedCommentId = commentService.save(comment, id);
        return savedCommentId != null ? new RedirectView("/api/comments/" + savedCommentId)
                : ResponseEntity.accepted().body("Failed to create a comment. Comment with this id does not exists");
    }

    @GetMapping("")
    public List<CommentDto> getAll() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        Comment comment = commentService.findById(id);
        if(comment != null)
            return ResponseEntity.accepted().body(modelMapper.map(comment, CommentDto.class));
        return ResponseEntity.accepted().body("Comment with id " + id + " does not exists");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable long id, @RequestBody Comment comment) {

        Comment existingComment = commentService.findById(id);
        if(existingComment == null)
            return ResponseEntity.accepted().body("Comment with Id: " + id + " does not exist.");

        if(existingComment.getUser().getUsername() != ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())
            return ResponseEntity.accepted().body("User is not authorized to update a comment with Id: " + id);

        comment.setId(id);
        commentService.update(comment);
//        return new RedirectView("api/posts/" + id);
        return ResponseEntity.accepted().body("Comment has successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Comment comment = commentService.findById(id);
        if(comment == null)
            return ResponseEntity.accepted().body("Comment with Id: " + id + " does not exist.");

        if(comment.getUser().getUsername() != ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())
            return ResponseEntity.accepted().body("User is not authorized to delete a post with Id: " + id);

        commentService.deleteById(id);
        return ResponseEntity.accepted().body("Post has successfully deleted");
    }

}
