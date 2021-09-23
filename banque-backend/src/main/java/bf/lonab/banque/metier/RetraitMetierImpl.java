package bf.lonab.banque.metier;

import java.util.List;

import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.RetraitRepository;
import bf.lonab.banque.entites.Retrait;
import bf.lonab.banque.utilitaires.IRetrait;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RetraitMetierImpl implements IRetrait{
    
	private RetraitRepository retraitRepository;
	
	@Override
	public Retrait creer(Retrait entites) {
		return retraitRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		retraitRepository.deleteById(id);
		return true;
	}

	@Override
	public Retrait modifier(Retrait entites) {
		return retraitRepository.save(entites);
	}

	@Override
	public List<Retrait> lister() {
		 return retraitRepository.findAll();
	}

	@Override
	public Retrait trouver(Long id) {
		return retraitRepository.findById(id).get();
	}

}
