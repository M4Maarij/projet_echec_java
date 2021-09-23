package Echec;

import java.util.List;


//interface qui d�crit le mouvement
//seule m�thode getMouvementPossible, chaque classe impl�mente l'interface doit red�finir la m�thode
public interface Mouvement {

	
	List<Position> getMouvementPossible();
	
}
