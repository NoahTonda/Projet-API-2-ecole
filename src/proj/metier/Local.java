package proj.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author noaht
 * @version 1.0
 * @see SessionCours
 */
public class Local {
    /**
     * identifiant unique-numéro de local
     */
    private int id;
    /**
     * sigle du local
     */
    private String sigle;
    /**
     * nombre de places maximum disponibles dans le local
     */
    private int places;
    /**
     * Liste des sessions de cours ayant lieu dans ce local
     */
    private List<SessionCours> sessions=new ArrayList<>();
    /**
     *
     * @param id numero de local
     * @param sigle sigle du local
     * @param places nombre de places du local
     */
    public Local(int id, String sigle, int places) {
        this.id = id;
        this.sigle = sigle;
        this.places = places;
    }

    /**
     * getter de la liste des sessions dans le local
     * @return la liste des sessions dans le local
     */
    public List<SessionCours> getSession() {
        return sessions;
    }

    /**
     * setter de la liste des sessions dans le local
     * @param session la liste des sessions dans le local
     */
    public void setSession(List<SessionCours> session) {
        this.sessions = session;
    }


    /**
     * test d'égalité basé sur le sigle
     * @param o autre local
     * @return égalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return Objects.equals(sigle, local.sigle);
    }

    /**
     * calcul du hashcode basé sur le sigle
     * @return hashcode du local
     */
    @Override
    public int hashCode() {
        return Objects.hash(sigle);
    }

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
     * getter Sigle
     * @return Sigle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * setter Sigle
     * @param sigle le sigle du local
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * getter places d'un local
     * @return places d'un local
     */
    public int getPlaces() {
        return places;
    }

    /**
     * setter des places d'un local
     * @param places places d'un local
     */
    public void setPlaces(int places) {
        this.places = places;
    }
    /**
     * methode permettant d'afficher un local
     * @return les informations du local
     */
    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", sigle='" + sigle + '\'' +
                ", places=" + places +
                '}';
    }
}
