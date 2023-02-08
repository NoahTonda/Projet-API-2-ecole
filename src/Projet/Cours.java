package Projet;

import java.util.List;

public class Cours {
    private int id;
    private String matiere;
    private int nbreHeures;
    private List<Maitrise> specialite;
    private List<SessionCours> session;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public int getNbreHeures() {
        return nbreHeures;
    }

    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }

    public List<Maitrise> getSpecialite() {
        return specialite;
    }

    public void setSpecialite(List<Maitrise> specialite) {
        this.specialite = specialite;
    }

    public List<SessionCours> getSession() {
        return session;
    }

    public void setSession(List<SessionCours> session) {
        this.session = session;
    }

    public Cours(int id, String matiere, int nbreHeures) {
        this.id = id;
        this.matiere = matiere;
        this.nbreHeures = nbreHeures;
    }
}
