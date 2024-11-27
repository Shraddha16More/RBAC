package com.rbac.repository;

import com.rbac.entity.Role;
import com.rbac.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);

    Optional<Object> findByName(RoleName user);
}
