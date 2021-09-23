package echec;

public class Executeur {
    public Executeur() {
    }

    public static void main(String[] args) {
        Partie partie = new Partie("Joueur blanc", "Joueur noir");
        partie.lancer();
    }
}
