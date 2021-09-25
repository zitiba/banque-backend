package bf.lonab.banque.utilitaires;

import java.util.List;

import bf.lonab.banque.exceptions.InvalidBanqueBackendExceptions;

/* interface generique qui sera utilisé par toutes les classes*/
public interface InterfaceMetier<T,U> {
	public T creer(T entites) throws InvalidBanqueBackendExceptions;
	public boolean supprimer(U id);
	public T modifier(T entites);
    public List<T> lister();
    public T trouver(U id) ;
}
