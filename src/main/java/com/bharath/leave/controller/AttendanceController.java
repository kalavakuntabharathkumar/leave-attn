package com.bharath.leave.controller;
import com.bharath.leave.model.*; import com.bharath.leave.repository.*;
import org.springframework.web.bind.annotation.*; import java.time.LocalDate; import java.util.*;
@RestController @RequestMapping("/api/attendance")
public class AttendanceController {
 private final AttendanceRepository repo; private final EmployeeRepository employees;
 public AttendanceController(AttendanceRepository r,EmployeeRepository e){repo=r;employees=e;}
 @GetMapping("/{employeeId}") public List<Attendance> range(@PathVariable Long employeeId,@RequestParam LocalDate from,@RequestParam LocalDate to){return repo.findByEmployeeIdAndWorkDateBetween(employeeId,from,to);}
 @PostMapping public Attendance mark(@RequestBody Attendance a){Employee e=employees.findById(a.getEmployee().getId()).orElseThrow();a.setEmployee(e);return repo.save(a);}
}
