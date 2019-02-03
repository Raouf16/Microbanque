package fr.dauphine.miageif.msa.Microbanque.controller;


import fr.dauphine.miageif.msa.Microbanque.jparepository.OperationRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
public class OperationController {

    @Autowired
    private Environment environment;

    @Autowired
    private OperationRepository repository;


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
    public Operation findOperationByType(@PathVariable String type)
    {
        Operation operation = repository.findByType(type);
        return operation;
    }

    @GetMapping("/operation/date/{date}")
    public Operation findOperationByDate(@PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date)
    {
        Operation operation = repository.findByDate(date);
        return operation;
    }

    @PostMapping("/operation/add")
    public String addOperation(@ModelAttribute("form") Operation operation) {

        List ibans;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8012/account/iban",List.class);

        ibans = responseEntity.getBody();

        String compte_source = "";
        String compte_dest = "";


        if(operation.getMontant() <= 0){
            return "Impossible d'effectuer une opération d'un montant nul ou négatif";
        }

        for(int i = 0; i < ibans.size(); i++){
            if(String.valueOf(ibans.get(i)).equals(String.valueOf(operation.getIban_source()))){
                compte_source = String.valueOf(ibans.get(i));
            }
            else if(String.valueOf(ibans.get(i)).equals(String.valueOf(operation.getIban_destination()))){
                compte_dest = String.valueOf(ibans.get(i));
            }
        }

        if(compte_source == ""){
            return "Iban source non existant";
        }
        else if(compte_dest == ""){
            return "Iban destination non existant";
        }

        repository.save(operation);
        return "Opération effectuée avec succès";

    }

    @Transactional
    @DeleteMapping("/operation/{id}")
    void deleteOperation(@PathVariable int id) {
        repository.deleteById(id);
    }
}
