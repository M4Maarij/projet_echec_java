package echec;

import java.util.ArrayList;
import java.util.List;

// Un Cavalier EST une Piece qui peux changer de place alors il a la possibilité d'effectuer un mouvement.
public class Cavalier extends Piece implements Mouvement {

    public Cavalier(int x, int y, Couleur couleur) {
        super(x, y, couleur); // demander le constructeur père à remplir le Cavalier par (Sa position définie par X et Y et donner la couleur de Cette Piece.
    }

    public List<Position> getMouvementPossible() {
        int x = this.position.getX(); // X actuel
        int y = this.position.getY();//  Y actuel
        Position position = new Position(x, y); // Creer un objet position pour cette position X,Y
        ArrayList<Position> tableauPosition = new ArrayList<Position>(); // Tableau des positions à retourner en fin

        for(int xx = -2; xx <= 2; ++xx) { // X de -2 ->  -1 -> 0 -> 1 -> 2, le '0' represente la Piece, Centre
            for(int yy = -2; yy <= 2; ++yy) { // Y de -2  jusqu'à 2, le centre est 0.
                if (Math.abs(xx) + Math.abs(yy) == 3) { // si |X|+|Y| = 3, alors X et Y entoure la Piece
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
                    * En Général en effectue un mouvement sur X et Y par les Deux boucle FOR, une fois la somme des deux valeurs est 3 en se positionne
                    * */
                    position.setPosition(x + xx, y + yy);
                    if (position.inBounds() && !this.bloqueAmi(position)) {
                        // Si la position en question  ne dépasse pas la scene du jeu, 1 -------- 8 sur les X et les Y
                        // si cette position ne bloque pas un ami, meme couleur de la case.
                        tableauPosition.add(position.clone()); // Ajouter la position au tableau comme un mouvement possible
                    }
                }
            }
        }
        // faire la meme chose sur les 4 elements qui entoure la piece et retourner le tableau des position possible à occuper
        return tableauPosition;
    }

    public String toString() {
        return "[Cavalier " + super.position.getX() + "," + super.position.getY() + "]";
    }
}
