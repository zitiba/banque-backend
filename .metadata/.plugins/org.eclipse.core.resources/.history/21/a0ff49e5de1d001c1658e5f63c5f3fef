package bf.lonab.banque.metier;

import java.util.List;

import bf.lonab.banque.dao.EmployeRepository;
import bf.lonab.banque.entites.Employe;
import bf.lonab.banque.utilitaires.IEmploye;

public class EmployeMetierImpl implements IEmploye{
	
	private EmployeRepository employeRepository;
	
	@Override
	public Employe creer(Employe entites) {
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
