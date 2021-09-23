package bf.lonab.banque.entites;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("OR")
public class Retrait extends Operation {
	private static final long serialVersionUID = 1L;

}
