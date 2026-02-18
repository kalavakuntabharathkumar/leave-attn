package com.bharath.leave.repository;
import com.bharath.leave.model.LeaveRequest; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long>{
 List<LeaveRequest> findByStatus(LeaveRequest.Status status);
 long countByEmployeeIdAndStatus(Long employeeId, LeaveRequest.Status status);
}
