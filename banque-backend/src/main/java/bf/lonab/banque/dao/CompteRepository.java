package bf.lonab.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banque.entites.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{

}
