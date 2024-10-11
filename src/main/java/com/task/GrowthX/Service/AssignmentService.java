package com.task.GrowthX.Service;

import com.task.GrowthX.Modals.Assignment;
import com.task.GrowthX.Repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    public Assignment submitAssignment(Assignment assignment) {
        assignment.setStatus("PENDING");
        return assignmentRepository.save(assignment);
    }

    public Assignment updateStatus(String assignmentId, String status) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow();
        assignment.setStatus(status);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsByAdminUsername(String adminUsername) {
        return assignmentRepository.findByAdminUsername(adminUsername);
    }

    public List<Assignment> getUserAssignments(String userId) {
        return assignmentRepository.findByUserId(userId);
    }
}
