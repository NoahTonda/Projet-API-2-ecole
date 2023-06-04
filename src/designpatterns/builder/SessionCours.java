package designpatterns.builder;

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
     * getter de la date du premier cours
     * @return la date du premier cours
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * getter de la date du dernier cours
     * @return la date du dernier cours
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * getter du nombre d'élèves inscrits
     * @return nombre d'élèves inscrits
     */
    public int getNbreInscrits() {
        return nbreInscrits;
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
     * getter du local dans lequel les cours sont donné
     * @return local dans lequel les cours sont donné
     */
    public Local getLocal() {
        return local;
    }

    /**
     *
     * @param scb objet de la classe SessionCoursBuilder contenant les informations d'initialisation
     */

    public SessionCours(SessionCoursBuilder scb) {
        this.id = scb.id;
        this.dateDebut = scb.dateDebut;
        this.dateFin = scb.dateFin;
        this.nbreInscrits = scb.nbreInscrits;
        this.cours = scb.cours;
        this.formateur=scb.formateur;
        this.local = scb.local;
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
    public static class SessionCoursBuilder {
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

        public SessionCoursBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public SessionCoursBuilder setDateDebut(LocalDate dateDebut) {
            this.dateDebut = dateDebut;
            return this;
        }

        public SessionCoursBuilder setDateFin(LocalDate dateFin) {
            this.dateFin = dateFin;
            return this;
        }

        public SessionCoursBuilder setNbreInscrits(int nbreInscrits) {
            this.nbreInscrits = nbreInscrits;
            return this;
        }

        public SessionCoursBuilder setCours(Cours cours) {
            this.cours = cours;
            return this;
        }

        public SessionCoursBuilder setFormateur(Formateur formateur) {
            this.formateur = formateur;
            return this;
        }

        public SessionCoursBuilder setLocal(Local local) {
            this.local = local;
            return this;
        }

        public SessionCours build() throws Exception {
            if (dateDebut == null || dateFin == null ||dateDebut.isAfter(dateFin)||nbreInscrits <= 0 || cours == null || formateur == null)
                throw new Exception("informations de construction incomplètes");
            return new SessionCours(this);

        }
    }
}

