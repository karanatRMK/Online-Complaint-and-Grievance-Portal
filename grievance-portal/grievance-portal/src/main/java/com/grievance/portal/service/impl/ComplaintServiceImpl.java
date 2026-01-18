package com.grievance.portal.service.impl;

import com.grievance.portal.entity.Complaint;
import com.grievance.portal.repository.ComplaintRepository;
import com.grievance.portal.service.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository repo;

    @Override
    public Complaint save(Complaint complaint) {
        return repo.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }

    @Override
    public Complaint updateStatus(Long id, String status) {
        Complaint complaint = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(status);
        return repo.save(complaint);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Complaint> getComplaintsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

}

