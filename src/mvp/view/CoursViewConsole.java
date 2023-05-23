package mvp.view;

import mvp.presenter.CoursPresenter;
import proj.metier.Cours;
import proj.metier.SessionCours;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class CoursViewConsole implements CoursViewInterface {
    private CoursPresenter presenter;
    private List<Cours> lc;
    private Scanner sc = new Scanner(System.in);
    public CoursViewConsole(){

    }
    @Override
    public void setPresenter(CoursPresenter presenter){this.presenter=presenter;}

    @Override
    public void setListDatas(List<Cours> cours) {

        this.lc = cours;
        affListe(lc);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }
    public void menu() {
        do {

            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "special", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    special();
                    break;
                case 6:
                    return;
            }
        } while (true);
    }

    private void special() {
        System.out.println("numéro de ligne : ");
        int nl = choixListe(lc) - 1;
        Cours cours = lc.get(nl);
        cours = presenter.search(cours.getId());//pour obtenir les cours dans la liste
        do {
            int ch = choixListe(Arrays.asList("sessions avec local", "spécialistes du cours", "fin sessions entre 2 dates avec local","fin" ));

            switch (ch) {
                case 1:
                    presenter.sessionLoc(cours);
                    break;
                case 2:
                    presenter.specialistes(cours);
                    break;
                case 3:
                    presenter.sessionDate(cours);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }

    private void modifier() {
        int nl = choixListe(lc) - 1;

        Cours cours = lc.get(nl);
        String matiere = modifyIfNotBlank("matiere", cours.getMatiere());
        int nbh = Integer.parseInt(modifyIfNotBlank("nombre d'heure", "" + cours.getNbreHeures()));
        presenter.update(new Cours(cours.getId(), matiere, nbh));
        lc = presenter.getAll();//rafraichissement
        affListe(lc);
    }

    private void rechercher() {
        System.out.println("idcours : ");
        int idCours = sc.nextInt();
        presenter.search(idCours);
    }


    private void retirer() {
        int del = choixListe(lc)-1;
        Cours cours=lc.get(del);
        presenter.removeCours(cours);
        lc=presenter.getAll();
        affList(lc);;
    }

    private void ajouter() {
        System.out.println("matière :");
        String matiere = sc.nextLine();
        System.out.println("nombre d'heure");
        int nbh = sc.nextInt();
        presenter.addCours(new Cours(0,matiere, nbh));
    }

    @Override
    public Cours selectionner(List<Cours> lc) {
        int nl = choixListe(lc);
        Cours cours = lc.get(nl - 1);
        return cours;
    }

}
