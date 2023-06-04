package designpatterns.observer;


import java.util.*;

public class Formateur extends Observer{
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
    private List<SessionCours> session=new ArrayList<>();

    /**
     * getter de l'id du formateur
     * @return l'id du formateur
     */

    public int getId() {
        return id;
    }

    /**
     * setter de l'id du formateur
     * @param id l'id du formateur
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter du mail du formateur
     * @return mail du formateur
     */
    public String getMail() {
        return mail;
    }

    /**
     * setter du mail du formateur
     * @param mail mail du formateur
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * getter du nom du formateur
     * @return nom du formateur
     */
    public String getNom() {
        return nom;
    }

    /**
     *  setter du nom du formateur
     * @param nom nom du formateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter du prénom du formateur
     * @return prénom du formateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter du prénom du formateur
     * @param prenom prénom du formateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter de la liste des sessions de cours du formateur
     * @return la liste des sessions de cours du formateur
     */
    public List<SessionCours> getSession() {
        return session;
    }

    /**
     * setter de la liste des sessions de cours du formateur
     * @param session la liste des sessions de cours du formateur
     */
    public void setSession(List<SessionCours> session) {
        this.session = session;
    }

    /**
     *
     * @param id id du formateur
     * @param mail mail du formateur
     * @param nom nom du formateur
     * @param prenom prénom du formateur
     */
    public Formateur(int id, String mail, String nom, String prenom) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
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
    @Override
    public void update(String msg) {
        System.out.println(nom+" "+prenom+" a reçu le massage : "+msg);
    }

}
