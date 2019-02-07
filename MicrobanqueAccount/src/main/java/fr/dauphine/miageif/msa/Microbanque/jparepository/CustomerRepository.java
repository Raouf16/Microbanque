package fr.dauphine.miageif.msa.Microbanque.jparepository;

import fr.dauphine.miageif.msa.Microbanque.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Cette classe n'est pas utilisée, j'allais en faire un 3ème service "Customer" mais contrainte de temps ..

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    List<Customer> findAll();
    Customer findById(int id);
}
