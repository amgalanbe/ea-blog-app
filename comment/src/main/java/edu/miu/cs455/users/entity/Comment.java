package edu.miu.cs455.users.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment{
    @Id
    private Long id;
    @Lob
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Post post;
}