package com.example.employeeapi.service;

import com.example.employeeapi.exception.EmployeeNotFoundException;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee create(Employee e) {
        return repo.save(e);
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee update(Long id, Employee incoming) {
        Employee existing = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        existing.setFirstName(incoming.getFirstName());
        existing.setLastName(incoming.getLastName());
        existing.setEmail(incoming.getEmail());
        existing.setPosition(incoming.getPosition());
        existing.setSalary(incoming.getSalary());
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new EmployeeNotFoundException(id);
        repo.deleteById(id);
    }
}
