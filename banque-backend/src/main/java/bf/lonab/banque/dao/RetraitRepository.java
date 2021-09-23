package bf.lonab.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banque.entites.Retrait;

public interface RetraitRepository extends JpaRepository<Retrait, Long>{

}
