package com.grievance.portal.controller;

import com.grievance.portal.entity.Complaint;
import com.grievance.portal.repository.ComplaintRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/admin/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminReportController {

    @Autowired
    private ComplaintRepository complaintRepository;

    // ============================
    // CSV EXPORT
    // ============================
    @GetMapping("/export/csv")
    public void exportComplaintsCSV(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam(required = false)
            String category,

            HttpServletResponse response
    ) throws IOException {

        // Convert dates to datetime range
        LocalDateTime startDateTime =
                startDate != null ? startDate.atStartOfDay() : LocalDateTime.MIN;

        LocalDateTime endDateTime =
                endDate != null ? endDate.atTime(LocalTime.MAX) : LocalDateTime.MAX;

        List<Complaint> complaints;

        // Filter logic
        if (category != null && !category.isEmpty()) {
            complaints = complaintRepository
                    .findByCategoryAndCreatedAtBetween(category, startDateTime, endDateTime);
        } else {
            complaints = complaintRepository
                    .findByCreatedAtBetween(startDateTime, endDateTime);
        }

        // Response headers
        response.setContentType("text/csv");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=complaints_report.csv"
        );

        PrintWriter writer = response.getWriter();

        // CSV Header
        writer.println("ComplaintID,Title,Category,Status,User,Email,CreatedAt");

        // CSV Rows
        for (Complaint c : complaints) {
            writer.printf(
                    "%d,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                    c.getId(),
                    c.getTitle(),
                    c.getCategory(),
                    c.getStatus(),
                    c.getUser() != null ? c.getUser().getName() : "Anonymous",
                    c.getUser() != null ? c.getUser().getEmail() : "-",
                    c.getCreatedAt()
            );
        }

        writer.flush();
        writer.close();
    }
}
