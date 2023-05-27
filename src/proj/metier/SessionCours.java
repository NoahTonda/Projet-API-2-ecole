package proj.metier;

import java.time.LocalDate;

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
    private LocalDate dateDebut;
    /**
     * date de la dernière session de cours
     */
    private LocalDate dateFin;
    /**
     * nombre d'élèves inscrits au cours
     */
    private int nbreInscrits;
    /**
     * Le cours donné lors des sessions
     */
    private Cours cours;



    /**
     * Le formateur qui donne le cours
     */
    private Formateur formateur;
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
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * setter de la date du premier cours
     * @param dateDebut la date du premier cours
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter de la date du dernier cours
     * @return la date du dernier cours
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * setter de la date du dernier cours
     * @param dateFin la date du dernier cours
     */
    public void setDateFin(LocalDate dateFin) {
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
     * getter du formateur donnant la session
     * @return le formateur donnant la session
     */
    public Formateur getFormateur() {
        return formateur;
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
     * setter du formateur donnant la session
     * @param formateur formateur donnant la session
     */
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    /**
     *
     * @param id
     * @param dateDebut
     * @param dateFin
     * @param nbreInscrits
     * @param cours
     * @param formateur
     * @param local
     */

    public SessionCours(int id, LocalDate dateDebut, LocalDate dateFin, int nbreInscrits, Cours cours,Formateur formateur, Local local) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbreInscrits = nbreInscrits;
        this.cours = cours;
        this.formateur=formateur;
        this.local = local;
    }

    /**
     * methode permettant d'afficher une session de cours
     * @return les informations de la session de cours
     */
    @Override
    public String toString() {
        return "cours :" + cours + "dateDebut :" + dateDebut + ", dateFin :" + dateFin + ", nbreInscrits :" + nbreInscrits +
                "\nformateur = " + formateur +
                "local = " + local;
    }
}
