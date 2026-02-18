package com.bharath.leave.repository;
import com.bharath.leave.model.LeaveBalance; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long>{ Optional<LeaveBalance> findByEmployeeId(Long employeeId); }
