package edu.miu.cs544.BlogApplication.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String className;
    private String methodName;
    private Long executedTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedAt;
    @CreatedBy
    private String executedBy;
}
