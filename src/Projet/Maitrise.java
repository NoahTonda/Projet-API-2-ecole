package Projet;

public class Maitrise {
    private String description;
    private Formateur specialiste;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Formateur getSpecialiste() {
        return specialiste;
    }

    public void setSpecialiste(Formateur specialiste) {
        this.specialiste = specialiste;
    }

    public Maitrise(String description, Formateur specialiste) {
        this.description = description;
        this.specialiste = specialiste;
    }
}
