package bf.lonab.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banque.entites.Versement;

public interface VersementRepository extends JpaRepository<Versement, Long>{

}
