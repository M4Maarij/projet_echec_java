package echec;

import java.util.ArrayList;
import java.util.List;



/*
* Le fou est considéré, à juste titre, comme une arme à longue portée.
* En effet, il ne se déplace qu’en diagonale mais a le privilège de se pouvoir se déplacer d’une ou de plusieurs cases,
*  vers l’avant ou l’arrière. A noter qu’il ne peut se déplacer que sur une seule couleur,
*  c'est-à-dire la couleur de sa case initiale.
* */

// Fou est une Piece qui peut effectuer un Mouvement
public class Fou extends Piece implements Mouvement {
    public Fou(int x, int y, Couleur couleur) {
        super(x, y, couleur); // Constructeur de la classe Père, sur la position (x,y), avec  la couleur
    }
    public Fou(int x, int y, Couleur couleur,boolean premier) {
        super(x, y, couleur,premier); // Constructeur de la classe Père, sur la position (x,y), avec  la couleur
    }


    /*
     En matière d’attaque, le fou peut prendre la première pièce adverse qui se trouve sur son chemin. Cependant,
     comme il ne peut pas sauter sur une autre pièce,
     il se trouve automatiquement bloqué dès qu’une autre pièce amie ou adverse se trouve sur sa trajectoire.
     */

    public List<Position> getMouvementPossible() {
        int x = this.position.getX();
        int y = this.position.getY();
        Position position = new Position(x, y);
        ArrayList<Position> mouvements = new ArrayList<>();

        int pas;
        for(pas = 1; pas < 8; ++pas) {
            position.setX(x + pas);
            position.setY(y + pas);
            // Mouvement sur la diagonale par un pas
            /*
            *   P -(pas)->
            *            |
            *          (pas)
            *            |
            *           ICI
            * */

            // Si on est pas dans les limites de la scene ou bien  on bloque un ami, arrete!
            if (!position.inBounds() || this.bloqueAmi(position)) {
                break;
            }

            mouvements.add(position.clone());
            if (this.bloqueEnnemi(position)) {
                break; // Si on est bloqué par un ennemi, Arrete.
            }
        }

        // meme mouvement sur la deuxieme diagonale en bas
        for(pas = 1; pas < 8; ++pas) {
            position.setX(x - pas);
            position.setY(y + pas);
            if (!position.inBounds() || this.bloqueAmi(position)) {
                break;
            }

            mouvements.add(position.clone());
            if (this.bloqueEnnemi(position)) {
                break;
            }
        }

        // mouvement sur  la diagonale en haut à gauche
        for(pas = 1; pas < 8; ++pas) {
            position.setX(x - pas);
            position.setY(y - pas);
            if (!position.inBounds() || this.bloqueAmi(position)) {
                break;
            }

            mouvements.add(position.clone());
            if (this.bloqueEnnemi(position)) {
                break;
            }
        }

        // mouvement sur la diagonale en haut à droite
        for(pas = 1; pas < 8; ++pas) {
            position.setX(x + pas);
            position.setY(y - pas);
            if (!position.inBounds() || this.bloqueAmi(position)) {
                break;
            }

            mouvements.add(position.clone());
            if (this.bloqueEnnemi(position)) {
                break;
            }
        }
        // tableau des mouvements
        return mouvements;
    }

    public String toString() {
        return "[Fou " + super.position.getX() + "," + super.position.getY() + "]";
    }
}
