package bf.lonab.banque.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employe",uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Employe extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(max=20)
	private String nom;
	private String prenom;
	
	@Email
	private String email;
	private String pwd;
	private String nomComplet;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="Role_Employe", joinColumns = @JoinColumn(name="employeId"), inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Roles> roles =new HashSet<>();
	
	@PrePersist
	public void setNomComplet() {
		this.nomComplet=nom+" "+prenom;
	}
}
