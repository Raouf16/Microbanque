package fr.dauphine.miageif.msa.Microbanque.controller;

import fr.dauphine.miageif.msa.Microbanque.jparepository.AccountRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private Environment environment;

    @Autowired
    private AccountRepository repository;

    @GetMapping("/")
    public String index(Model model) {

        return "index";
    }
    @GetMapping("/view")
    public String viewAccountList(Model model) {

        List<Account> accounts = repository.findAll();
        model.addAttribute("accounts", accounts);

        return "accountList";
    }

    @GetMapping("/view/id")
    public String viewAccount(@ModelAttribute("form") Account accountGet, Model model) {

        Account account = repository.findById(accountGet.getId());
        model.addAttribute("account", account);

        return "accountView";
    }

    @GetMapping("/account/all")
    @ResponseBody
    public List<Account> findAllAccounts()
    {
        List<Account> accounts = repository.findAll();
        return accounts;
    }

    @GetMapping("/account/{id}")
    @ResponseBody
    public Account findAccount(@PathVariable int id)
    {
        Account account = repository.findById(id);
        return account;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public String updateAccount(@ModelAttribute("form") Account account) {

        if(repository.findById(account.getId()) == null){
            return "Compte non existant";
        }
        repository.deleteById(account.getId());
        repository.save(account);
        return "Compte mis à jour avec succès";

    }

    @GetMapping("/account/iban")
    @ResponseBody
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
    public String addAccount(@ModelAttribute("form") Account account, Model model) {
        repository.save(account);

        return "index";
    }

    @ResponseBody
    @DeleteMapping("/account/{id}")
    void deleteAccount(@PathVariable int id) {
        repository.deleteById(id);
    }
}
