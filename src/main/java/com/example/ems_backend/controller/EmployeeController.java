package com.example.ems_backend.controller;

import com.example.ems_backend.dto.EmployeeDTO;
import com.example.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    // Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build API Get employee by id with GET method
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDTO foundEmployee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(foundEmployee);
    }

    // Build API Get employee by id with POST method
    @PostMapping("/getEmployeeById")
    public ResponseEntity<EmployeeDTO> getEmployeeByIdWithPost(@RequestBody Long employeeId) {
        EmployeeDTO foundEmployee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(foundEmployee);
    }

    // Build API Get all employees with GET method
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Build API Update employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDTO updatedEmployeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, updatedEmployeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Build API Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
