package bf.lonab.banque.metier;

import java.util.List;

import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.CompteEpargneRepository;
import bf.lonab.banque.entites.CompteEpargne;
import bf.lonab.banque.utilitaires.ICompteEpargne;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompteEpargneMetierImpl implements ICompteEpargne {

	private CompteEpargneRepository compteEpargneRepository;

	@Override
	public CompteEpargne creer(CompteEpargne entites) {
		return compteEpargneRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		compteEpargneRepository.deleteById(id);
		return true;
	}

	@Override
	public CompteEpargne modifier(CompteEpargne entites) {
		return compteEpargneRepository.save(entites);
	}

	@Override
	public List<CompteEpargne> lister() {
		 return compteEpargneRepository.findAll();
	}

	@Override
	public CompteEpargne trouver(Long id) {
		return compteEpargneRepository.findById(id).get();
	}
}
