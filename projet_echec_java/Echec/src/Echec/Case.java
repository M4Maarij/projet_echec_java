package Echec;

/*
* La classe Case represente tout simplement une case
* qui contient une piece
* d�fini par une position de type Position
* */

public class Case {
	private Position position; // La position de  la case
	private Piece piece; // La piece de cette case de type Piece
	private String affichage;
	
	// Constructeur avec un seul paramettre position
	public Case(Position position)
	{
		this.position = position;
	}
	
    // Positionner dans la case une Piece
	public void positionne(Piece p)
	{
		this.piece=p;
	}
	
	// Getter de la position
	public Position getPosition() {
		return position;
	}
	
	// Vider la case -> enlever la piece de cette case
	public void vider() {
		piece=null;
	}
	
	// Getter de la piece de cette case
	 public Piece getPiece()
	 {
		 return piece;
	 }

	// Methode toString qui retourne une chaine de caract�re ( la position et la piece), 
	//chacun des deux elements appelle sa m�thode toString implicitement
	@Override
	public String toString() {
		return ("Case"+ position + piece);
	}
	 
	 
}
