package Projet;
/**
 * @author noaht
 * @version 1.0
 * @see Cours
 * @see Local
 */
public class SessionCours {
    /**
     * identifiant unique-numéro de la session de cours
     */
    private int id;
    /**
     * date de la première session de cours
     */
    private String dateDebut;
    /**
     * date de la dernière session de cours
     */
    private String dateFin;
    /**
     * nombre d'élèves inscrits au cours
     */
    private int nbreInscrits;
    /**
     * Le cours donné lors des sessions
     */
    private Cours cours;
    /**
     * le local dans lequel le cours est donné
     */
    private Local local;

    /**
     * getter de l'id du local
     * @return l'id du local
     */

    public int getId() {
        return id;
    }

    /**
     * setter de l'id du local
     * @param id l'id du local
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter de la date du premier cours
     * @return la date du premier cours
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * setter de la date du premier cours
     * @param dateDebut la date du premier cours
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter de la date du dernier cours
     * @return la date du dernier cours
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * setter de la date du dernier cours
     * @param dateFin la date du dernier cours
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * getter du nombre d'élèves inscrits
     * @return nombre d'élèves inscrits
     */
    public int getNbreInscrits() {
        return nbreInscrits;
    }

    /**
     * setter du nombre d'élèves inscrits
     * @param nbreInscrits nombre d'élèves inscrits
     */
    public void setNbreInscrits(int nbreInscrits) {
        this.nbreInscrits = nbreInscrits;
    }

    /**
     * getter du cours donné
     * @return cours donné
     */
    public Cours getCours() {
        return cours;
    }

    /**
     * setter du cours donné
     * @param cours cours donné
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /**
     * getter du local dans lequel les cours sont donné
     * @return local dans lequel les cours sont donné
     */
    public Local getLocal() {
        return local;
    }

    /**
     * setter du local dans lequel les cours sont donné
     * @param local local dans lequel les cours sont donné
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     *
     * @param id identifiant de la session
     * @param dateDebut date du premier cours
     * @param dateFin date du dernier cours
     * @param nbreInscrits nombre d'élèves inscrits au cours
     * @param cours cours donné lors des sessions
     * @param local local dans lequel les cours sont donnés
     */
    public SessionCours(int id, String dateDebut, String dateFin, int nbreInscrits, Cours cours, Local local) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbreInscrits = nbreInscrits;
        this.cours = cours;
        this.local = local;
    }
}
