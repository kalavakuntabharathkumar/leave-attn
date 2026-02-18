package com.bharath.leave.model;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="attendance", uniqueConstraints=@UniqueConstraint(name="uk_attendance_employee_date",columnNames={"employee_id","work_date"}))
public class Attendance {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="employee_id") private Employee employee;
 @Column(name="work_date") private LocalDate workDate;
 private boolean present;
 public Attendance(){}
 public Long getId(){return id;} public Employee getEmployee(){return employee;} public void setEmployee(Employee e){employee=e;}
 public LocalDate getWorkDate(){return workDate;} public void setWorkDate(LocalDate d){workDate=d;}
 public boolean isPresent(){return present;} public void setPresent(boolean p){present=p;}
}
