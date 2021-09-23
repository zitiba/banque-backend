package bf.lonab.banque.metier;

import java.util.List;
import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.ClientRepository;
import bf.lonab.banque.entites.Client;
import bf.lonab.banque.utilitaires.IClientMetier;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientMetierImpl implements IClientMetier{
	 private ClientRepository clientRepository;
	 
	@Override
	public Client creer(Client entites) {
		 return clientRepository.save(entites);
	}

	@Override
	public boolean supprimer(Long id) {
		clientRepository.deleteById(id);
		return true;
	}

	@Override
	public Client modifier(Client entites) {
		return clientRepository.save(entites);
	}

	@Override
	public List<Client> lister() {
		 return clientRepository.findAll();
	}

	@Override
	public Client trouver(Long id) {
		return clientRepository.findById(id).get();
	}

}
