package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Formateur {
    private int id;
    private String mail;
    private String nom;
    private String prenom;
    private List<SessionCours> session=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<SessionCours> getSession() {
        return session;
    }

    public void setSession(List<SessionCours> session) {
        this.session = session;
    }

    public Formateur(int id, String mail, String nom, String prenom) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formateur formateur = (Formateur) o;
        return Objects.equals(mail, formateur.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
