package edu.miu.cs544.BlogApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    private String entity; //Post, Comment, User
    private String action; //create, update, delete
    private Object object; //post, comment, user, id
    private Long userId; //current logged in user id
}
