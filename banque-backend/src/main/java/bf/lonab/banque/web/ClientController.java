package bf.lonab.banque.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banque.entites.Client;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.IClientMetier;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class ClientController {

	@Autowired
	 private IClientMetier iClientMetier;
	
	@Autowired private ObjectMapper mapper;
	
    @PostMapping("/client")
	public String creer(@RequestBody Client c) throws JsonProcessingException {
		
		Reponse<Client> reponse;
		try {
			List<String> message=new ArrayList<>();
			Client client=iClientMetier.creer(c);
			message.add(String.format("%s %s a été creé avec succcès", client.getPrenom(), client.getNom()));
			reponse=new Reponse<Client>(0,message,client);
		} catch (Exception e) {
			reponse=new Reponse<Client>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
    @PutMapping("/client")
   	public String modifier(@RequestBody Client c) throws JsonProcessingException {
   		
   		Reponse<Client> reponse = null;
   		List<String> message=new ArrayList<>();
   		if(c!=null) {
	   		try {
	   				Client client=iClientMetier.trouver(c.getId());
	   				if(client!=null) {
	   					client=iClientMetier.modifier(c);
	   					message=new ArrayList<>();
	   					message.add(String.format("%s %s a été modifié avec succcès", client.getPrenom(), client.getNom()));
	   					reponse=new Reponse<Client>(0,message,client);
	   				}
	   			
	   		} catch (Exception e) {
	   			reponse=new Reponse<Client>(1,Exceptions.getErrorForException(e),null);
	   		}
   		}
   		else {
   			message=new ArrayList<>();
   			message.add("Client inexistant !");
			reponse=new Reponse<Client>(3,message,null);
   		}
   		return mapper.writeValueAsString(reponse);
   	}
    
	@GetMapping("/client/{id}")
	public String trouver(@PathVariable Long id) throws JsonProcessingException {
		List<String> message ;
		Reponse<Client> reponse = null;
		if(id!=null) {
			try {
				Client client=iClientMetier.trouver(id);
				if(client!=null) {
					message=new ArrayList<>();
					message.add("Client trouvé avec succès");
					reponse=new Reponse<Client>(0,message,client);
				}
				
			} catch (Exception e) {
				reponse=new Reponse<Client>(1,Exceptions.getErrorForException(e),null);
			}
		}
		else {
				message=new ArrayList<>();
				message.add("Paramètre inexistant");
				reponse=new Reponse<Client>(3,message,null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/client")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<Client>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<Client> listeClient=iClientMetier.lister();
			if(listeClient!=null && !listeClient.isEmpty()) {
				message.add("Liste des client");
				reponse=new Reponse<List<Client>>(0,message,listeClient);
			}else {
					message.add("Aucun client trouvé");
					reponse=new Reponse<List<Client>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<Client>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@DeleteMapping("/client/{id}")
	public String supprimer(@PathVariable Long id) throws JsonProcessingException {
		
		Reponse<Boolean> reponse = null;
   		List<String> message=new ArrayList<>();
   		if(id!=null) {
	   		try {
	   				Client client=iClientMetier.trouver(id);
	   				if(client!=null) {
	   					message=new ArrayList<>();
	   					message.add(String.format("%s %s a été supprimé avec succcès", client.getPrenom(), client.getNom()));
	   					reponse=new Reponse<Boolean>(0,message,iClientMetier.supprimer(id));
	   				}
	   				else {
	   					message=new ArrayList<>();
	   					message.add("Element à supprimer inexistant");
	   				}
	   			
	   		} catch (Exception e) {
	   			reponse=new Reponse<Boolean>(1,Exceptions.getErrorForException(e),null);
	   		}
   		}
   		else {
   			message=new ArrayList<>();
   			message.add("Paramètre inexistant !");
			reponse=new Reponse<Boolean>(3,message,null);
   		}
   		return mapper.writeValueAsString(reponse);
	}
}
