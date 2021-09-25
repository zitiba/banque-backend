package bf.lonab.banque.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import bf.lonab.banque.entites.NomRoles;
import bf.lonab.banque.entites.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
	
 //optional permet d'éviter null pointer exception
  Optional<Roles> findByNomRole(NomRoles nomRole);
}
