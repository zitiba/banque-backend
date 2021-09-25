package bf.lonab.banque.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.RolesRepository;
import bf.lonab.banque.entites.NomRoles;
import bf.lonab.banque.entites.Roles;
import bf.lonab.banque.utilitaires.IRoleMetier;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleMetierImpl implements IRoleMetier{
    
	private RolesRepository rolesRepository;
	
	@Override
	public Roles creer(Roles entites) {
		return rolesRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		rolesRepository.deleteById(id);
		return true;
	}

	@Override
	public Roles modifier(Roles entites) {
		return rolesRepository.save(entites);
	}

	@Override
	public List<Roles> lister() {
		 return rolesRepository.findAll();
	}

	@Override
	public Roles trouver(Long id) {
		return rolesRepository.findById(id).get();
	}

	@Override
	public Roles findByNomRole(NomRoles nomRole){
		return rolesRepository.findByNomRole(nomRole).get();
	}
}
