package mvp.view;

import mvp.presenter.FormateurPresenter;
import proj.metier.Formateur;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class FormateurViewConsole extends AbstractViewConsole<Formateur>{
    public FormateurViewConsole(){

    }
    
    protected void modifier() {
        int nl = choixListe(ldatas) - 1;

        Formateur formateur = ldatas.get(nl);
        String mail = modifyIfNotBlank("mail", formateur.getMail());
        String nom = modifyIfNotBlank("nom", formateur.getNom());
        String prenom = modifyIfNotBlank("prenom", formateur.getPrenom());
        presenter.update(new Formateur(formateur.getId(), mail,nom, prenom));
        ldatas = presenter.getAll();
        affListe(ldatas);
    }

    protected void rechercher() {
        System.out.println("idFormateur : ");
        int idFormateur = sc.nextInt();
        presenter.search(idFormateur);
    }


    protected void retirer() {
        int del = choixListe(ldatas)-1;
        Formateur Formateur=ldatas.get(del);
        presenter.remove(Formateur);
        ldatas=presenter.getAll();
        affList(ldatas);
    }

    protected void ajouter() {
        System.out.println("mail :");
        String mail = sc.nextLine();
        System.out.println("nom :");
        String nom = sc.nextLine();
        System.out.println("prénom : ");
        String prenom = sc.nextLine();
        presenter.add(new Formateur(0,mail, nom,prenom));
        ldatas=presenter.getAll();
    }

    @Override
    protected void special() {
        System.out.println("Il n'y a pas de requetes spéciales");
    }

    @Override
    public Formateur selectionner(List<Formateur> ldatas) {
        int nl = choixListe(ldatas);
        Formateur Formateur = ldatas.get(nl - 1);
        return Formateur;
    }
}
