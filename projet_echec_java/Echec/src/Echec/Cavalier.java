package Echec;
import java.util.*;

/*
 * Un Cavalier EST une Piece qui peux changer 
 *de place alors il a la possibilité d'effectuer un mouvement.
 *il peut se déplacer de deux cases verticalement et d'une case
 * horizontalement, ou de deux cases horizontalement et d'une case 
 * verticalement (les deux formant la forme d'un L).
 *  De cette façon, un chevalier peut avoir un maximum de 8 mouvements.
 */


public class Cavalier  extends Piece implements Mouvement {

	public Cavalier(int x, int y, Couleur c) {
		super(x, y, c);  		// demander le constructeur père à remplir le Cavalier 
								//par (Sa position définie par X et Y et donner la couleur de Cette Piece.
		
	}
	
	@Override
	public List<Position> getMouvementPossible() {
		// TODO Auto-generated method stub
		
		int x=position.getX();  // X actuel
		int y=position.getY();  // Y actuel
		Position temp=new Position(x,y);   // Creer un objet position pour cette position X,Y
		List<Position> mouvementPossible = new ArrayList<Position>(); // Tableau des positions à retourner en fin
		
		
		for(int px=-2;px<=2;px++)  // X de -2 ->  -1 -> 0 -> 1 -> 2, le '0' represente la Piece, Centre
		{
			for(int py=-2;py<=2;py++) // Y de -2  jusqu'à 2, le centre est 0.
			{
				
				if(Math.abs(px)+Math.abs(py)==3)  // si |X|+|Y| = 3, alors X et Y entoure la Piece
				{
					/* EXEMPLE  SITUATION
	                    *        *<- -2
	                    *        |
	                    *    * <-|- -1     // * EST alors devient la position de la piece
	                    *    |   |
	                    *   -2  -1   0    1   2
	                    *                 |   |
	                    *            1 ---|--> *
	                    *                 |
	                    *            2 -> *    // * devient la position de l'objet en question
	                    *
	                    * En Général en effectue un mouvement sur X et Y par les Deux boucle FOR,
	                    *  une fois la somme des deux valeurs est 3 en se positionne
	                    * 
	                    */
					temp.setPosition(x+px, y+py);
					if(temp.inBounds()&& !this.bloqueAmi(temp)) // pas d'ami sur les cases
					{
						// Si la position en question  ne dépasse pas la scene du jeu,
						//1 -------- 8 sur les X et les Y
                        // si cette position ne bloque pas un ami, meme couleur de la case.
						
						mouvementPossible.add(temp.clone());   // Ajouter la position au tableau comme un mouvement possible
					}
				}
			}
		}
		
		// faire la meme chose sur les 4 elements qui entoure
		//la piece et retourner le tableau des position possible à occuper
		return mouvementPossible;
	}

	@Override
	public String toString() {
		return "[cheval "+super.position.getX()+","+super.position.getY()+"]" ;
	}

}
