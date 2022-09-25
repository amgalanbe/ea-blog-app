package edu.miu.cs544.BlogApplication.controller;

import edu.miu.cs544.BlogApplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

}
