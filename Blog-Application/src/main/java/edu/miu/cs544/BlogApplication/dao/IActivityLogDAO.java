package edu.miu.cs544.BlogApplication.dao;

import edu.miu.cs544.BlogApplication.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActivityLogDAO extends JpaRepository<ActivityLog, Long> {
}
