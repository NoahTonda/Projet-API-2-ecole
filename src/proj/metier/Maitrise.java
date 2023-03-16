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
     * @param specialiste formateur de la matière
     */
    public void setSpecialiste(Formateur specialiste) {
        this.specialiste = specialiste;
    }

    /**
     *
     * @param description description du niveau de maitrise de la matière
     * @param specialiste formateur de la matière
     */
    public Maitrise(String description, Formateur specialiste) {
        this.description = description;
        this.specialiste = specialiste;
    }
}
