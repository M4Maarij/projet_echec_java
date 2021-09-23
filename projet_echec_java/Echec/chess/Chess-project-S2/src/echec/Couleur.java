package echec;

public enum Couleur {
    // Deux couleurs possible dans la scene, case blanche et noire.
    BLACK("BLACK"), WHITE("WHITE");

    private final String description;

    Couleur(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }
}
