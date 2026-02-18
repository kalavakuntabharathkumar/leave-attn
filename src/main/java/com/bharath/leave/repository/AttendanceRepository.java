package com.bharath.leave.repository;
import com.bharath.leave.model.Attendance; import org.springframework.data.jpa.repository.JpaRepository; import java.time.LocalDate; import java.util.*;
public interface AttendanceRepository extends JpaRepository<Attendance,Long>{ List<Attendance> findByEmployeeIdAndWorkDateBetween(Long id,LocalDate from,LocalDate to); }
