package echec;

import java.util.ArrayList;
import java.util.List;

public class Reine extends Piece implements Mouvement {
    public Reine(int x, int y, Couleur couleur) {
        super(x, y, couleur);
    }

    //  les cases de déplacement du fou
    public List<Position> getMouvementPossible() {
        ArrayList<Position> ret = new ArrayList<>();
        int var2 = this.position.getX();
        int var3 = this.position.getY();
        ret.addAll(new Fou(var2, var3, this.couleur, false).getMouvementPossible());
        ret.addAll(new Tour(var2, var3, this.couleur, false).getMouvementPossible());//mettre le 1er tour à faux
        return ret;
    }

    public String toString() {
        int x = super.position.getX();
        int y = super.position.getY();
        return "[Reine " + x + "," + y+ "]";
    }
}
