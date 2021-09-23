package echec;

import java.util.ArrayList;
import java.util.List;

/*
* La tour peut se déplacer horizontalement ou verticalement.
* Cette pièce est à longue portée, c'est-à-dire qu'elle peut être déplacée d'autant de cases qu'on le souhaite,
* sans pouvoir sauter par-dessus une autre pièce*/

// est une Piece qui peut effectuer un Mouvement
public class Tour extends Piece implements Mouvement {
    boolean firstRound;

    public Tour(int x, int y, Couleur c) {
        super(x, y, c);
        firstRound =true;
    }

    public Tour(int x, int y, Couleur c,boolean b) {
        super(x, y, c);
        firstRound=b;
    }

    @Override
    public List<Position> getMouvementPossible() {  // sera lancé à chaque fois que le pion est bougé
        List<Position> mouvementPossible = new ArrayList<Position>();
        int x=position.getX();         //max de 8 case par ligne
        int y=position.getY();         // 2 couleurs
        Position temp=new Position(x,y);

        for(int c=1;c<8;c++)				//ligne à droite
        {
            temp.setX(x+c);
            temp.setY(y);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp))	//si un ennemie sur la case
                    break;						// les suivantes innaccessibles
            }
            else
                break;
        }
        for(int c=1;c<8;c++)				//ligne à gauche
        {
            temp.setX(x-c);
            temp.setY(y);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp))	//si un ennemie sur la case
                    break;						// les suivantes innaccessibles
            }
            else
                break;
        }
        for(int colonne=1;colonne<8;colonne++)				//ligne au dessus
        {
            temp.setX(x);
            temp.setY(y+colonne);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp))	//si un ennemie sur la case
                    break;						// les suivantes innaccessibles
            }
            else
                break;
        }
        for(int ligne=1;ligne<8;ligne++)				//ligne en dessous
        {
            temp.setX(x);
            temp.setY(y-ligne);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp))	//si un ennemie sur la case
                    break;						// les suivantes innaccessibles
            }
            else
                break;
        }
        return mouvementPossible;
    }

    public String toString() {
        int x = super.position.getX();
        return "[Tour " + x + "," + super.position.getY() + "]";
    }
}
