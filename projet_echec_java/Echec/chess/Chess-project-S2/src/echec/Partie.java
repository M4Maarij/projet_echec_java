package echec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Partie {
    private Joueur joueurActuel; // joueur de la partie en cours
    private int numTour = 1; // nombre de tours
    ArrayList<Joueur> listeJoueurs = new ArrayList<>(); // liste des joueurs
    Plateau plateau; // Plateau
    private Scanner entree; // entrée, demander au joueur de soisir et entrer des choix
    boolean fini; // partie fini?

    public Partie(String nom, String nomEnnemie) {
        this.entree = new Scanner(System.in);
        Joueur joueur1 = new Joueur(nom, Couleur.WHITE);
        Joueur joueur2 = new Joueur(nomEnnemie, Couleur.BLACK);
        this.listeJoueurs.add(joueur1);
        this.listeJoueurs.add(joueur2);
        this.initialisation();
        joueur1.genererPieceJoueur(); // cases Blanches
        joueur2.genererPieceJoueur(); // cases Noires
        this.fini = false; // pas encore, on a commencé
        Piece.updateAll(); // chargement de plateau, cases etc.
    }


    // initialisation du jeu
    private void initialisation() {
        String mode = "";
        do {
            System.out.println("Vous devez choisir un mode de lancement, Lancer en mode\ntest z| echec et mat | normal | ");
            mode = this.entree.nextLine();
        } while(!mode.equals("test") && !mode.equals("normal") && !mode.equals("echec et mat"));
        if(mode.equals("normal")) {
            this.init_pion();
            this.init_fou();
            this.init_tour();
            this.init_cavalier();
            this.init_royaux();
        } else if (mode.equals("test")) {
            this.test_init();
        } else if (mode.contains("echec")||mode.contains("et")||mode.contains("mat")) {
            this.echecMat_init();
        }

        this.plateau = new Plateau();
        this.plateau.initialisation();
    }

    public Joueur getJoueurActuel() {
        return this.joueurActuel;
    }

    public int getNumTour() {
        return this.numTour;
    }

    public ArrayList<Joueur> getListeJoueurs() {
        return this.listeJoueurs;
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public Scanner getEntree() {
        return this.entree;
    }

    public boolean isFini() {
        return this.fini;
    }

    // test avec TOUR, PION, ROI
    private void test_init() {
        new Tour(4, 1, Couleur.BLACK);
        new Tour(4, 2, Couleur.BLACK);
        new Pion(4, 5, Couleur.WHITE);
        new Pion(3, 5, Couleur.BLACK);
        new Roi(5, 5, Couleur.WHITE);
        new Roi(2, 2, Couleur.BLACK);
        System.out.println(Roi.getRoiCouleur(Couleur.WHITE));
    }
// test Echec et mat
    private void echecMat_init() {
        new Tour(3, 2, Couleur.BLACK);
        new Tour(2, 3, Couleur.BLACK);
        new Tour(2, 7, Couleur.BLACK);
        new Roi(2, 1, Couleur.WHITE);
        new Roi(2, 5, Couleur.BLACK);
    }

    // initiation pion
    private void init_pion() {
        byte var2 = 2; // afin de ne pas depasser 127, petit chiffre sur Y,  de 1 .....à....8

        // Creer des pion sur (1,2), (2,2), (3,2), ......(8,2)
        int x;
        for(x = 1; x <= 8; ++x) {
            new Pion(x, var2, Couleur.WHITE);
        }

        var2 = 7;
        // Creer des pions sur (1,7),(2,7), ............(8,7)
        for(x = 1; x <= 8; ++x) {
            new Pion(x, var2, Couleur.BLACK);
        }
    }

    private void init_tour() {
        new Tour(1, 8, Couleur.BLACK);
        new Tour(8, 8, Couleur.BLACK);
        new Tour(1, 1, Couleur.WHITE);
        new Tour(8, 1, Couleur.WHITE);
    }

    private void init_cavalier() {
        new Cavalier(2, 8, Couleur.BLACK);
        new Cavalier(7, 8, Couleur.BLACK);
        new Cavalier(2, 1, Couleur.WHITE);
        new Cavalier(7, 1, Couleur.WHITE);
    }

    private void init_fou() {
        new Fou(3, 8, Couleur.BLACK);
        new Fou(6, 8, Couleur.BLACK);
        new Fou(3, 1, Couleur.WHITE);
        new Fou(6, 1, Couleur.WHITE);
    }

    private void init_royaux() {
        new Reine(5, 1, Couleur.WHITE);
        new Reine(5, 8, Couleur.BLACK);
        new Roi(4, 8, Couleur.BLACK);
        new Roi(4, 1, Couleur.WHITE);
    }

    public void lancer() {
        while(!this.fini) {
            this.debutTour();
        }
    }

    public void finDuTour() {
        this.numTour++;
    }

    // Fin de la partie, mais on demande s'il vous recommencer directement sotn  arreter l'application
    public void finDePartie() {
        System.out.println("Fini!");
        System.out.println("Voulez vous rejouez une partie ? (oui ou non)");
        String choix = ".";
        // assurer que l'utilisateur donne un oui ou non par la boulce  do while
        do {
            do {
                choix = this.entree.nextLine();
            } while(choix.equals("."));
        } while(!choix.equals("oui") && !choix.equals("non"));
        if (choix.equals("oui")) {
            String choix2 = ".";
            // tant que l'entrée est différente de oui et non, alors re-demander.
            do {
                do {
                    System.out.println("Voulez vous changer de couleur? (oui ou non)");
                    choix2 = this.entree.nextLine();
                } while(choix2.equals("."));
            } while(!choix2.equals("oui") && !choix2.equals("non"));
            if (choix2.equals("oui")) {
                // OK recommencer
                this.recommencerPartie(2);
            } else {
                // fin!
                this.recommencerPartie(1);
            }
        }

    }

    //lancer le tour d'un des 2 joueurs
    public void debutTour() {
        this.update();
        if (!this.fini) { // jeu pas encore fini
            if (this.numTour % 2 == 1) { // joueur dont le numéro est impaire
                this.joueurActuel = this.listeJoueurs.get(0);
            } else { // joueur dont le numéro est paire.
                this.joueurActuel = this.listeJoueurs.get(1);
            }

            System.out.println("C'est au " + this.joueurActuel.getNom() + " de jouer");
            this.jouer(this.joueurActuel);
        } else {
            this.finDePartie(); // sinon  la variable fin est en  true, alors terminer le jeu
        }
    }

    // un joueur joue
    private void jouer(Joueur joueur) {
        Position depart = null; // position de départ
        Position arrivee = null; // position d'arrivée
        Piece piece = null; // la piece séléctionnée
        Couleur couleur = this.joueurActuel.getCouleur();

        // tand que le jeux peut manger il mange
        // this.joueurActuel.mangerT2()
        for(boolean temporaire = false; !temporaire; temporaire = this.joueurActuel.bougerT2(piece, depart, arrivee)) {
            boolean positionDepart = false;
            while(!positionDepart) { // La position de départ est fausse?, ok redemarrer le jeu
                if (!Roi.getRoiCouleur(couleur).isEchec()) {
                    System.out.println(joueur.pieces); // Pieces du joueur actuel
                    depart = this.getEntree(1);	//la position  de départ
                    piece = this.joueurActuel.bougerT1(depart); //une piece allié est à cette position
                } else {
                    System.out.println(Roi.getRoiCouleur(couleur));
                    depart = this.getEntree(1);
                    piece = this.joueurActuel.bougerRoiT1(depart);
                }

                if (piece != null) {
                    positionDepart = true;
                }
            }

            positionDepart = false;
            arrivee = this.getEntree(2); // l'arrivée est reccupéré
        }

        Case caseTemp = this.plateau.getCase(depart.getX(), depart.getY());
        caseTemp.vider(); // supprimer la case.
        System.out.println(caseTemp.getPosition());
        this.finDuTour();
    }

    private Position getEntree(int n)
    {
        String temp=null;
        if(n==1)
            temp="entrez la position de depart (x puis y)";
        if(n==2)
            temp="entrez la position d'arrivé (x puis y)";

        System.out.println(temp);
        int x=0, y=0;
        do{
            x = entree.nextInt();
            y = entree.nextInt();
        }
        while(x==0 && y==0);
        System.out.println(x+"  "+y);
        return new Position(x,y);
    }

    private void update() {
        this.plateau.update();
        Iterator<Roi> piecesRoi = Roi.tabRoi.iterator();
        ((Joueur)this.listeJoueurs.get(0)).genererPieceJoueur();
        ((Joueur)this.listeJoueurs.get(1)).genererPieceJoueur();
        Piece.updateAll(); // mettre a jour les pieces.
        while(true) {
            while(piecesRoi.hasNext()) {
                Roi roi = (Roi)piecesRoi.next(); // next roi
                String var3 = roi.update(); // Echec et pat, ou bien echec et mat
                Couleur couleurRoi = roi.getCouleur();
                if (roi.echec && !roi.echecEtMat) {
                    System.out.println("Le roi " + roi.getCouleur() + " est en echec");
                } else if ((roi.echec && roi.echecEtMat) || roi.echecEtPat) {
                    this.fini = true;
                    System.out.println("Le roi " + roi.couleur + " est en " + var3 + "\n");
                    if (couleurRoi.equals(Couleur.WHITE)) {
                        System.out.print("Le joueur " + this.listeJoueurs.get(0));
                    } else {
                        System.out.print("Le joueur " + this.listeJoueurs.get(1));
                    }
                    System.out.println(" a perdu");
                }
            }

            return;
        }
    }

    // recommencer la partie, vider les tableaux des pieces, le roi et mettre la fin du jeu en false.
    public void recommencerPartie(int choix) {
        if (choix == 1) {
            Piece.tabPiece.clear();
            Roi.tabRoi.clear();
            this.initialisation();
            this.fini = false;
            Piece.updateAll();
        } else if (choix == 2) {
            Piece.tabPiece.clear();
            Roi.tabRoi.clear();
            Joueur var2 = (Joueur)this.listeJoueurs.get(0);
            this.listeJoueurs.set(0, (Joueur)this.listeJoueurs.get(1));
            this.listeJoueurs.set(1, var2);
            this.initialisation();
            this.fini = false;
            Piece.updateAll();
        }

    }
}
