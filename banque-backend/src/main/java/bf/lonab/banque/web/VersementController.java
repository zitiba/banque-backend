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
import bf.lonab.banque.entites.Versement;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.IVersement;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class VersementController {

	@Autowired
	 private IVersement iVersement;
	
	@Autowired private ObjectMapper mapper;
	
	@PostMapping("/versement")
	public String creer(@RequestBody Versement v) throws JsonProcessingException {
		
		Reponse<Versement> reponse;
		try {
			List<String> message=new ArrayList<>();
			Versement versement=iVersement.creer(v);
			message.add(String.format("%s a été creé avec succcès", versement.getId()));
			reponse=new Reponse<Versement>(0,message,versement);
		} catch (Exception e) {
			reponse=new Reponse<Versement>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/versement")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<Versement>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<Versement> listeVersement=iVersement.lister();
			if(listeVersement!=null && !listeVersement.isEmpty()) {
				message.add("Liste des compte épargne");
				reponse=new Reponse<List<Versement>>(0,message,listeVersement);
			}else {
					message.add("Aucun compte épargne trouvé");
					reponse=new Reponse<List<Versement>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<Versement>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
