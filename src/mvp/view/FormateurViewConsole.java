package mvp.view;

import mvp.presenter.FormateurPresenter;
import proj.metier.Formateur;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class FormateurViewConsole implements FormateurViewInterface{
    private FormateurPresenter presenter;
    private List<Formateur> lf;
    private Scanner sc = new Scanner(System.in);
    public FormateurViewConsole(){

    }
    @Override
    public void setPresenter(FormateurPresenter presenter){this.presenter=presenter;}

    @Override
    public void setListDatas(List<Formateur> formateurs) {
        this.lf = formateurs;
        affListe(lf);
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
        int nl = choixListe(lf) - 1;

        Formateur formateur = lf.get(nl);
        String mail = modifyIfNotBlank("mail", formateur.getMail());
        String nom = modifyIfNotBlank("nom", formateur.getNom());
        String prenom = modifyIfNotBlank("prenom", formateur.getPrenom());
        presenter.update(new Formateur(formateur.getId(), mail,nom, prenom));
        lf = presenter.getAll();
        affListe(lf);
    }

    private void rechercher() {
        System.out.println("idFormateur : ");
        int idFormateur = sc.nextInt();
        presenter.search(idFormateur);
    }


    private void retirer() {
        int del = choixListe(lf)-1;
        Formateur Formateur=lf.get(del);
        presenter.removeFormateur(Formateur);
        lf=presenter.getAll();
        affList(lf);
    }

    private void ajouter() {
        System.out.println("mail :");
        String mail = sc.nextLine();
        System.out.println("nom :");
        String nom = sc.nextLine();
        System.out.println("prénom : ");
        String prenom = sc.nextLine();
        presenter.addFormateur(new Formateur(0,mail, nom,prenom));
        lf=presenter.getAll();
    }

    @Override
    public Formateur selectionner(List<Formateur> lf) {
        int nl = choixListe(lf);
        Formateur Formateur = lf.get(nl - 1);
        return Formateur;
    }
}
