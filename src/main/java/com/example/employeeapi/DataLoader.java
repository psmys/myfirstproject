package com.example.employeeapi;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final EmployeeRepository repo;

    public DataLoader(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repo.count() > 0) return;

        List<Employee> list = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            list.add(new Employee(
                    "First" + i,
                    "Last" + i,
                    "emp" + i + "@example.com",
                    (i % 3 == 0) ? "Engineering" : (i % 3 == 1) ? "Sales" : "HR",
                    40000 + i * 1500
            ));
        }

        repo.saveAll(list);
        System.out.println("Inserted " + repo.count() + " employees.");
    }
}
