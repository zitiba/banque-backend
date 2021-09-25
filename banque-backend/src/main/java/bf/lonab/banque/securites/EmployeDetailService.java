package bf.lonab.banque.securites;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import bf.lonab.banque.dao.EmployeRepository;
import bf.lonab.banque.entites.Employe;

@Service
public class EmployeDetailService implements UserDetailsService   {
	
    @Autowired
	EmployeRepository employeRepository;
    
    @Override 
    @Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Employe emp=employeRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Utilisateur non disponible: "+email));
		return UserPrincipal.create(emp);
	}
    
    @Transactional
    public UserDetails loadUserById(Long id) {
        Employe p = employeRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("Utilisateur non disponible avec id : " + id)
        );

        return UserPrincipal.create(p);
    }
}
