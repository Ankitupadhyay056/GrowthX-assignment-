package com.task.GrowthX.Modals;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "assignments")
public class Assignment {
    @Id
    private String id;
    private String userId;        // ID of the user who uploaded the assignment
    private String adminUsername; // Username of the admin handling the assignment
    private String title;
    private String description;
    private String status;        // "PENDING", "ACCEPTED", "REJECTED"
}
