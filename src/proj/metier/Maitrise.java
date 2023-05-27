package proj.metier;
/**
 * @author noaht
 * @version 1.0
 * @see Formateur
 */
public class Maitrise {
     /**
     * description du niveau de maitrise de la matière par le formateur (un peu compétent, moyennement compétent, très compétent)
     */
    private String description;
    /**
     * le formateur de la matière
     */
    private Formateur specialiste;
    /**
     * le cours dont le formateur est spécialiste
     */
    private Cours specialite;

    /**
     * getter de la description du niveau de maitrise de la matière
     * @return la description du niveau de maitrise de la matière
     */

    public String getDescription() {
        return description;
    }

    /**
     * setter de la description du niveau de maitrise de la matière
     * @param description la description du niveau de maitrise de la matière
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter du formateur de la matière
     * @return formateur de la matière
     */
    public Formateur getSpecialiste() {
        return specialiste;
    }

    /**
     * setter du formateur de la matière
     * @param specialiste le formateur de la matière
     */
    public void setSpecialiste(Formateur specialiste) {
        this.specialiste = specialiste;
    }


    /**
     *
     * @param description description du niveau de maitrise de la matière
     * @param specialiste le formateur de la matière
     */
    public Maitrise(String description, Formateur specialiste, Cours specialite) {
        this.description = description;
        this.specialiste = specialiste;
        this.specialite=specialite;
    }

    /**
     * getter du cours dont le formateur est spécialiste
     * @return specialite le cours dont le formateur est spécialiste
     */

    public Cours getSpecialite() {
        return specialite;
    }

    /**
     * setter du cours dont le formateur est spécialiste
     * @param specialite le cours dont le formateur est spécialiste
     */
    public void setSpecialite(Cours specialite) {
        this.specialite = specialite;
    }
    /**
     * methode permettant d'afficher la maitrise d'un prof et d'un cours
     * @return la maitrise d'un prof et d'un cours
     */
    @Override
    public String toString() {
        return "specialiste=" + specialiste + "\nCours='" + specialite+ "\ndescription='" + description +"\n";
    }
}
