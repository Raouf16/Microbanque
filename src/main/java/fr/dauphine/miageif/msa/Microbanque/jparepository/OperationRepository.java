package fr.dauphine.miageif.msa.Microbanque.jparepository;

import fr.dauphine.miageif.msa.Microbanque.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    List<Operation> findAll();
    Operation findById(int id);

}