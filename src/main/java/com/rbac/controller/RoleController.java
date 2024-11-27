package com.rbac.controller;

import com.rbac.entity.Role;
import com.rbac.entity.RoleName;
import com.rbac.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    // Endpoint to add a new role (USER or ADMIN)
    @PostMapping("/add")
    public String addRole(@RequestBody Role role) {
        // Check if role already exists
        if (roleRepository.findByRoleName(role.getRoleName()).isPresent()) {
            return "Role already exists!";
        }

        // Save the new role
        roleRepository.save(role);
        return "Role added successfully!";
    }

    // Endpoint to get all roles
    @GetMapping("/all")
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
