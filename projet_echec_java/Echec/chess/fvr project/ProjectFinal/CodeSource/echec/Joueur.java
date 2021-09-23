package echec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class Joueur {
    private String nom; // nom joueur
    private Couleur couleur; // couleur de Joueur, exemple black white
    List<Piece> pieces = new ArrayList<>(); // les pieces du joueur
    private Couleur couleurEnnemie; // couleur de son adversaire

    Joueur(String nom, Couleur couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    // getter de couleur
    public Couleur getCouleur() {
        return this.couleur;
    }

    // tous simplement remplir la table de ce joueur et lui faire savoir la couleur de son ennemie
    public void genererPieceJoueur() {
        if (this.couleur.equals(Couleur.BLACK)) {
            this.pieces = Piece.getBlackPiece();
            this.couleurEnnemie = Couleur.WHITE;
        } else {
            this.pieces = Piece.getWhitePiece();
            this.couleurEnnemie = Couleur.BLACK;
        }
    }

    public String getNom() {
        return this.nom;
    }

    public Piece bougerT1(Position position) {
        Piece p = null;
        boolean done = false;
        Iterator<Piece> mouve = this.pieces.iterator();

        while(mouve.hasNext()) {
            Piece secondeCase = (Piece)mouve.next();
            // Si la position donner est parmi les positions possible ( dans la liste de ses positions), OK!
            if (position.equals(secondeCase.getPosition())) {
                p = secondeCase;
                done = true; // effectué avec succès
                System.out.println(secondeCase.getMouvementPossible());
                break;
            }
        }
        if (!done) {
            System.out.println("Position erronée, rejouer");
        }
        // retourner la nouvelle piece.
        return p;
    }

    /*
    * Le roi se déplace d'une case dans n'importe quelle direction (horizontale, verticale, ou en diagonale),
    *  sauf sur une case occupée par une pièce de sa couleur ou contrôlée par une pièce ennemie
    * */
    public Piece bougerRoiT1(Position position) {
        Roi roi = null;
        boolean done = false;
        if (position.equals(Objects.requireNonNull(Roi.getRoiCouleur(this.couleur)).getPosition())) {
            roi = Roi.getRoiCouleur(this.couleur);
            done = true;
            assert roi != null;
            System.out.println(roi.getMouvementPossible());
        }
        if (!done) {
            System.out.println("Position erronée, rejouer");
        }
        return roi;
    }

    public boolean bougerT2(Piece piece, Position pos2, Position pos3) {
        boolean done = piece.bouger(pos3);
        if (done) {
            Iterator<Piece> ennemies = Piece.getColoredPiece(this.couleurEnnemie).iterator();

            while(ennemies.hasNext()) {
                Piece p = ennemies.next();
                if (p.getPosition().equals(pos3)) {
                    p.destroy(); // prend l'ennemie suivant, par un destroy, remove it
                    break;
                }
            }
        } else {
            System.out.println("Erreur d'arrivée");
        }
        return done; // OK ou Non
    }

    public String toString() {
        return "Joueur [nom=" + this.nom + ", couleur=" + this.couleur + "]";
    }
}
