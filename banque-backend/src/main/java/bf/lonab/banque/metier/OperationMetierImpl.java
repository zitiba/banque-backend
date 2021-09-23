package bf.lonab.banque.metier;

import java.util.List;

import org.springframework.stereotype.Service;

import bf.lonab.banque.dao.OperationRepository;
import bf.lonab.banque.entites.Operation;
import bf.lonab.banque.utilitaires.IOperationMetier;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperationMetierImpl implements IOperationMetier{

	private OperationRepository operationRepository;
	
	@Override
	public Operation creer(Operation entites) {
		return operationRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		operationRepository.deleteById(id);
		return true;
	}

	@Override
	public Operation modifier(Operation entites) {
		return operationRepository.save(entites);
	}

	@Override
	public List<Operation> lister() {
		return operationRepository.findAll();
	}

	@Override
	public Operation trouver(Long id) {
		return operationRepository.findById(id).get();
	}

}
