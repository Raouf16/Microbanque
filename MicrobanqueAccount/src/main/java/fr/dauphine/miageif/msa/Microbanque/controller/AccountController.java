package fr.dauphine.miageif.msa.Microbanque.controller;

import fr.dauphine.miageif.msa.Microbanque.jparepository.AccountRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/update/{id}")
    public String updateAccount(@ModelAttribute("form") Account account) {

        if(repository.findById(account.getId()) == null){
            return "Compte non existant";
        }
        repository.deleteById(account.getId());
        repository.save(account);
        return "Compte mis à jour avec succès";

    }

    @GetMapping("/account/iban")
    public List<String> findAllIban()
    {
        List<Account> accounts = repository.findAll();
        List<String> ibanList = new ArrayList<>();

        for(Account a : accounts){
            ibanList.add(a.getIban());
        }

        return ibanList;
    }

    @PostMapping("/account/add")
    public void addAccount(@ModelAttribute("form") Account account) {
        repository.save(account);
    }

    @DeleteMapping("/account/{id}")
    void deleteAccount(@PathVariable int id) {
        repository.deleteById(id);
    }
}
