package fr.dauphine.miageif.msa.Microbanque.controller;

import fr.dauphine.miageif.msa.Microbanque.jparepository.OperationRepository;
import fr.dauphine.miageif.msa.Microbanque.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Controller
public class OperationController {

    @Autowired
    private Environment environment;

    @Autowired
    private OperationRepository repository;


    @GetMapping("/")
    public String index(Model model) {

        return "index";
    }
    @GetMapping("/view")
    public String viewOperationList(Model model) {

        List<Operation> operations = repository.findAll();
        model.addAttribute("operations", operations);

        return "operationList";
    }

    @GetMapping("/view/id")
    public String viewOperation(@ModelAttribute("form") Operation operationGet, Model model) {

        Operation operation = repository.findById(operationGet.getId());
        model.addAttribute("operation", operation);

        return "operationView";
    }

    @GetMapping("/view/type")
    public String viewOperationType(@ModelAttribute("form") Operation operationGet, Model model) {

        List<Operation> operations = repository.findByType(operationGet.getType());
        model.addAttribute("operations", operations);

        return "operationList";
    }

    @GetMapping("/view/date")
    public String viewOperationDate(@ModelAttribute("form") Operation operationGet, Model model) {

        List<Operation> operations = repository.findByDate(operationGet.getDate());
        model.addAttribute("operations", operations);

        return "operationList";
    }

    @ResponseBody
    @GetMapping("/operation/all")
    public List<Operation> findAllOperatios()
    {
        List<Operation> operations = repository.findAll();
        return operations;
    }

    @ResponseBody
    @GetMapping("/operation/{id}")
    public Operation findOperationByID(@PathVariable int id)
    {
        Operation operation = repository.findById(id);
        return operation;
    }

    @ResponseBody
    @GetMapping("/operation/type/{type}")
    public List<Operation>  findOperationByType(@PathVariable String type)
    {
        List<Operation> operations = repository.findByType(type);
        return operations;
    }

    @ResponseBody
    @GetMapping("/operation/date/{date}")
    public List<Operation>  findOperationByDate(@PathVariable @DateTimeFormat(pattern="dd-MM-yyyy") Date date)
    {
        List<Operation> operations = repository.findByDate(date);
        return operations;
    }

    @ResponseBody
    @PostMapping("/operation/add")
    public String addOperation(@ModelAttribute("form") Operation operation) {

        List ibans;

        //On récupère tous les ibans du service account
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8012/account/iban",List.class);

        ibans = responseEntity.getBody();

        String compte_source = "";
        String compte_dest = "";

        //On check si les ibans renseignés pour l'opération existent bien
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

    @ResponseBody
    @Transactional
    @DeleteMapping("/operation/{id}")
    void deleteOperation(@PathVariable int id) {
        repository.deleteById(id);
    }
}
