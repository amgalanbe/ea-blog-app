package edu.miu.cs544.BlogApplication.controller;

import edu.miu.cs544.BlogApplication.entity.User;
import edu.miu.cs544.BlogApplication.model.LoginRequest;
import edu.miu.cs544.BlogApplication.services.UaaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/uaa")
public class UaaController {
    private final UaaService uaaService;

    public UaaController(UaaService uaaService) {
        this.uaaService = uaaService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return ResponseEntity.ok().body(uaaService.signup(user));
    }
}
