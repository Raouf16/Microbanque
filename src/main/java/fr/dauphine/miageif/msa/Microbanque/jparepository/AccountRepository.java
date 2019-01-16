package fr.dauphine.miageif.msa.Microbanque.jparepository;

import fr.dauphine.miageif.msa.Microbanque.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAll();
    Account findById(int id);

}
