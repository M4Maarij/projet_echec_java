package Echec;

import java.util.*;


/*
* Le fou est consid�r�, � juste titre, comme une arme � longue port�e.
* En effet, il ne se d�place qu�en diagonale mais a le privil�ge de se pouvoir 
* se d�placer d�une ou de plusieurs cases, vers l�avant ou l�arri�re. 
* A noter qu�il ne peut se d�placer que sur une seule couleur,
*  c'est-�-dire la couleur de sa case initiale.
*/

// Fou est une Piece qui peut effectuer un Mouvement
public class Fou extends Piece implements Mouvement{

	public Fou(int x, int y, Couleur c) {
		super(x, y, c); // Constructeur de la classe P�re, sur la position (x,y), avec  la couleur
		
	}
	public Fou(int x, int y) {
		super(x, y);  // Constructeur de la classe P�re, sur la position (x,y), sans  la couleur
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	 /*
    En mani�re d�attaque, le fou peut prendre la premi�re pi�ce adverse qui se
    trouve sur son chemin. Cependant, comme il ne peut pas sauter sur une autre pi�ce,
    il se trouve automatiquement bloqu� d�s qu�une autre pi�ce amie ou adverse se trouve
    sur sa trajectoire.
    */
	
	@Override
	public
	List<Position> getMouvementPossible() {  //renvoi les cases de dplacement du fou
		int x=position.getX();
		int y=position.getY();
		Position temp=new Position(x,y);
		List<Position> mouvementPossible = new ArrayList<Position>();
		
		
	
		
			for(int c=1;c<8;c++)				
			{
				temp.setX(x+c);
				temp.setY(y+c);			
				// Mouvement sur la diagonale par un pas
	            /*
	            *   P -(pas)->
	            *            |
	            *          (pas)
	            *            |
	            *           ICI
	            * */
				
	            // Si on est pas dans les limites de la scene ou bien  on bloque un ami, s'arrete!
				if (temp.inBounds() && !this.bloqueAmi(temp))	//si un alli dans la ligne s'arrete
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		
						break; // Si on est bloqu� par un ennemi, s'arrete et deplacement pas possible.
				}
				else
					break;					// si ami bloque case prend fin
			
			}
			
			// meme mouvement sur la deuxieme diagonale en bas
			for(int c=1;c<8;c++)				
			{
				temp.setX(x-c);
				temp.setY(y+c);
				if (temp.inBounds() && !this.bloqueAmi(temp))	
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		
						break;
				}
				else
					break;					// si ami bloque case prend fin
			
			}
			
			// mouvement sur  la diagonale en bas � gauche
			for(int c=1;c<8;c++)				
			{
				temp.setX(x-c);
				temp.setY(y-c);
				if (temp.inBounds() && !this.bloqueAmi(temp))	
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		
						break;
				}
				else
					break;					// si ami bloque case prend fin
			
			}
			
			 // mouvement sur la diagonale en bas � droite
			for(int c=1;c<8;c++)				
			{
				temp.setX(x+c);
				temp.setY(y-c);
				if (temp.inBounds() && !this.bloqueAmi(temp))	
				{
					mouvementPossible.add(temp.clone());
					if (this.bloqueEnnemi(temp))		
						break;
				}
				else
					break;					// si ami bloque case prend fin
			
			}
			
			
		// rajouter regle 1er mouv
		// tableau des mouvements
		return mouvementPossible;
	}
	
	


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[fou "+super.position.getX()+","+super.position.getY()+"]" ;
	}
	
	

}
