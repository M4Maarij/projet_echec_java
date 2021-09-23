package Echec;

import java.util.List;


//interface qui décrit le mouvement
//seule méthode getMouvementPossible, chaque classe implémente l'interface doit redéfinir la méthode
public interface Mouvement {

	
	List<Position> getMouvementPossible();
	
}
