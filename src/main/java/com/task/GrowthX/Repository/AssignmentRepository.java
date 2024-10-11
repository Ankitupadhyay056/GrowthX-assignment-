package com.task.GrowthX.Repository;

import com.task.GrowthX.Modals.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    List<Assignment> findByUserId(String userId);
    List<Assignment> findByAdminUsername(String adminUsername); // New method
}
