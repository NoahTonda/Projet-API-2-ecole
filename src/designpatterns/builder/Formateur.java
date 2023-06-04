package designpatterns.builder;

import java.util.*;

public class Formateur {
    /**
     * identifiant unique-numero de formateur
     */
    private int id;
    /**
     * mail unique du formateur
     */
    private String mail;
    /**
     * nom du formateur
     */
    private String nom;
    /**
     * prénom du formateur
     */
    private String prenom;
    /**
     * liste des sessions de cours données par le formateur
     */
    private Set<SessionCours> session=new HashSet<>();

    /**
     * getter de l'id du formateur
     * @return l'id du formateur
     */

    public int getId() {
        return id;
    }


    /**
     * getter du mail du formateur
     * @return mail du formateur
     */
    public String getMail() {
        return mail;
    }


    /**
     * getter du nom du formateur
     * @return nom du formateur
     */
    public String getNom() {
        return nom;
    }


    /**
     * getter du prénom du formateur
     * @return prénom du formateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * getter de la liste des sessions de cours du formateur
     * @return la liste des sessions de cours du formateur
     */
    public Set<SessionCours> getSession() {
        return session;
    }

    /**
     *
     * @param fb objet de la classe FormateurBuilder contenant les informations d'initialisation
     */
    public Formateur(FormateurBuilder fb) {
        this.id = fb.id;
        this.mail = fb.mail;
        this.nom = fb.nom;
        this.prenom = fb.prenom;
    }

    /**
     * test dégalité basé sur le mail du formateur
     * @param o autre formateur
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formateur formateur = (Formateur) o;
        return Objects.equals(mail, formateur.mail);
    }

    /**
     * calcul du hashcode basé sur le mail du formateur
     * @return hashcode du formateur
     */
    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
    /**
     * methode permettant d'afficher un formateur
     * @return les informations du formateur
     */
    @Override
    public String toString() {
        return "id :"+ id+", nom :'" + nom +"'"+ ", prenom :'" + prenom +"'"+ ", mail :'" + mail+"'\n";
    }
    public static class FormateurBuilder{
        /**
         * identifiant unique-numero de formateur
         */
        private int id;
        /**
         * mail unique du formateur
         */
        private String mail;
        /**
         * nom du formateur
         */
        private String nom;
        /**
         * prénom du formateur
         */
        private String prenom;

        public FormateurBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public FormateurBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public FormateurBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public FormateurBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }
        public Formateur build() throws Exception{
            if (mail==null||nom==null||prenom==null)throw new Exception("informations de construction incomplètes");
            return new Formateur(this);
        }

    }
}
