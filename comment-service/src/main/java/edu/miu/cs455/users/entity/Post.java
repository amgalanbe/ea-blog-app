package edu.miu.cs455.users.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    private Long id;
    @Lob
    private String title;
    @Lob
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Collection<Comment> comments;
    @ManyToOne
    private User user;
}