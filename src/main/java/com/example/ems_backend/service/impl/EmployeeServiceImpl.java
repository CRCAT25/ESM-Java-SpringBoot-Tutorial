package com.example.ems_backend.service.impl;

import com.example.ems_backend.dto.EmployeeDTO;
import com.example.ems_backend.entity.Employee;
import com.example.ems_backend.mapper.EmployeeMapper;
import com.example.ems_backend.repository.EmployeeRepository;
import com.example.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
    Employee foundEmployee = employeeRepository
                                .findById(employeeId)
                                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDTO(foundEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees
                .stream()
                .map(EmployeeMapper::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployeeDTO) {
        Employee employee = employeeRepository
                            .findById(employeeId)
                            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        employee.setFirstName(updatedEmployeeDTO.getFirstName());
        employee.setLastName(updatedEmployeeDTO.getLastName());
        employee.setEmail(updatedEmployeeDTO.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        employeeRepository.delete(employee);
    }
}
