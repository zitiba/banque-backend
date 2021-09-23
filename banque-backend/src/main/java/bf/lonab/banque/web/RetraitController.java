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
import bf.lonab.banque.entites.Retrait;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.IRetrait;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class RetraitController {

	@Autowired
	 private IRetrait iRetrait;
	
	@Autowired private ObjectMapper mapper;
	
	@PostMapping("/retrait")
	public String creer(@RequestBody Retrait r) throws JsonProcessingException {
		
		Reponse<Retrait> reponse;
		try {
			List<String> message=new ArrayList<>();
			Retrait retrait=iRetrait.creer(r);
			message.add(String.format("%s a été creé avec succcès", retrait.getId()));
			reponse=new Reponse<Retrait>(0,message,retrait);
		} catch (Exception e) {
			reponse=new Reponse<Retrait>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/retrait")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<Retrait>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<Retrait> listeCompteEpargne=iRetrait.lister();
			if(listeCompteEpargne!=null && !listeCompteEpargne.isEmpty()) {
				message.add("Liste des retraits");
				reponse=new Reponse<List<Retrait>>(0,message,listeCompteEpargne);
			}else {
					message.add("Aucun retrait trouvé");
					reponse=new Reponse<List<Retrait>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<Retrait>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
