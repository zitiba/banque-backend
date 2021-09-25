package bf.lonab.banque.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banque.entites.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long>{
	Optional<Employe> findByEmail(String email);

}
