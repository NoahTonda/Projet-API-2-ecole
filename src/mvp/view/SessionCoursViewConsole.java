package mvp.view;

import mvp.presenter.SessionCoursPresenter;
import proj.metier.SessionCours;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class SessionCoursViewConsole implements SessionCoursViewInterface{
    private SessionCoursPresenter presenter;
    private List<SessionCours> ls;
    private Scanner sc = new Scanner(System.in);
    public SessionCoursViewConsole(){

    }
    @Override
    public void setPresenter(SessionCoursPresenter presenter){this.presenter=presenter;}

    @Override
    public void setListDatas(List<SessionCours> sessionCourss) {
        this.ls = sessionCourss;
        affListe(ls);
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

            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));
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
                    return;
            }
        } while (true);
    }
    private void modifier() {
        int nl = choixListe(ls) - 1;

        SessionCours sessionCours = ls.get(nl);
        LocalDate dateDebut = LocalDate.parse(modifyIfNotBlank("date de début", String.valueOf(sessionCours.getDateDebut())));
        String nom = modifyIfNotBlank("nom", sessionCours.getNom());
        String prenom = modifyIfNotBlank("prenom", sessionCours.getPrenom());
        presenter.update(new SessionCours(sessionCours.getId(), mail,nom, prenom));
        ls = presenter.getAll();
        affListe(ls);
    }

    private void rechercher() {
        System.out.println("idSessionCours : ");
        int idSessionCours = sc.nextInt();
        presenter.search(idSessionCours);
    }


    private void retirer() {
        int del = choixListe(ls)-1;
        SessionCours SessionCours=ls.get(del);
        presenter.removeSessionCours(SessionCours);
        ls=presenter.getAll();
        affList(ls);
    }

    private void ajouter() {
        System.out.println("mail :");
        String mail = sc.nextLine();
        System.out.println("nom :");
        String nom = sc.nextLine();
        System.out.println("prénom : ");
        String prenom = sc.nextLine();
        presenter.addSessionCours(new SessionCours(0,mail, nom,prenom));
        ls=presenter.getAll();
    }

    @Override
    public SessionCours selectionner(List<SessionCours> ls) {
        int nl = choixListe(ls);
        SessionCours SessionCours = ls.get(nl - 1);
        return SessionCours;
    }
}
