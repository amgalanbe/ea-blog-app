package edu.miu.cs455.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    private String entity; //Post, Comment, User
    private String action; //create, update, delete
    private Object object; //post, comment, user, id
    private String username; //current logged in user id
}

