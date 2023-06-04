package designpatterns.observer;


import designpatterns.composite.Element;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cours  {


    private int id;

    private String matiere;

    private int nbreHeures;

    private Set<Maitrise> specialiste = new HashSet<>();

    private Set<SessionCours> session=new HashSet<>();


    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }

    public String getMatiere() {
        return matiere;
    }


    public int getNbreHeures() {
        return nbreHeures;
    }
    public Set<Maitrise> getSpecialiste() {
        return specialiste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id == cours.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Set<SessionCours> getSession() {
        return session;
    }

    public Cours(int id,String matiere, int nbreHeures) {
        this.id=id;
        this.matiere = matiere;
        this.nbreHeures = nbreHeures;
    }

    @Override
    public String toString() {
        return "matiere :'" + matiere + "'" + ", nombre d'heures de cours :" + nbreHeures + "\n";

    }
}