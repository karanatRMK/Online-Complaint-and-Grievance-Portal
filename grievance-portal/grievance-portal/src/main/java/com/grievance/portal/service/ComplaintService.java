package com.grievance.portal.service;

import com.grievance.portal.entity.Complaint;
import java.util.List;


public interface ComplaintService {

    Complaint save(Complaint complaint);

    List<Complaint> getAllComplaints();

    Complaint updateStatus(Long id, String status);

    void deleteById(Long id);

    List<Complaint> getComplaintsByUser(Long userId);

}
