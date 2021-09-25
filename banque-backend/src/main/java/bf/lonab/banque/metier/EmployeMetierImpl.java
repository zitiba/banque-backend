package bf.lonab.banque.metier;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.EmployeRepository;
import bf.lonab.banque.entites.Employe;
import bf.lonab.banque.exceptions.InvalidBanqueBackendExceptions;
import bf.lonab.banque.utilitaires.IEmploye;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeMetierImpl implements IEmploye{
	
	private EmployeRepository employeRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Employe creer(Employe entites) throws InvalidBanqueBackendExceptions{
		if(entites.getNom()==null || "".equals(entites.getNom()) ) {
			throw new InvalidBanqueBackendExceptions("Le nom ne peut être vide");
		}
		entites.setPwd(passwordEncoder.encode(entites.getPwd()));
		return employeRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		employeRepository.deleteById(id);
		return true;
	}

	@Override
	public Employe modifier(Employe entites) {
		return employeRepository.save(entites);
	}

	@Override
	public List<Employe> lister() {
		return employeRepository.findAll();
	}

	@Override
	public Employe trouver(Long id) {
		return employeRepository.findById(id).get();
	}

}
