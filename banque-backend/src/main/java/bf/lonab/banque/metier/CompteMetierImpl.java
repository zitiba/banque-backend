package bf.lonab.banque.metier;

import java.util.List;
import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.CompteRepository;
import bf.lonab.banque.entites.Compte;
import bf.lonab.banque.utilitaires.ICompteMetier;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompteMetierImpl implements ICompteMetier{
    private CompteRepository compteRepository;
    
	@Override
	public Compte creer(Compte entites) {
		return compteRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		compteRepository.deleteById(id);
		return true;
	}

	@Override
	public Compte modifier(Compte entites) {
		return  compteRepository.save(entites);
	}

	@Override
	public List<Compte> lister() {
		return compteRepository.findAll();
	}

	@Override
	public Compte trouver(Long id) {
		return compteRepository.findById(id).get();
	}

}
