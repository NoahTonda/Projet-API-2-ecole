package designpatterns.builder;


public class Maitrise {
    /**
     * description du niveau de maitrise de la matière par le formateur (un peu compétent, moyennement compétent, très compétent)
     */
    private String description;
    /**
     * le formateur de la matière
     */
    private Formateur specialiste;
    /**
     * le cours dont le formateur est spécialiste
     */
    private Cours specialite;

    /**
     * getter de la description du niveau de maitrise de la matière
     * @return la description du niveau de maitrise de la matière
     */

    public String getDescription() {
        return description;
    }

    /**
     * getter du formateur de la matière
     * @return formateur de la matière
     */
    public Formateur getSpecialiste() {
        return specialiste;
    }


    /**
     * @param mb objet de la classe MaitriseBuilder contenant les informations d'initialisation
     */
    public Maitrise(MaitriseBuilder mb) {
        this.description = mb.description;
        this.specialiste = mb.specialiste;
        this.specialite=mb.specialite;
    }

    /**
     * getter du cours dont le formateur est spécialiste
     * @return specialite le cours dont le formateur est spécialiste
     */

    public Cours getSpecialite() {
        return specialite;
    }
    /**
     * methode permettant d'afficher la maitrise d'un prof et d'un cours
     * @return la maitrise d'un prof et d'un cours
     */
    @Override
    public String toString() {
        return "specialiste=" + specialiste + "\nCours='" + specialite+ "\ndescription='" + description +"\n";
    }
    public static class MaitriseBuilder{
        /**
         * description du niveau de maitrise de la matière par le formateur (un peu compétent, moyennement compétent, très compétent)
         */
        private String description;
        /**
         * le formateur de la matière
         */
        private Formateur specialiste;
        /**
         * le cours dont le formateur est spécialiste
         */
        private Cours specialite;

        public MaitriseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public MaitriseBuilder setSpecialiste(Formateur specialiste) {
            this.specialiste = specialiste;
            return this;
        }

        public MaitriseBuilder setSpecialite(Cours specialite) {
            this.specialite = specialite;
            return this;
        }
        public Maitrise build() throws Exception{
            if (description==null||specialiste==null||specialite==null)throw new Exception("informations de construction incomplètes");
            return new Maitrise(this);
        }

    }
}