package designpatterns.composite;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Formateur {

    private int id;
    private String mail;
    private String nom;

    private String prenom;

    private Set<SessionCours> session=new HashSet<>();

    public String getMail() {
        return mail;
    }


    public String getNom() {
        return nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public Set<SessionCours> getSession() {
        return session;
    }


    public Formateur(int id,String mail, String nom, String prenom) {
        this.id=id;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "id :"+ id+", nom :'" + nom +"'"+ ", prenom :'" + prenom +"'"+ ", mail :'" + mail+"'\n";
    }
}
