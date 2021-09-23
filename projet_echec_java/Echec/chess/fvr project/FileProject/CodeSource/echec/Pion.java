 package echec;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece implements Mouvement {
    public boolean _1erTour = true;

    public Pion(int var1, int var2, Couleur var3) {
        super(var1, var2, var3);
    }

    public boolean bouger(Position var1) {
        boolean var2 = super.bouger(var1);
        if (var2) {
            this._1erTour = false;
        }

        return var2;
    }
    //  les cases de déplacement du pion
    public List<Position> getMouvementPossible() {  //renvoi les cases de déplacement du pion
        int x=position.getX();
        int y=position.getY();
       List<Position> mouvementPossible = new ArrayList<Position>(aCote());
        if(this.getCouleur().equals(Couleur.WHITE)  )	//si la piece est blanche elle monte
        {
            Position temp1=new Position(x,y+1);
            if (temp1.inBounds() && !this.bloqueAmi(temp1))
                mouvementPossible.add(temp1.clone());
            Position temp2=new Position(x,y+2);
            if(_1erTour && !this.bloqueAmi(temp2)&& !this.bloqueEnnemi(temp1))//règle du premier tour
                mouvementPossible.add(temp2.clone());
        }
        if(this.getCouleur().equals(Couleur.BLACK))	//si la piece est noir elle descend
        {
            Position temp1=new Position(x,y-1);
            if(temp1.inBounds() && !this.bloqueAmi(temp1))
                mouvementPossible.add(temp1.clone());
            else if(this.bloqueAmi(temp1))
                return mouvementPossible;
            Position temp2=new Position(x,y-2);
            if(_1erTour && !this.bloqueAmi(temp2)&& !this.bloqueEnnemi(temp1))		//règle du premier tour +1 case
                mouvementPossible.add(temp2.clone());
        }
        return mouvementPossible;
    }

    // si une piece se trouve a coté
    private List<Position> aCote() {
        List<Position> mouvementPossible = new ArrayList<Position>();
        if( this.getCouleur().equals(Couleur.WHITE))		//si la piece est blanche
        {
            for (Piece other: Piece.getBlackPiece())
            {
                if(position.getY()-other.position.getY()==0)					// si un piece se trouve sur la meme ligne
                {
                    if(Math.abs(position.getX()-other.position.getX())==1)	//si la distance horizontal entre 2 piece est de 1
                    {
                        int x=other.position.getX();
                        int y=other.position.getY();
                        Position temp1=new Position(other.position.getX(),y+1);		//prend sa position
                        mouvementPossible.add(temp1);			// ajout aux mouvement possibles
                    }
                }
            }
        }
        if( this.getCouleur().equals(Couleur.BLACK)) //si la piece est noir
        {
            for (Piece other: Piece.getWhitePiece())
            {
                if(position.getY()-other.position.getY()==0) // si un piece se trouve sur la ligne au dessous
                {
                    if (Math.abs(position.getX()-other.position.getX())==1) // et collé à la piece actuelle en x
                    {
                        int x=other.position.getX();
                        int y=other.position.getY();
                        Position temp1=new Position(other.position.getX(),y-1);
                        mouvementPossible.add(temp1.clone());
                    }
                }
            }
        }
        return mouvementPossible;
    }

  @Override
    public String toString() {
        return "[Pion " +  position.getX() + "," +  position.getY() + "]";
    }
}
