package bf.lonab.banque.metier;

import java.util.List;

import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.CompteCourantRepository;
import bf.lonab.banque.entites.CompteCourant;
import bf.lonab.banque.utilitaires.ICompteCourant;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompteCourantMetierImpl  implements ICompteCourant{

	 private CompteCourantRepository compteCourantRepository;

	@Override
	public CompteCourant creer(CompteCourant entites) {
		 return compteCourantRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		compteCourantRepository.deleteById(id);
		return true;
	}

	@Override
	public CompteCourant modifier(CompteCourant entites) {
		 return compteCourantRepository.save(entites);
	}

	@Override
	public List<CompteCourant> lister() {
		 return compteCourantRepository.findAll();
	}

	@Override
	public CompteCourant trouver(Long id) {
		return compteCourantRepository.findById(id).get();
	}
}
