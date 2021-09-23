package bf.lonab.banque.utilitaires;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Exceptions {
    public static List<String> getErrorForException(Exception ex){
		Throwable cause=ex;
		List<String> erreurs=new ArrayList();
		while (cause !=null) {
			String message=cause.getMessage();
			if(message!=null && message.trim().length()!=0) {
				erreurs.add(message);
			}
			cause=cause.getCause();
		}
    	return erreurs;
    }
}
