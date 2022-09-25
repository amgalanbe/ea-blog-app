package edu.miu.cs544.BlogApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
}