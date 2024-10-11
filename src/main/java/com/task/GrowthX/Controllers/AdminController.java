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
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentService assignmentService;

    // Register a new admin
    @PostMapping("/register")
    public User registerAdmin(@RequestBody User admin) {
        admin.setRole("ADMIN");
        return userService.createUser(admin);
    }

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (authenticated) {
            return ResponseEntity.ok("Admin login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // View assignments tagged to the admin
    @GetMapping("/assignments/{adminUsername}")
    public ResponseEntity<List<Assignment>> getAdminAssignments(@PathVariable String adminUsername) {
        List<Assignment> assignments = assignmentService.getAssignmentsByAdminUsername(adminUsername);

        if (assignments.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok(assignments);
    }
    // Accept an assignment
    @PostMapping("/assignments/{id}/accept")
    public Assignment acceptAssignment(@PathVariable String id) {
        return assignmentService.updateStatus(id, "ACCEPTED");
    }

    // Reject an assignment
    @PostMapping("/assignments/{id}/reject")
    public Assignment rejectAssignment(@PathVariable String id) {
        return assignmentService.updateStatus(id, "REJECTED");
    }
}
