package bf.lonab.banque.entites;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import bf.lonab.banque.audits.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEntity  extends DateAudit{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	/*definit la version de la classe chaque fois que la classe est modifié la version est changé*/
	@Version
	protected Long version;
}
