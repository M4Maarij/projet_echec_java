package Echec;

import java.util.List;
import java.util.ArrayList;

public class Joueur{
	private String nom; // nom joueur
    private Couleur couleur; // couleur de Joueur, exemple black white
    List<Piece> tab = new ArrayList<>(); // les pieces du joueur
    private Couleur couleurEnnemie; // couleur de son adversaire

	
	
	Joueur (String n, Couleur c)		
	{
		nom=n;
		couleur=c;
	}
	
	// getter de couleur
	public Couleur getCouleur()
	{
		return couleur;
	}
	
	// tous simplement remplir la table de ce joueur et lui faire savoir la couleur de son ennemie
	public void updateTab()
	{
		if (couleur.equals(Couleur.BLACK))
		{
			tab=Piece.getBlackPiece();
			
			couleurEnnemie=Couleur.WHITE;
		}
		 else
		 {
			 tab=Piece.getWhitePiece();		 // definition des pieces du joueur
			 couleurEnnemie=Couleur.BLACK;
			 
		 }
		
	}
	
	
	
	public String getNom() {
		return nom;
	}





	public void abandonner(){
		
	}

	
	

	
	public Piece bougerT1(Position depart) {					
		Piece selectionnee=null;
		boolean ok=false;
		for (Piece count: tab)
		{
			// Si la position donner est parmi les positions possible 
			//( dans la liste de ses positions), OK!
			
			 if (depart.equals(count.getPosition()))
			 {
				 selectionnee=count;
				 ok=true;  // effectué avec succès
				 System.out.println(count.getMouvementPossible());
				 break;
			 }
			 
			 // si position fausse affiche erreur et arrete le code
			 
			 
		}
		if (!ok)
			System.out.println("position erronnée, rejouer");
		// retourner la nouvelle piece.
		return selectionnee;
			
	}
	
	   /*
	    * Le roi se déplace d'une case dans n'importe quelle direction (horizontale, verticale,
	    *  ou en diagonale), sauf sur une case occupée par une pièce de sa couleur ou 
	    *  contrôlée par une pièce ennemie  
	    */
	
	public Piece bougerRoiT1(Position depart) {					
		Piece selectionnee=null;
		boolean ok=false;
		if (depart.equals(Roi.getRoiCouleur(couleur).getPosition()))
		{
			selectionnee=Roi.getRoiCouleur(couleur);
			ok=true;
			System.out.println(selectionnee.getMouvementPossible());
				 
		}
			 // si position fausse affiche erreur et arrete le code
			 	 
		
		if (!ok)
			System.out.println("position erronnée, rejouer");
		return selectionnee;
			
	}
	
	public boolean bougerT2(Piece selectionnee, Position depart,Position arrivee) {

		boolean tentative=selectionnee.bouger(arrivee); 						//deplace piece et renvoi vrai si la position utilisable
		if (tentative)															
		{			
			for (Piece count: Piece.getColoredPiece(couleurEnnemie)  )//verifier presence des piece ennemie sur nouvelle position (manger)
			{
				if(count.getPosition().equals(arrivee))
				{
					count.destroy();	// prend l'ennemie suivant, par un destroy, remove it
					break;		
				}
			}
		}
		else
			System.out.println("erreur d'arrivé");
		return tentative;			
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", couleur=" + couleur + "]";
	}
	
	
	
	
	
}
