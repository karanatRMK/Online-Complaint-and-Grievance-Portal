package com.grievance.portal.controller;

import com.grievance.portal.dto.DashboardStats;
import com.grievance.portal.repository.ComplaintRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    private final ComplaintRepository repo;

    public AdminController(ComplaintRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/dashboard")
    public DashboardStats getAdminStats() {
        return new DashboardStats(
                repo.count(),
                repo.countByStatus("Submitted") + repo.countByStatus("In Progress"),
                repo.countByStatus("Resolved"),
                repo.countByStatus("Escalated")
        );
    }
}
