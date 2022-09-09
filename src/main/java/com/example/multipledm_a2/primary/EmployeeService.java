package com.example.multipledm_a2.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Add an employee
    public void add(Employee employee)
    {
        employeeRepository.save(employee);
    }

    //Find by id
    public Employee findById(Long id)
    {
        return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    //Find all
    public Iterable<Employee> findAll()
    {
        return employeeRepository.findAll();
    }

    //Update
    public void update(Long id, Employee updatedInfo)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException());
        employee.setName(updatedInfo.getName());
        employee.setSalary(updatedInfo.getSalary());
        employeeRepository.save(employee);
    }

    //Delete
    public void delete(Long id)
    {
        employeeRepository.deleteById(id);
    }
}
