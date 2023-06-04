package proj.metier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author noaht
 * @version 1.0
 * @see Maitrise
 * @see SessionCours
 */
public class Cours {

     /**
     * id du cours
     */
    private int id;
    /**
     * nom de la matière
     */
    private String matiere;
    /**
     * nombre d'heure de cette matière données
     */
    private int nbreHeures;
    /**
     * liste des spécialistes de cette matière
     */
    private List<Maitrise> specialiste=new ArrayList<>();
    /**
     * liste des sessions de ce cours
     */
    private List<SessionCours> session;

    /**
     * getter de l'id du cours
     * @return l'id du cours
     */
    public int getId() {
        return id;
    }

    /**
     * setter de l'id du cours
     * @param id l'id du cours
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter du nom de la matière
     * @return nom de la matière
     */
    public String getMatiere() {
        return matiere;
    }

    /**
     * setter du nom de la matière
     * @param matiere nom de la matière
     */
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    /**
     * getter du nombre d'heure de cours
     * @return nombre d'heure de cours
     */
    public int getNbreHeures() {
        return nbreHeures;
    }

    /**
     * setter du nombre d'heure de cours
     * @param nbreHeures nombre d'heure de cours
     */
    public void setNbreHeures(int nbreHeures) {
        this.nbreHeures = nbreHeures;
    }

    /**
     * getter de la liste des spécialistes de ce cours
     * @return liste des spécialistes de ce cours
     */
    public List<Maitrise> getSpecialite() {
        return specialiste;
    }

    /**
     * setter de la liste des spécialistes de ce cours
     * @param specialite liste des spécialistes de ce cours
     */
    public void setSpecialite(List<Maitrise> specialite) {
        this.specialiste = specialite;
    }

    /**
     * getter des la liste des sessions de ce cours données
     * @return la liste des sessions de ce cours données
     */
    public List<SessionCours> getSession() {
        return session;
    }

    /**
     * setter de la liste des sessions de ce cours données
     * @param session la liste des sessions de ce cours données
     */
    public void setSession(List<SessionCours> session) {
        this.session = session;
    }

    /**
     *
     * @param id identifiant du cours
     * @param matiere nom de la matière
     * @param nbreHeures nombre d'heure de cette matière données
     */
    public Cours(int id, String matiere, int nbreHeures) {
        this.id = id;
        this.matiere = matiere;
        this.nbreHeures = nbreHeures;
    }
    /**
     * methode permettant d'afficher un cours
     * @return les informations du cours
     */
    @Override
    public String toString() {
        return "matiere :'" + matiere +"'"+ ", nombre d'heures de cours :" + nbreHeures+"\n";

    }
}
