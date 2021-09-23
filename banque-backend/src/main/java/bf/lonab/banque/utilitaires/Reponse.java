package bf.lonab.banque.utilitaires;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reponse<T> {
   private int statut;
   private List<String> message;
   private T object;
}
