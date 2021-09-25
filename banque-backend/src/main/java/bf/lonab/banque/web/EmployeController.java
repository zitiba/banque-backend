package bf.lonab.banque.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import bf.lonab.banque.entites.Employe;
import bf.lonab.banque.entites.NomRoles;
import bf.lonab.banque.entites.Roles;
import bf.lonab.banque.securites.JwtAuthenticationResponse;
import bf.lonab.banque.securites.JwtTokenProvider;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.IEmploye;
import bf.lonab.banque.utilitaires.IRoleMetier;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class EmployeController {

	@Autowired
	 private IEmploye iEmploye;
	
	@Autowired private ObjectMapper mapper;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	IRoleMetier roleMetier;
	
	@PostMapping("/connexion")
	public String authenticateUser(@Valid @RequestBody Employe loginRequest) throws JsonProcessingException {
		Reponse<ResponseEntity<?>> reponse;
		Authentication authentication = authenticationManager.authenticate
		(
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPwd())
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		reponse = new Reponse<ResponseEntity<?>>(0, null, ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
		return mapper.writeValueAsString(reponse);

	}

	@PostMapping("/inscription")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createUser(@RequestBody Employe signUpRequest) throws Exception {
		
        Reponse<Employe> reponse = null;
		Employe employe = null;
		try {

			Roles userRole = roleMetier.findByNomRole(NomRoles.ROLE_DIRECTEUR);
			signUpRequest.setRoles(Collections.singleton(userRole));
			employe = iEmploye.creer(signUpRequest);
			System.out.println("Voir le nom complet de la personne recuperée:" + employe.getNomComplet());
			

			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  a été créé avec succès", employe.getId()));
			reponse = new Reponse<Employe>(0, messages, employe);

		} catch (Exception e) {
			reponse = new Reponse<Employe>(1, Exceptions.getErrorForException(e), null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
    @PostMapping("/employe")
	public String creer(@RequestBody Employe emp) throws JsonProcessingException {
		
		Reponse<Employe> reponse;
		try {
			List<String> message=new ArrayList<>();
			Employe employe=iEmploye.creer(emp);
			message.add(String.format("%s a été creé avec succcès", employe.getNomComplet()));
			reponse=new Reponse<Employe>(0,message,employe);
		} catch (Exception e) {
			reponse=new Reponse<Employe>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
    @PutMapping("/employe")
   	public String modifier(@RequestBody Employe emp) throws JsonProcessingException {
   		
   		Reponse<Employe> reponse = null;
   		List<String> message=new ArrayList<>();
   		if(emp!=null) {
	   		try {
	   				Employe employe=iEmploye.trouver(emp.getId());
	   				if(employe!=null) {
	   					employe=iEmploye.modifier(emp);
	   					message=new ArrayList<>();
	   					message.add(String.format("%s a été modifié avec succcès", employe.getNomComplet()));
	   					reponse=new Reponse<Employe>(0,message,employe);
	   				}
	   			
	   		} catch (Exception e) {
	   			reponse=new Reponse<Employe>(1,Exceptions.getErrorForException(e),null);
	   		}
   		}
   		else {
   			message=new ArrayList<>();
   			message.add("Employé inexistant !");
			reponse=new Reponse<Employe>(3,message,null);
   		}
   		return mapper.writeValueAsString(reponse);
   	}
    
	@GetMapping("/employe/{id}")
	public String trouver(@PathVariable Long id) throws JsonProcessingException {
		List<String> message ;
		Reponse<Employe> reponse = null;
		if(id!=null) {
			try {
				Employe employe=iEmploye.trouver(id);
				if(employe!=null) {
					message=new ArrayList<>();
					message.add("Employé trouvé avec succès");
					reponse=new Reponse<Employe>(0,message,employe);
				}
				
			} catch (Exception e) {
				reponse=new Reponse<Employe>(1,Exceptions.getErrorForException(e),null);
			}
		}
		else {
				message=new ArrayList<>();
				message.add("Paramètre inexistant");
				reponse=new Reponse<Employe>(3,message,null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/employe")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<Employe>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<Employe> listeEmploye=iEmploye.lister();
			if(listeEmploye!=null && !listeEmploye.isEmpty()) {
				message.add("Liste des client");
				reponse=new Reponse<List<Employe>>(0,message,listeEmploye);
			}else {
					message.add("Aucun employé trouvé");
					reponse=new Reponse<List<Employe>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<Employe>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@DeleteMapping("/employe/{id}")
	public String supprimer(@PathVariable Long id) throws JsonProcessingException {
		
		Reponse<Boolean> reponse = null;
   		List<String> message=new ArrayList<>();
   		if(id!=null) {
	   		try {
	   				Employe employe=iEmploye.trouver(id);
	   				if(employe!=null) {
	   					message=new ArrayList<>();
	   					message.add(String.format("%s %s a été supprimé avec succcès", employe.getPrenom(), employe.getNom()));
	   					reponse=new Reponse<Boolean>(0,message,iEmploye.supprimer(id));
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
