package fr.dauphine.miageif.msa.Microbanque.jparepository;

import fr.dauphine.miageif.msa.Microbanque.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    List<Customer> findAll();
    Customer findById(int id);
}
