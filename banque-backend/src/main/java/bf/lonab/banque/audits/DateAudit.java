package bf.lonab.banque.audits;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dateCreation","dateModification"})
public abstract  class DateAudit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Instant dateCreation;
	@LastModifiedDate
	@Column(nullable = false)
	private Instant dateModification;
}
