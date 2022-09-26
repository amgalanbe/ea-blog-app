package edu.miu.cs544.userservice.dao;

import edu.miu.cs544.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDAO extends JpaRepository<Role, Long> {
    public Role findRoleByRole(String role);
}
