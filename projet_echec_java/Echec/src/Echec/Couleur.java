package Echec;

public enum Couleur {
	
	// Deux couleurs possible dans le jeu, case blanche et noire.
	BLACK ("BLACK"), WHITE ("WHITE");
	
	
	Couleur(String s){description = s;} // Constructeur couleur pour eviter erreurs
	private String description;
	public String getDescription() {return description;}

}
