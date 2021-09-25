package bf.lonab.banque.utilitaires;

import bf.lonab.banque.entites.NomRoles;
import bf.lonab.banque.entites.Roles;

public interface IRoleMetier extends InterfaceMetier<Roles, Long>{
	public Roles findByNomRole(NomRoles nomRole);
}
