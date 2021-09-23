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

import bf.lonab.banque.entites.Compte;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.ICompteMetier;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class CompteControler {

	@Autowired
	 private ICompteMetier iCompteMetier;
	
	@Autowired private ObjectMapper mapper;
	
	@PostMapping("/compte")
	public String creer(@RequestBody Compte c) throws JsonProcessingException {
		
		Reponse<Compte> reponse;
		try {
			List<String> message=new ArrayList<>();
			Compte compte=iCompteMetier.creer(c);
			message.add(String.format("%s a été creé avec succcès", compte.getId()));
			reponse=new Reponse<Compte>(0,message,compte);
		} catch (Exception e) {
			reponse=new Reponse<Compte>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/compte")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<Compte>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<Compte> listeCompte=iCompteMetier.lister();
			if(listeCompte!=null && !listeCompte.isEmpty()) {
				message.add("Liste des comptes");
				reponse=new Reponse<List<Compte>>(0,message,listeCompte);
			}else {
					message.add("Aucun compte trouvé");
					reponse=new Reponse<List<Compte>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<Compte>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
