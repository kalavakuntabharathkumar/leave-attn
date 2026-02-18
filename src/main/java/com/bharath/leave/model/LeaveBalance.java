package com.bharath.leave.model;
import jakarta.persistence.*;
@Entity @Table(name="leave_balances", uniqueConstraints=@UniqueConstraint(columnNames="employee_id"))
public class LeaveBalance {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @OneToOne(optional=false) @JoinColumn(name="employee_id") private Employee employee;
 private int totalDays=24; private int usedDays=0;
 public LeaveBalance(){}
 public Long getId(){return id;} public Employee getEmployee(){return employee;} public void setEmployee(Employee e){employee=e;}
 public int getTotalDays(){return totalDays;} public void setTotalDays(int v){totalDays=v;}
 public int getUsedDays(){return usedDays;} public void setUsedDays(int v){usedDays=v;}
}
