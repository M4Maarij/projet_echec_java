 package Echec;

 public class Test {
	    public static void main(String[] args) {
	        Partie partie = new Partie("Maarij","Dinesh");

	        while (!partie.fini)
	            partie.debutTour();

			
			/*Position pos=new Position(5,6);
			Reine r2=new Reine (5,8,Couleur.BLACK);
			System.out.println(r2.bouger(pos));
			System.out.println(r2.getMouvementPossible());

			Reine r2=new Reine (5,8,Couleur.BLACK);
			System.out.println(r2.getCouleur().toString());

			String coul=r2.getCouleur().toString();
			coul=coul.substring(0,1).toLowerCase();
			System.out.println(coul);*/
			



	    }
	}