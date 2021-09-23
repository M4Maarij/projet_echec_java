package Echec;
import java.util.*;

public class Plateau {
	
	ArrayList<Case> tabCase= new ArrayList<Case>();		//case rangé par x puis y
	
	
	public Plateau ()
	{
		for (int i=1;i<=8;i++)
		{
			for (int l=1;l<=8;l++)
			{
				Position p=new Position(l,i);
				Case c =new Case(p.clone());
				tabCase.add(c);		
			}
			
		}
	}
	
	// initialisation
	public void initialisation() {
		for (Piece count:Piece.tabPiece)
		{
			
			for (Case carre:tabCase) {
				if (count.position.equals(carre.getPosition())){
						carre.positionne(count);
						break;
				}
			}
		}
	}
	
	// Affichage de l'échiquier sur la console. (Premiere lettre de la piece  + la coueleur)
	public void afficherEchiquier() {
		
		
		
		
		
		int i=1;
		int j=7;
		System.out.print(" | 8 | ");
		for (Case count:tabCase)
		{
			
			if (i!=count.getPosition().getY()) {	// fais passer a la ligne ou deplacement sur l'axe Y
				
				
				System.out.println();
				i++;
				
				System.out.print(" | " +j + " | ");
				j--;
			}
			
			if (count.getPiece()!=null) {
				Piece p =count.getPiece(); // la piece
				String coul; // la couleur
				
				if (p.getCouleur().equals(Couleur.WHITE))
					coul=" blnc";
				else
					coul=" noir";

				
					switch(p.getClass().getSimpleName()) {
						case "Cavalier" :
							System.out.print("C"+coul);
							break;
						case "Reine" :
							System.out.print("D"+coul);
							break;
						case "Fou" :
							System.out.print("F"+coul);
							break;
						case "Pion" :
							System.out.print("P"+coul);
							break;
						case "Roi" :
							System.out.print("R"+coul);
							break;
						case "Tour" :
							System.out.print("T"+coul);
							break;
					}
			
			}else {
				System.out.print("......");
			}
			System.out.print(" | ");
				
		}
		System.out.println("\n _______________________________________________________________________________");
		System.out.println("     |   A    |   B    |   C    |   D    |   E    |   F    |   G    |   H    |");
        
		
	}
	
	// Mise a jour de l'échiquier et l'affichage.
	public void update()
	{
		
		this.initialisation();
		this.afficherEchiquier();
	}
	
	// Getter de la case définie par x,y
	public Case getCase(int x,int y)
	 {
		int entree=(y-1)*8+x-1;
		return this.tabCase.get(entree);
	 }
}
