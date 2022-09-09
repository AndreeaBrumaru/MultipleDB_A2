package com.example.multipledm_a2.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable Long id)
    {
        return employeeService.findById(id);
    }

    @GetMapping("/employee")
    public Iterable<Employee> findAll()
    {
        return employeeService.findAll();
    }

    @PostMapping("/employee")
    public void add(@RequestBody Employee employee)
    {
        employeeService.add(employee);
    }

    @PutMapping("/employee/{id}")
    public void update(@PathVariable Long id, @RequestBody Employee employee)
    {
        employeeService.update(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public void delete(@PathVariable Long id)
    {
        employeeService.delete(id);
    }
}
