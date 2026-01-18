package com.grievance.portal.repository;
import java.time.LocalDateTime;
import java.util.*;

import com.grievance.portal.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    long countByStatus(String status);

    long countByUserId(Long userId);

    long countByUserIdAndStatus(Long userId, String status);

    List<Complaint> findByUserId(Long userId);

    List<Complaint> findByCreatedAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<Complaint> findByCategoryAndCreatedAtBetween(
            String category,
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("""
        SELECT c.category, COUNT(c)
        FROM Complaint c
        GROUP BY c.category
    """)
    List<Object[]> countByCategory();

    @Query("""
        SELECT c.status, COUNT(c)
        FROM Complaint c
        GROUP BY c.status
    """)
    List<Object[]> countByStatus();
}
