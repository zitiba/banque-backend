package bf.lonab.banque.metier;

import java.util.List;

import org.springframework.stereotype.Service;

import bf.lonab.banque.dao.VersementRepository;
import bf.lonab.banque.entites.Versement;
import bf.lonab.banque.utilitaires.IVersement;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VersmentMetierImpl implements IVersement{

	private VersementRepository versementRepository;
	
	@Override
	public Versement creer(Versement entites) {
		return versementRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		versementRepository.deleteById(id);
		return true;
	}

	@Override
	public Versement modifier(Versement entites) {
		return versementRepository.save(entites);
	}

	@Override
	public List<Versement> lister() {
		 return versementRepository.findAll();
	}

	@Override
	public Versement trouver(Long id) {
		return versementRepository.findById(id).get();
	}

}
