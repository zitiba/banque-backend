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

import bf.lonab.banque.entites.Operation;
import bf.lonab.banque.utilitaires.Exceptions;
import bf.lonab.banque.utilitaires.IOperationMetier;
import bf.lonab.banque.utilitaires.Reponse;

@RestController
@CrossOrigin
public class OperationController {

	@Autowired
	 private IOperationMetier iOperationMetier;
	
	@Autowired private ObjectMapper mapper;
	
	@PostMapping("/operation")
	public String creer(@RequestBody Operation op) throws JsonProcessingException {
		
		Reponse<Operation> reponse;
		try {
			List<String> message=new ArrayList<>();
			Operation operation=iOperationMetier.creer(op);
			message.add(String.format("%s a été creé avec succcès", operation.getType().toString()));
			reponse=new Reponse<Operation>(0,message,operation);
		} catch (Exception e) {
			reponse=new Reponse<Operation>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/operation")
	public String lister() throws JsonProcessingException {
		
		Reponse<List<Operation>> reponse;
		try {
			List<String> message=new ArrayList<>();
			List<Operation> listeOperation=iOperationMetier.lister();
			if(listeOperation!=null && !listeOperation.isEmpty()) {
				message.add("Liste des operations");
				reponse=new Reponse<List<Operation>>(0,message,listeOperation);
			}else {
					message.add("Aucune operation trouvée");
					reponse=new Reponse<List<Operation>>(3,message,null);
				}			
			
		} catch (Exception e) {
			reponse=new Reponse<List<Operation>>(1,Exceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
