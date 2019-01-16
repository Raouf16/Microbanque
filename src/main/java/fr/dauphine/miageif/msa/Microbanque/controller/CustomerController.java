package fr.dauphine.miageif.msa.Microbanque.controller;

import fr.dauphine.miageif.msa.Microbanque.jparepository.CustomerRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/customer/all")
    public List<Customer> findAllCustomers()
    {
        List<Customer> customers = repository.findAll();
        return customers;
    }

    @GetMapping("/customer/{id}")
    public Customer findCustomer(@PathVariable int id)
    {
        Customer customer = repository.findById(id);
        return customer;
    }

    @PostMapping("/customer/add")
    public void addCustomer(@ModelAttribute("form") Customer customer) {
        repository.save(customer);
    }

    @DeleteMapping("/customer/{id}")
    void deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
