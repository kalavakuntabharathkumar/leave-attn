package com.bharath.leave.controller;
import com.bharath.leave.model.Employee; import com.bharath.leave.repository.EmployeeRepository;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/employees")
public class EmployeeController {
 private final EmployeeRepository repo; public EmployeeController(EmployeeRepository r){repo=r;}
 @GetMapping public List<Employee> all(){return repo.findAll();}
 @GetMapping("/{id}") public ResponseEntity<Employee> one(@PathVariable Long id){return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
 @PostMapping public ResponseEntity<?> create(@Valid @RequestBody Employee e){if(repo.existsByEmail(e.getEmail())) return ResponseEntity.badRequest().body("Email already exists"); return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(e));}
 @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Employee e){return repo.findById(id).map(x->{x.setName(e.getName());x.setEmail(e.getEmail());x.setRole(e.getRole());return ResponseEntity.ok(repo.save(x));}).orElse(ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
