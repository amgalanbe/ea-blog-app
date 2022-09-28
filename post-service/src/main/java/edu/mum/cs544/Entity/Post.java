package edu.mum.cs544.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Collection<Comment> comments;
    @ManyToOne
    private User user;
}