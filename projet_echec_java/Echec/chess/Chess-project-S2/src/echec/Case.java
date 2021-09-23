package echec;

/*
* La classe Case represente tout simplement une case
* qui contient une piece
* défini par une position de type Position
* */
public class Case {
    private Position position; // La position de  la case
    private Piece piece;  // La piece de cette case de type Piece
    private String affichage;
    
    // Constructeur avec un seul paramettre
    public Case(Position position) {
        this.position = position;
    }

    // Positionner dans la case une Piece
    public void positionne(Piece piece) {
        this.piece = piece;
    }

    // Getter de la position
    public Position getPosition() {
        return this.position;
    }
   // Vider la case -> enlever la piece de cette case
    public void vider() {
        this.piece = null;
    }

    // Getter de la piece de cette case
    public Piece getPiece() {
        return this.piece;
    }

    // Methode toString qui retourne une chaine de caractère ( la position et la piece), chacun des deux elements appelle sa méthode toString implicitement
    public String toString() {
        return "Case" + this.position + this.piece;
    }
}
