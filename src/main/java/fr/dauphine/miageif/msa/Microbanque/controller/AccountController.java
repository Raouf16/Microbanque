package fr.dauphine.miageif.msa.Microbanque.controller;

import fr.dauphine.miageif.msa.Microbanque.jparepository.AccountRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private Environment environment;

    @Autowired
    private AccountRepository repository;

    @GetMapping("/account/all")
    public List<Account> findAllAccounts()
    {
        List<Account> accounts = repository.findAll();
        return accounts;
    }

    @GetMapping("/account/{id}")
    public Account findAccount(@PathVariable int id)
    {
        Account account = repository.findById(id);
        return account;
    }

    @PostMapping("/account/add")
    public void addAccount(@ModelAttribute("form") Account account) {
        repository.save(account);
    }

    @DeleteMapping("/account/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
