package bf.lonab.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banque.entites.Operation;

public interface OperationRepository  extends JpaRepository<Operation, Long>{

}
