package mvp.view;

import mvp.presenter.SpecialCoursPresenter;
import proj.metier.Cours;
import proj.metier.Maitrise;

import java.util.Arrays;
import java.util.List;

import static utilitaires.Utilitaire.*;

public class CoursViewConsole extends AbstractViewConsole<Cours> implements SpecialCoursViewConsole {
    public CoursViewConsole(){

    }
    @Override
    protected void special() {
        System.out.println("numéro de ligne : ");
        int nl = choixListe(ldatas) - 1;
        Cours cours = ldatas.get(nl);
        cours = presenter.search(cours.getId());//pour obtenir les cours dans la liste
        do {
            int ch = choixListe(Arrays.asList("sessions avec local", "spécialistes du cours", "fin sessions entre 2 dates avec local","fin" ));

            switch (ch) {
                case 1:
                    sessionLoc(cours);
                    break;
                case 2:
                    specialistes(cours);
                    break;
                case 3:
                    sessionDate(cours);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }
    @Override
    public void sessionLoc(Cours cours) {
        ((SpecialCoursPresenter)presenter).sessionLoc(cours);
    }


    @Override
    public void specialistes(Cours cours) {
        ((SpecialCoursPresenter)presenter).specialistes(cours);
    }

    @Override
    public void sessionDate(Cours cours) {
        ((SpecialCoursPresenter)presenter).sessionDate(cours);
    }

    @Override
    protected void modifier() {
        int nl = choixListe(ldatas) - 1;

        Cours cours = ldatas.get(nl);
        String matiere = modifyIfNotBlank("matiere", cours.getMatiere());
        int nbh = Integer.parseInt(modifyIfNotBlank("nombre d'heure", "" + cours.getNbreHeures()));
        presenter.update(new Cours(cours.getId(), matiere, nbh));
        ldatas = presenter.getAll();//rafraichissement
        affListe(ldatas);
    }
    @Override
    protected void rechercher() {
        System.out.println("idcours : ");
        int idCours = sc.nextInt();
        presenter.search(idCours);
    }

    @Override
    protected void retirer() {
        int del = choixListe(ldatas)-1;
        Cours cours=ldatas.get(del);
        presenter.remove(cours);
        ldatas=presenter.getAll();
        affList(ldatas);;
    }
    @Override
    protected void ajouter() {
        System.out.println("matière :");
        String matiere = sc.nextLine();
        System.out.println("nombre d'heure");
        int nbh = sc.nextInt();
        presenter.add(new Cours(0,matiere, nbh));
    }

    @Override
    public Cours selectionner(List<Cours> ldatas) {
        int nl = choixListe(ldatas);
        Cours cours = ldatas.get(nl - 1);
        return cours;
    }


}
