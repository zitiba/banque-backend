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
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="Type_Compte", discriminatorType = DiscriminatorType.STRING,length = 2)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(name="CC", value=CompteCourant.class),
	@Type(name="CE", value=CompteEpargne.class)
})

public class Compte extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	private double solde;
	@Column(name="Type_Compte", insertable = false, updatable = false)
	private String type;
	
	@JoinColumn(name="idClient")
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	
	@JoinColumn(name="idEmploye")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employe employe;
	
}
