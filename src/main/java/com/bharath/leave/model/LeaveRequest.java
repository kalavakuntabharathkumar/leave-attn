package com.bharath.leave.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="leave_requests", indexes=@Index(name="idx_leave_employee_status", columnList="employee_id,status"))
public class LeaveRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="employee_id") private Employee employee;
 private LocalDate startDate, endDate;
 @Enumerated(EnumType.STRING) private Status status=Status.PENDING;
 private String reason;
 public enum Status { PENDING, APPROVED, REJECTED }
 public LeaveRequest(){}
 public Long getId(){return id;} public Employee getEmployee(){return employee;} public void setEmployee(Employee e){employee=e;}
 public LocalDate getStartDate(){return startDate;} public void setStartDate(LocalDate d){startDate=d;}
 public LocalDate getEndDate(){return endDate;} public void setEndDate(LocalDate d){endDate=d;}
 public Status getStatus(){return status;} public void setStatus(Status s){status=s;}
 public String getReason(){return reason;} public void setReason(String r){reason=r;}
}
