package Echec;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece implements Mouvement{
	public static List <Roi> tabRoi=new ArrayList<Roi>();
	boolean echec=false;
	boolean echecEtMat=false;
	boolean echecEtPat=false;
	boolean premierTour=true;
	
	public Roi(int x, int y, Couleur c) {
		super(x, y, c);
		tabRoi.add(this);
		// TODO Auto-generated constructor stub
	}

	
	public boolean isEchec() {
		return echec;
	}


	public boolean isEchecEtMat() {
		return echecEtMat;
	}

	/*
	 * Le roi se déplace d'exactement une case horizontalement, 
	 * verticalement ou en diagonale. Un mouvement spécial du roi, 
	 * appelé roque, n'est autorisé qu'une seule fois par joueur et
	 *  par partie.
	 */
	
	
	
	// mouvement possible du roi
    // Le Roi (ayant une croix) se place à côté de sa Dame au centre de sa rangée.
	public List<Position> getMouvementPossible() {
		// TODO Auto-generated method stub
		int x=position.getX();
		int y=position.getY();
		List<Position> mouvementPossible = new ArrayList<Position>();
		
		
		for (int c=-1;c<=1;c++)	//colonne
		{
			for(int l=-1;l<=1;l++)	//ligne
			{
				Position temp=new Position(x+l,y+c);  
				if((l!=0 || c!=0 )&& temp.inBounds() && !this.bloqueAmi(temp))	//ne pas mettre la position actuelle dans la matrice
				{
					
					mouvementPossible.add(temp.clone()); //obtient carré autour du roi c-a-d un mouvement possible
					
				}
			}
		}
		
		mouvementPossible=MouvementAutorises(mouvementPossible); //car roi ne peut pas se sacrifier
		
		
		
		
		return mouvementPossible;
	}
	
	
	public List<Position> MouvementAutorises(List<Position> mouvement){
		List <Position> posPrises;
		List <Position> posEnlevees=new ArrayList <Position>();
		if (couleur.equals(Couleur.WHITE) )
			posPrises=posPrisesNoir;
		else
			posPrises=posPrisesBlanc;
		
		for (Position pos:mouvement) {
			for (Position pos2:posPrises) {
				if (pos.equals(pos2)) {
					posEnlevees.add(pos);
					break;
				}
			
			}
		}
		mouvement.removeAll(posEnlevees);
		
		return mouvement;
	}
	
	
	String update()
	{
		String result="";
		List<Position> tempTab;
		if (couleur.equals(Couleur.BLACK))
			tempTab=posPrisesBlanc;
		else
			tempTab=posPrisesNoir;
		if (echec)			// reset de la variable
			echec=false;
		
		
		
		for (Position pos:tempTab) {	// verifie si une piece peut manger le roi
			if(pos.equals(position))
			{
				echec=true;
				result="echec";			
			}
			
		}
				
		/*
		 * L'échec est une attaque sur le roi ennemi ;
		 *  cette attaque ne peut être ignorée. Si l'échec 
		 *  ne peut pas être neutralisé, c'est un échec et mat et la partie est terminée.
		 *  Le pat se produit lorsqu'un joueur n'a aucun mouvement légal, mais que 
		 *  son roi n'est pas en échec.
		 */
	
		if(!echec && this.mouvementExecutable.isEmpty() && 
				Piece.getColoredPiece(couleur).size()==1)
		{
			
			echecEtPat=true;
			result="echec et pat";       
		}
		
		
		if(echec && this.mouvementExecutable.isEmpty())
		{
			result="echec et mat";
			echecEtMat=true;
		}
		return result;
	}
	
	static Roi getRoiCouleur(Couleur c)
	{
		for (Roi r:Roi.tabRoi) {
			if (r.couleur.equals(c))
				return r;
		}
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[roi "+super.position.getX()+","+super.position.getY()+"]" ;
	}

}
