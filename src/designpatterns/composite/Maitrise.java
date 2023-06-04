package designpatterns.composite;


public class Maitrise {
    private String description;

    private Formateur specialiste;

    private Cours specialite;

    public Maitrise(String description, Formateur specialiste, Cours specialite) {
        this.description = description;
        this.specialiste = specialiste;
        this.specialite = specialite;
    }
    public String getDescription() {
        return description;
    }

    public Formateur getSpecialiste() {
        return specialiste;
    }

    public Cours getSpecialite() {
        return specialite;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setSpecialiste(Formateur specialiste) {
        this.specialiste = specialiste;
    }

    public void setSpecialite(Cours specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "specialiste=" + specialiste + "\nCours='" + specialite+ "\ndescription='" + description +"\n";
    }
}