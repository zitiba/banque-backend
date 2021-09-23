package bf.lonab.banque.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import bf.lonab.banque.entites.CompteEpargne;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.ICompteEpargne;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class CompteEpargneController {

	@Autowired
	 private ICompteEpargne iCompteEpargne;
	
	@Autowired private ObjectMapper mapper;
	
	@PostMapping("/compteEpargne")
	public String creer(@RequestBody CompteEpargne c) throws JsonProcessingException {
		
		Reponse<CompteEpargne> reponse;
		try {
			List<String> message=new ArrayList<>();
			CompteEpargne compteEpargne=iCompteEpargne.creer(c);
			message.add(String.format("%s a été creé avec succcès", compteEpargne.getId()));
			reponse=new Reponse<CompteEpargne>(0,message,compteEpargne);
		} catch (Exception e) {
			reponse=new Reponse<CompteEpargne>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/compteEpargne")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<CompteEpargne>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<CompteEpargne> listeCompteEpargne=iCompteEpargne.lister();
			if(listeCompteEpargne!=null && !listeCompteEpargne.isEmpty()) {
				message.add("Liste des compte épargne");
				reponse=new Reponse<List<CompteEpargne>>(0,message,listeCompteEpargne);
			}else {
					message.add("Aucun compte épargne trouvé");
					reponse=new Reponse<List<CompteEpargne>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<CompteEpargne>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
