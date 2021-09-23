package echec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Roi extends Piece implements Mouvement {
    public static List<Roi> tabRoi = new ArrayList<>();
    boolean echec = false;
    boolean echecEtMat = false;
    boolean echecEtPat = false;
    boolean premierTour = true;

    public Roi(int x, int y, Couleur c) {
        super(x, y, c);
        tabRoi.add(this);
    }

    public boolean isEchec() {
        return this.echec;
    }

    public boolean isEchecEtMat() {
        return this.echecEtMat;
    }

    // mouvement possible du roi
    // Le Roi (ayant une croix) se place à côté de sa Dame au centre de sa rangée.
    public List<Position> getMouvementPossible() {
        int x=position.getX();
        int y=position.getY();
        List<Position> mouvementPossible = new ArrayList<>();
        for (int c=-1;c<=1;c++)	//les colonnes
        {
            for(int l=-1;l<=1;l++)	//les lignes
            {
                Position temp=new Position(x+l,y+c);
                if((l!=0 || c!=0 )&& temp.inBounds() && !this.bloqueAmi(temp))	//ne pas mettre la position actuelle dans la matrice
                {
                    mouvementPossible.add(temp.clone());//obtient carré autour du roi
                }
            }
        }
        mouvementPossible=MouvementAutorises(mouvementPossible); //car roi ne peut pas se sacrifier
        return mouvementPossible;
    }

    public List<Position> MouvementAutorises(List<Position> mouvement){
        List <Position> posPrises;
        List <Position> positionEnlevees= new ArrayList<>();
        if (couleur.equals(Couleur.WHITE) )
            posPrises=posPrisesNoir;
        else
            posPrises=posPrisesBlanc;
        for (Position pos:mouvement) {
            for (Position pos2:posPrises) {
                if (pos.equals(pos2)) {
                    positionEnlevees.add(pos);
                    break;
                }
            }
        }
        mouvement.removeAll(positionEnlevees);
        return mouvement;
    }


   public String update()
    {
        String result="";
        List<Position> tempTab;
        if (couleur.equals(Couleur.BLACK))
            tempTab=posPrisesBlanc;
        else
            tempTab=posPrisesNoir;
        if (echec)			// reset de la variable
            echec=false;
        for (Position pos:tempTab) {	// verifie si une piece peut manger le roi
            if(pos.equals(position))
            {
                echec=true;
                result="echec";
            }
        }
        if(!echec && this.mouvementExecutable.isEmpty() &&
                Piece.getColoredPiece(couleur).size()==1)
        {
            echecEtPat=true;
            result="echec et pat";      /**/
        }
        if(echec && this.mouvementExecutable.isEmpty())
        {
            result="echec et mat";
            echecEtMat=true;
        }
        return result;
    }

    static Roi getRoiCouleur(Couleur c) {
        for (Roi r:Roi.tabRoi) {
            if (r.couleur.equals(c))
                return r;
        }
        return null;
    }

    public String toString() {
        int x = super.position.getX();
        return "[Roi " + x + "," + super.position.getY() + "]";
    }
}
