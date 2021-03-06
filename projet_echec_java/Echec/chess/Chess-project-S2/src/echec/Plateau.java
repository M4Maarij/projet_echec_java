package echec;

import java.util.ArrayList;
import java.util.Iterator;

public class Plateau {
    ArrayList<Case> tabCase = new ArrayList<>();

    public Plateau () {
        for (int i=1;i<=8;i++) {
            for (int l=1;l<=8;l++) {
                Position p=new Position(l,i);
                Case c =new Case(p.clone());
                tabCase.add(c);
            }
        }
    }
    // Affichage de l'échiquier sur la console. (Premiere lettre de la piece  + la coueleur)
    public void afficherEchiquier() {
        int i=1;
        for (Case caseTemp:tabCase)
        {
            if (i!=caseTemp.getPosition().getY()) {	// fais passer a la ligne
                System.out.println();
                i++;
            }
            if (caseTemp.getPiece()!=null) {
                Piece p =caseTemp.getPiece(); // la piece
                String couleur; // la couleur
                if (p.getCouleur().equals(Couleur.WHITE))
                    couleur="blnc ";
                else
                    couleur="noir ";

                switch(p.getClass().getSimpleName()) {
                    case "Cavalier" :
                        System.out.print("C"+couleur);
                        break;
                    case "Reine" :
                        System.out.print("D"+couleur);
                        break;
                    case "Fou" :
                        System.out.print("F"+couleur);
                        break;
                    case "Pion" :
                        System.out.print("P"+couleur);
                        break;
                    case "Roi" :
                        System.out.print("R"+couleur);
                        break;
                    case "Tour" :
                        System.out.print("T"+couleur);
                        break;
                }

            }else {
                System.out.print("......");
            }
            System.out.print(" | ");

        }
        System.out.println("\n");
    }
    // initialisation
    public void initialisation() {
        Iterator<Piece> pieces = Piece.tabPiece.iterator();
        while(true) {
            while(pieces.hasNext()) {
                Piece p = pieces.next();
                Iterator<Case> cases = this.tabCase.iterator();
                while(cases.hasNext()) {
                    Case var  = cases.next();
                    if (p.position.equals(var.getPosition())) {
                        var.positionne(p);
                        break;
                    }
                }
            }
            return;
        }
    }
// Getter de la case définie par x,y
    public Case getCase(int x,int y) {
        int entree=(y-1)*8+x-1;
        assert this.tabCase.get(entree) != null;
        return this.tabCase.get(entree);
    }
// Mise a jour de l'échiquier et l'affichage.
    public void update() {
        this.initialisation();
        this.afficherEchiquier();
    }
}
