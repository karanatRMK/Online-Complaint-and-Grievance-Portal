package com.grievance.portal.controller;

import com.grievance.portal.entity.Complaint;
import com.grievance.portal.entity.User;
import com.grievance.portal.repository.UserRepository;
import com.grievance.portal.service.ComplaintService;
import com.grievance.portal.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "http://localhost:3000")
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;


    // ✅ CREATE COMPLAINT
    @PostMapping
    public Complaint createComplaint(
            @RequestBody Complaint complaint,
            @RequestParam(required = false) Long userId
    ) {
        if (!complaint.isAnonymous() && userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            complaint.setUser(user);
        }

        complaint.setStatus("Submitted");
        return service.save(complaint);
    }

    // ✅ GET ALL COMPLAINTS
    @GetMapping
    public List<Complaint> getAll() {
        return service.getAllComplaints();
    }

    // ✅ UPDATE STATUS
    @PutMapping("/{id}/status")
    public Complaint updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }

    // ✅ DELETE COMPLAINT
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {
        return service.getComplaintsByUser(userId);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Complaint submitComplaintWithFile(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam String priority,
            @RequestParam boolean anonymous,
            @RequestParam(required = false) Long userId,
            @RequestPart(required = false) MultipartFile file
    ) {
        Complaint complaint = new Complaint();
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setCategory(category);
        complaint.setPriority(priority);
        complaint.setAnonymous(anonymous);
        complaint.setStatus("Submitted");

        if (!anonymous && userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            complaint.setUser(user);
        }

        if (file != null && !file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file);
            complaint.setAttachment(fileName);
        }

        return service.save(complaint);
    }

}
