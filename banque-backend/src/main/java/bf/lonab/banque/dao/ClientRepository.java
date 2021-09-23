package bf.lonab.banque.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bf.lonab.banque.entites.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	   Optional<Client> findById(Long id);
	   Client findByNom(String nom);
	   Client findByPrenom(String nom);
}
