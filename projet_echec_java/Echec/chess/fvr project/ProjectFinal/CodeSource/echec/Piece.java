package echec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Piece implements Mouvement {
    protected Couleur couleur;
    protected Position position;
    private static List<Piece> pieceSupprimees = new ArrayList<>();
    public static List<Position> posPrisesBlanc = new ArrayList<>();
    public static List<Position> posPrisesNoir = new ArrayList<>();
    public static List<Piece> tabPiece = new ArrayList<>();
    protected List<Position> mouvementExecutable = new ArrayList<>();

    public Piece(int x, int y, Couleur c) {
        this.position = new Position(x, y);
        this.couleur = c;
        tabPiece.add(this);
    }

    public Piece(int x, int y, Couleur c,boolean t) {
        this.position = new Position(x, y);
        this.couleur = c;
        tabPiece.add(this);
    }

    public static List<Piece> getColoredPiece(Couleur var ) {
        List<Piece> tmp;
        if (var .equals(Couleur.BLACK)) {
            tmp = getBlackPiece();
        } else {
            tmp = getWhitePiece();
        }

        return tmp;
    }

    // parcourir la liste des pieces, retourner un tableau qui contient justement les pieces blanches
    public static List<Piece> getWhitePiece() {
        List<Piece> tempTab = new ArrayList<Piece>();
        for(Piece temp:tabPiece)
        {
            if(temp.couleur.equals(Couleur.WHITE))
                tempTab.add(temp);
        }
        return tempTab;
    }

    // parcourir la liste des pieces, retourner un tableau qui contient justement les pieces noires
    public static List<Piece> getBlackPiece(){
        List<Piece> tempTab = new ArrayList<Piece>();
        for(Piece temp:tabPiece)
        {
            if(temp.couleur.equals(Couleur.BLACK))
                tempTab.add(temp);
        }
        return tempTab;
    }

    public Position getPosition() {
        return this.position;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    //bloqué par piece ami
    protected boolean bloqueAmi(Position mouvement) //bloqué par piece ami
    {
        List<Piece> tempTab=new ArrayList<Piece>();
        List <Position> posPrises= new ArrayList<Position>();
        if (this.couleur==Couleur.WHITE) {
            tempTab= Piece.getWhitePiece();
            posPrises=posPrisesBlanc;

        }
        else if(this.couleur==Couleur.BLACK) {
            tempTab=Piece.getBlackPiece();
            posPrises=posPrisesNoir;

        }
        for (Piece count:tempTab)
        {
            if (count.position.equals(mouvement)) {
                posPrises.add(count.position);
                return true;
            }

        }
        return false;
    }

    //bloqué par piece ennemie
    protected boolean bloqueEnnemi(Position mouvement){
        List<Piece> tableauPieces=new ArrayList<Piece>();
        if (this.couleur==Couleur.BLACK)
            tableauPieces= Piece.getWhitePiece();
        else if(this.couleur==Couleur.WHITE)
            tableauPieces=Piece.getBlackPiece();

        for (Piece count:tableauPieces) {
            // si la position du piece est = le mouvement en argument, OK return true, bloqué!
            if (count.position.equals(mouvement))
                return true;
        }
        //Sinon on n'est pas bloqué
        return false;
    }

    // a chaque fois qu'un coup est joué, on fait un mise a jour des pieces.
    public static void updateAll() {
        Piece var1;
        Iterator<Piece> var0 = null;
        if(!tabPiece.isEmpty()){
            var0 = tabPiece.iterator();
            while(var0.hasNext()) {
                var1 = var0.next();
                var1.mouvementExecutable = var1.getMouvementExecutable();
                if (var1.couleur.equals(Couleur.WHITE)) {
                    posPrisesBlanc.addAll(var1.mouvementExecutable);
                } else {
                    posPrisesNoir.addAll(var1.mouvementExecutable);
                }
            }

        }

        if (!pieceSupprimees.isEmpty()) {
            var0 = pieceSupprimees.iterator();

            while(var0.hasNext()) {
                var1 = (Piece)var0.next();
                tabPiece.remove(var1);
            }
        }

    }
    //determine si la piece peut se deplacer à la position d'entrée
    public boolean bouger(Position pos){
        boolean ok=false;
        for (Position count: this.mouvementExecutable)
        {
            if(pos.equals(count))	//position de destination est compris dans les mouvements possibles
            {
                position=count;
                ok=true;
                break;
            }
        }
        return ok;
    }

    public static List<Piece> getPieceSupprimees() {
        return pieceSupprimees;
    }

    public static List<Position> getPosPrisesBlanc() {
        return posPrisesBlanc;
    }

    public static List<Position> getPosPrisesNoir() {
        return posPrisesNoir;
    }

    public static List<Piece> getTabPiece() {
        return tabPiece;
    }

    public List<Position> getMouvementExecutable() {
        return this.mouvementExecutable;
    }

    public void destroy() {
        pieceSupprimees.add(this);
    }

    @Override
    public String toString() {
        return "Piece [couleur=" + this.couleur + ", position=" + this.position + "]";
    }


}
