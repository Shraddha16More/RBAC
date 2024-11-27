package com.rbac.controller;


import com.rbac.entity.Role;
import com.rbac.entity.RoleName;
import com.rbac.entity.User;
import com.rbac.repository.RoleRepository;
import com.rbac.repository.UserRepository;
import com.rbac.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }



    // TODO: Save a new user
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody @jakarta.validation.Valid User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User saved successfully!");
    }

    //    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return ResponseEntity.ok("User registered successfully");
//    }



    //    TODO: register an user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Object> userRole = roleRepository.findByName(RoleName.USER); // Get the Role from the RoleName
        if (userRole.isPresent()) {
            user.setRoles(Collections.singleton((Role) userRole.get())); // Set the role for the user
        } else {
            return new ResponseEntity<>("Role not found", HttpStatus.BAD_REQUEST);
        }


        // Save the user
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User user) {
//        // Simplified for now
//        return ResponseEntity.ok("Logged in!");
//    }


    //    TODO: login for an user
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        // Validate user credentials
        Optional<User> existingUser = userRepository.findByUsername(loginRequest.getUsername());
        if (existingUser.isEmpty() ||
                !passwordEncoder.matches(loginRequest.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }

        // Generate JWT token
        User user = existingUser.get();
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toSet());
        String token = jwtTokenProvider.createToken(user.getUsername(), roles);

        return ResponseEntity.ok("Bearer " + token);
    }

    // TODO: Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
