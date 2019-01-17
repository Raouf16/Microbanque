package fr.dauphine.miageif.msa.Microbanque.controller;

import fr.dauphine.miageif.msa.Microbanque.entity.Account;
import fr.dauphine.miageif.msa.Microbanque.jparepository.AccountRepository;
import fr.dauphine.miageif.msa.Microbanque.jparepository.OperationRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Operation;
import fr.dauphine.miageif.msa.Microbanque.utils.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public Operation findOperationByID(@PathVariable int id)
    {
        Operation operation = repository.findById(id);
        return operation;
    }

    @GetMapping("/operation/type/{type}")
    public Operation findOperationByType(@PathVariable OperationType type)
    {
        Operation operation = repository.findByType(type);
        return operation;
    }

    @GetMapping("/operation/date/{date}")
    public Operation findOperationByType(@PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date)
    {
        Operation operation = repository.findByDate(date);
        return operation;
    }

    @PostMapping("/operation/add")
    public String addOperation(@ModelAttribute("form") Operation operation) {
        List<Account> accounts = accountRepository.findAll();
        Account compte_source = new Account();
        Account compte_dest = new Account();

        if(operation.getMontant() <= 0){
            return "Impossible d'effectuer une opération d'un montant nul ou négatif";
        }

        for(int i = 0; i < accounts.size(); i++){
            if(String.valueOf(accounts.get(i).getIban()) == String.valueOf(operation.getIban_source())){
                compte_source = accounts.get(i);
            }
            else if(String.valueOf(accounts.get(i).getIban()) == String.valueOf(operation.getIban_destination())){
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
