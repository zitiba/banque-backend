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

import bf.lonab.banque.entites.CompteCourant;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.ICompteCourant;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class CompteCourantController {

	@Autowired
	 private ICompteCourant iCompteCourant;
	
	@Autowired private ObjectMapper mapper;
	
	@PostMapping("/compteCourant")
	public String creer(@RequestBody CompteCourant c) throws JsonProcessingException {
		
		Reponse<CompteCourant> reponse;
		try {
			List<String> message=new ArrayList<>();
			CompteCourant compteCourant=iCompteCourant.creer(c);
			message.add(String.format("%s a été creé avec succcès", compteCourant.getId()));
			reponse=new Reponse<CompteCourant>(0,message,compteCourant);
		} catch (Exception e) {
			reponse=new Reponse<CompteCourant>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/compteCourant")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<CompteCourant>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<CompteCourant> listeCompteCourant=iCompteCourant.lister();
			if(listeCompteCourant!=null && !listeCompteCourant.isEmpty()) {
				message.add("Liste des compte courant");
				reponse=new Reponse<List<CompteCourant>>(0,message,listeCompteCourant);
			}else {
					message.add("Aucun compte courant trouvé");
					reponse=new Reponse<List<CompteCourant>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<CompteCourant>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
