package com.bharath.leave.controller;
import com.bharath.leave.model.*; import com.bharath.leave.repository.*;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.temporal.ChronoUnit; import java.util.*;
@RestController @RequestMapping("/api/leaves")
public class LeaveController {
 private final LeaveRequestRepository leaves; private final EmployeeRepository employees; private final LeaveBalanceRepository balances;
 public LeaveController(LeaveRequestRepository l,EmployeeRepository e,LeaveBalanceRepository b){leaves=l;employees=e;balances=b;}
 @GetMapping public List<LeaveRequest> all(@RequestParam(required=false) LeaveRequest.Status status){return status==null?leaves.findAll():leaves.findByStatus(status);}
 @PostMapping public ResponseEntity<?> apply(@RequestBody LeaveRequest req){
  if(req.getEmployee()==null||req.getEmployee().getId()==null||req.getStartDate()==null||req.getEndDate()==null||req.getStartDate().isAfter(req.getEndDate()))
   return ResponseEntity.badRequest().body("Valid employee and date range are required");
  Employee e=employees.findById(req.getEmployee().getId()).orElse(null); if(e==null)return ResponseEntity.badRequest().body("Employee not found");
  req.setEmployee(e); long days=ChronoUnit.DAYS.between(req.getStartDate(),req.getEndDate())+1;
  LeaveBalance b=balances.findByEmployeeId(e.getId()).orElseGet(()->{LeaveBalance x=new LeaveBalance();x.setEmployee(e);return x;});
  if(b.getUsedDays()+days>b.getTotalDays())return ResponseEntity.badRequest().body("Insufficient leave balance");
  return ResponseEntity.status(HttpStatus.CREATED).body(leaves.save(req));
 }
 @PatchMapping("/{id}/approve") public ResponseEntity<?> approve(@PathVariable Long id,@RequestParam String approverRole){
  if(!Set.of("MANAGER","HR").contains(approverRole.toUpperCase())) return ResponseEntity.status(403).body("Manager or HR approval required");
  return leaves.findById(id).map(x->{if(x.getStatus()!=LeaveRequest.Status.PENDING)return ResponseEntity.badRequest().body("Request already processed");
   x.setStatus(LeaveRequest.Status.APPROVED); LeaveBalance b=balances.findByEmployeeId(x.getEmployee().getId()).orElseThrow(); int d=(int)(ChronoUnit.DAYS.between(x.getStartDate(),x.getEndDate())+1);b.setUsedDays(b.getUsedDays()+d);balances.save(b);return ResponseEntity.ok(leaves.save(x));}).orElse(ResponseEntity.notFound().build());
 }
 @PatchMapping("/{id}/reject") public ResponseEntity<?> reject(@PathVariable Long id,@RequestParam String approverRole){
  if(!Set.of("MANAGER","HR").contains(approverRole.toUpperCase())) return ResponseEntity.status(403).body("Manager or HR approval required");
  return leaves.findById(id).map(x->{x.setStatus(LeaveRequest.Status.REJECTED);return ResponseEntity.ok(leaves.save(x));}).orElse(ResponseEntity.notFound().build());
 }
}
