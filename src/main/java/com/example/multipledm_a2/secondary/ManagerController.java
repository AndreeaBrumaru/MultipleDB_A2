package com.example.multipledm_a2.secondary;

import com.example.multipledm_a2.primary.Employee;
import com.example.multipledm_a2.primary.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/manager/{id}")
    public Manager findById(@PathVariable Long id)
    {
        return managerService.findById(id);
    }

    @GetMapping("/manager")
    public Iterable<Manager> findAll()
    {
        return managerService.findAll();
    }

    @PostMapping("/manager")
    public void add(@RequestBody Manager manager)
    {
        managerService.add(manager);
    }

    @PutMapping("/manager/{id}")
    public void update(@PathVariable Long id, @RequestBody Manager manager)
    {
        managerService.update(id, manager);
    }

    @DeleteMapping("/manager/{id}")
    public void delete(@PathVariable Long id)
    {
        managerService.delete(id);
    }
}
