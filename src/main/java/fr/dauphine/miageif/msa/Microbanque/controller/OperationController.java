package fr.dauphine.miageif.msa.Microbanque.controller;

import fr.dauphine.miageif.msa.Microbanque.entity.Account;
import fr.dauphine.miageif.msa.Microbanque.jparepository.AccountRepository;
import fr.dauphine.miageif.msa.Microbanque.jparepository.OperationRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OperationController {

    @Autowired
    private Environment environment;

    @Autowired
    private OperationRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/operation/all")
    public List<Operation> findAllOperatios()
    {
        List<Operation> operations = repository.findAll();
        return operations;
    }

    @GetMapping("/operation/{id}")
    public Operation findOperation(@PathVariable int id)
    {
        Operation operation = repository.findById(id);
        return operation;
    }

    @PostMapping("/operation/add")
    public String addOperation(@ModelAttribute("form") Operation operation) {
        List<Account> accounts = accountRepository.findAll();
        Account compte_source = null;
        Account compte_dest = null;

        if(operation.getMontant() <= 0){
            return "Impossible d'effectuer une opération d'un montant nul ou négatif";
        }


        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getIban() == operation.getIban_source()){
                compte_source = accounts.get(i);
            }
            else if(accounts.get(i).getIban() == operation.getIban_destination()){
                compte_dest = accounts.get(i);
            }
        }

        if(compte_source == null){
            return "Iban source non existant";
        }
        else if(compte_dest == null){
            return "Iban destination non existant";
        }

        repository.save(operation);
        return "Opération effectuée avec succès";
    }

    @DeleteMapping("/operation/{id}")
    void deleteOperation(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
