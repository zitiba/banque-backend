package bf.lonab.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banque.entites.CompteCourant;

public interface CompteCourantRepository extends JpaRepository<CompteCourant, Long>{

}
