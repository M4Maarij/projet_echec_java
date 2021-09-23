package Echec;

import java.util.ArrayList;
import java.util.List;

public class Reine extends Piece implements Mouvement{

	public Reine(int x, int y, Couleur c) {
		super(x, y, c);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * La reine peut �tre d�plac�e d'un nombre quelconque de cases
	inoccup�es en ligne droite, verticalement, horizontalement ou
	en diagonale, combinant ainsi les d�placements de la tour et du fou.
	La reine fait une prise en occupant la case sur laquelle se trouve une pi�ce ennemie.
	*
	*/
	
	
	
	//  les cases de d�placement de Dame
	
	public List<Position> getMouvementPossible() {  
		List<Position> mouvementPossible = new ArrayList<Position>(); // verification des mouvements possible
		int x=position.getX();	// position horizontalement
		int y=position.getY();	// position verticalement
		Fou ftemp = new Fou(x,y);
		Tour Ttemp = new Tour(x,y, false); //mettre le 1er tour � faux
		
		mouvementPossible.addAll(ftemp.getMouvementPossible());	
		mouvementPossible.addAll(Ttemp.getMouvementPossible());
	
		return mouvementPossible;
	}
	
	
	// renvoi la position de Reine sur l'echiquier 
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[reine "+super.position.getX()+","+super.position.getY()+"]" ;
	}
	

}
