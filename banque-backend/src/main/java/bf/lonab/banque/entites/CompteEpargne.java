package bf.lonab.banque.entites;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{

	private static final long serialVersionUID = 1L;
    private double tauxInteret;
}
