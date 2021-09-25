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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import bf.lonab.banque.entites.Roles;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.IRoleMetier;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class RoleController {

	@Autowired
	 private IRoleMetier iRoleMetier;
	
	@Autowired private ObjectMapper mapper;
	
	@PostMapping("/roles")
	public String creer(@RequestBody Roles r) throws JsonProcessingException {
		
		Reponse<Roles> reponse;
		try {
			List<String> message=new ArrayList<>();
			Roles role=iRoleMetier.creer(r);
			message.add(String.format("%s a été creé avec succcès", role.getNomRole()));
			reponse=new Reponse<Roles>(0,message,role);
		} catch (Exception e) {
			reponse=new Reponse<Roles>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
    @PutMapping("/roles")
   	public String modifier(@RequestBody Roles r) throws JsonProcessingException {
   		
   		Reponse<Roles> reponse = null;
   		List<String> message=new ArrayList<>();
   		if(r!=null) {
	   		try {
	   			Roles role=iRoleMetier.trouver(r.getId());
	   				if(role!=null) {
	   					role=iRoleMetier.modifier(r);
	   					message=new ArrayList<>();
	   					message.add(String.format("%s a été modifié avec succcès", role.getNomRole()));
	   					reponse=new Reponse<Roles>(0,message,role);
	   				}
	   			
	   		} catch (Exception e) {
	   			reponse=new Reponse<Roles>(1,Exceptions.getErrorForException(e),null);
	   		}
   		}
   		else {
   			message=new ArrayList<>();
   			message.add("Role inexistant !");
			reponse=new Reponse<Roles>(3,message,null);
   		}
   		return mapper.writeValueAsString(reponse);
   	}
    
	@GetMapping("/roles/{id}")
	public String trouver(@PathVariable Long id) throws JsonProcessingException {
		List<String> message ;
		Reponse<Roles> reponse = null;
		if(id!=null) {
			try {
				Roles role=iRoleMetier.trouver(id);
				if(role!=null) {
					message=new ArrayList<>();
					message.add("Role trouvé avec succès");
					reponse=new Reponse<Roles>(0,message,role);
				}
				
			} catch (Exception e) {
				reponse=new Reponse<Roles>(1,Exceptions.getErrorForException(e),null);
			}
		}
		else {
				message=new ArrayList<>();
				message.add("Paramètre role inexistant");
				reponse=new Reponse<Roles>(3,message,null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/roles")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<Roles>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<Roles> listeRole=iRoleMetier.lister();
			if(listeRole!=null && !listeRole.isEmpty()) {
				message.add("Liste des client");
				reponse=new Reponse<List<Roles>>(0,message,listeRole);
			}else {
					message.add("Aucun role trouvé");
					reponse=new Reponse<List<Roles>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<Roles>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@DeleteMapping("/roles/{id}")
	public String supprimer(@PathVariable Long id) throws JsonProcessingException {
		
		Reponse<Boolean> reponse = null;
   		List<String> message=new ArrayList<>();
   		if(id!=null) {
	   		try {
	   				Roles role=iRoleMetier.trouver(id);
	   				if(role!=null) {
	   					message=new ArrayList<>();
	   					message.add(String.format("%s a été supprimé avec succcès", role.getNomRole()));
	   					reponse=new Reponse<Boolean>(0,message,iRoleMetier.supprimer(id));
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
   			message.add("Paramètre role inexistant !");
			reponse=new Reponse<Boolean>(3,message,null);
   		}
   		return mapper.writeValueAsString(reponse);
	}
}
