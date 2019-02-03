package fr.dauphine.miageif.msa.Microbanque.jparepository;

import fr.dauphine.miageif.msa.Microbanque.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Integer> {

    List<Operation> findAll();
    Operation findById(int id);
    Operation findByType(String type);
    Operation findByDate(Date date);
    @Transactional
    Operation deleteById(int id);
}
