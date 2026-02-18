package com.bharath.leave.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="employees", indexes=@Index(name="idx_employee_email", columnList="email"))
public class Employee {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String name;
 @Email @NotBlank @Column(unique=true) private String email;
 @NotBlank private String role;
 public Employee() {}
 public Employee(String name,String email,String role){this.name=name;this.email=email;this.role=role;}
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getRole(){return role;} public void setRole(String v){role=v;}
}
