package com.task.GrowthX.Controllers;

import com.task.GrowthX.Modals.Assignment;
import com.task.GrowthX.Modals.LoginRequest;
import com.task.GrowthX.Modals.User;
import com.task.GrowthX.Service.AssignmentService;
import com.task.GrowthX.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentService assignmentService;

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        user.setRole("USER");
        return userService.createUser(user);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (authenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Upload an assignment with adminUsername verification
    @PostMapping("/upload")
    public ResponseEntity<?> uploadAssignment(@RequestBody Assignment assignment,
                                              @RequestParam String userId,
                                              @RequestParam String adminUsername) {
        // Check if the provided adminUsername exists and is an admin
        User admin = userService.findByUsername(adminUsername);
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return ResponseEntity.status(404).body("Admin with username " + adminUsername + " not found.");
        }

        // Proceed to submit the assignment if the admin exists
        assignment.setUserId(userId);
        assignment.setAdminUsername(adminUsername);
        assignment.setStatus("PENDING");
        Assignment submittedAssignment = assignmentService.submitAssignment(assignment);
        return ResponseEntity.ok(submittedAssignment);
    }

    // Fetch all admins
    @GetMapping("/admins")
    public List<User> fetchAllAdmins() {
        return userService.getUsersByRole("ADMIN");
    }
}
