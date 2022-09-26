package edu.miu.cs544.BlogApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    private String entity; //Post, Comment, User
    private String action; //create, update, delete
    private Object object; //post, comment, user, id
    private String username; //current logged in user id

    public ServiceRequest(String entity, String action, Object object){
        this.entity = entity;
        this.action = action;
        this.object = object;
        username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
