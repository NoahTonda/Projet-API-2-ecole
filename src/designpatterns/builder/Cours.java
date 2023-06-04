package designpatterns.builder;


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private Set<Maitrise> specialiste = new HashSet<>();
    /**
     * liste des sessions de ce cours
     */
    private Set<SessionCours> session=new HashSet<>();

    /**
     * getter de l'id du cours
     *
     * @return l'id du cours
     */
    public int getId() {
        return id;
    }

    /**
     * getter du nom de la matière
     *
     * @return nom de la matière
     */
    public String getMatiere() {
        return matiere;
    }

    /**
     * getter du nombre d'heure de cours
     *
     * @return nombre d'heure de cours
     */
    public int getNbreHeures() {
        return nbreHeures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return Objects.equals(matiere, cours.matiere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matiere);
    }

    /**
     * getter de la liste des spécialistes de ce cours
     *
     * @return liste des spécialistes de ce cours
     */
    public Set<Maitrise> getSpecialite() {
        return specialiste;
    }

    /**
     * getter des la liste des sessions de ce cours données
     *
     * @return la liste des sessions de ce cours données
     */
    public Set<SessionCours> getSession() {
        return session;
    }


    /**
     * constructeur paramétré
     * @param cb objet de la classe CoursBuilder contenant les informations d'initialisation
     */
    public Cours(CoursBuilder cb) {
        this.id = cb.id;
        this.matiere = cb.matiere;
        this.nbreHeures = cb.nbreHeures;
    }

    /**
     * methode permettant d'afficher un cours
     *
     * @return les informations du cours
     */
    @Override
    public String toString() {
        return "matiere :'" + matiere + "'" + ", nombre d'heures de cours :" + nbreHeures + "\n";

    }
    public static class CoursBuilder{
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

        public CoursBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CoursBuilder setMatiere(String matiere) {
            this.matiere = matiere;
            return this;
        }

        public CoursBuilder setNbreHeures(int nbreHeures) {
            this.nbreHeures = nbreHeures;
            return this;
        }

        public Cours build() throws Exception{
            if (matiere==null||nbreHeures<0)throw new Exception("informations de construction incomplètes");
            return new Cours(this);
        }
    }
}