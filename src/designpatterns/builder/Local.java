package designpatterns.builder;

import java.util.*;

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
    private Set<SessionCours> sessions=new HashSet<>();
    /**
     *
     * @param lb objet de la classe LocalBuilder contenant les informations d'initialisation
     */
    public Local(LocalBuilder lb) {
        this.id = lb.id;
        this.sigle = lb.sigle;
        this.places = lb.places;
    }

    /**
     * getter de la liste des sessions dans le local
     * @return la liste des sessions dans le local
     */
    public Set<SessionCours> getSession() {
        return sessions;
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
     * getter Sigle
     * @return Sigle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * getter places d'un local
     * @return places d'un local
     */
    public int getPlaces() {
        return places;
    }
    /**
     * methode permettant d'afficher un local
     * @return les informations du local
     */
    @Override
    public String toString() {
        return "sigle :'" + sigle +"'"+ ", places :" + places+"\n";
    }
    public static class LocalBuilder{
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
        public LocalBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public LocalBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        public LocalBuilder setPlaces(int places) {
            this.places = places;
            return this;
        }
        public Local build() throws Exception{
            if (sigle==null||places<=0)throw new Exception("informations de construction incomplètes");
            return new Local(this);

        }
    }
}

