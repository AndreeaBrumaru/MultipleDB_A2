package com.example.multipledm_a2.secondary;

import com.example.multipledm_a2.primary.Employee;
import com.example.multipledm_a2.primary.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    //Add a manager
    public void add(Manager manager)
    {
        managerRepository.save(manager);
    }

    //Find by id
    public Manager findById(Long id)
    {
        return managerRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    //Find all
    public Iterable<Manager> findAll()
    {
        return managerRepository.findAll();
    }

    //Update
    public void update(Long id, Manager updatedInfo)
    {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new RuntimeException());
        manager.setName(updatedInfo.getName());
        manager.setSalary(updatedInfo.getSalary());
        managerRepository.save(manager);
    }

    //Delete
    public void delete(Long id)
    {
        managerRepository.deleteById(id);
    }
}
