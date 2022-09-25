package edu.miu.cs544.BlogApplication.dao;

import edu.miu.cs544.BlogApplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDAO extends JpaRepository<Role, Long> {
    public Role findByRole(String role);
}
