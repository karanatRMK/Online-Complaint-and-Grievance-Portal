package com.grievance.portal.controller;

import com.grievance.portal.dto.DashboardStats;
import com.grievance.portal.repository.ComplaintRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final ComplaintRepository repo;

    public UserController(ComplaintRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/dashboard/{userId}")
    public DashboardStats getUserStats(@PathVariable Long userId) {

        long total = repo.countByUserId(userId);

        long pending =
                repo.countByUserIdAndStatus(userId, "Submitted") +
                        repo.countByUserIdAndStatus(userId, "In Progress");

        long resolved =
                repo.countByUserIdAndStatus(userId, "Resolved");

        return new DashboardStats(total, pending, resolved, 0);
    }

}
