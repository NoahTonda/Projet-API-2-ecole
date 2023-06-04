package designpatterns.composite;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cours extends Element{

    private String matiere;

    private int nbreHeures;

    private Set<Maitrise> specialiste = new HashSet<>();

    private Set<SessionCours> session=new HashSet<>();


    public String getMatiere() {
        return matiere;
    }


    public int getNbreHeures() {
        return nbreHeures;
    }

    public Set<Maitrise> getSpecialite() {
        return specialiste;
    }


    public Set<SessionCours> getSession() {
        return session;
    }

    public Cours(int id,String matiere, int nbreHeures) {
        super(id);
        this.matiere = matiere;
        this.nbreHeures = nbreHeures;
    }

    @Override
    public String toString() {
        return "matiere :'" + matiere + "'" + ", nombre d'heures de cours :" + nbreHeures + "\n";

    }
    @Override
    public int nbreHeuresTotales() {
        return nbreHeures;
    }

}