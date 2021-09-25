package bf.lonab.banque.entites;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="Type_Operation", discriminatorType = DiscriminatorType.STRING,length = 2)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(name="OV", value=Versement.class),
	@Type(name="OR", value=Retrait.class)
})
public class Operation  extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	private double montant;
	@Column(name="Type_Operation", insertable = false, updatable = false)
	private String type;
	
	@JoinColumn(name="idCompte")
	@ManyToOne(fetch = FetchType.LAZY)
	private Compte compte;
	
	@JoinColumn(name="idEmploye")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employe employe;
}
