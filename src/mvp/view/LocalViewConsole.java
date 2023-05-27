package mvp.view;

import mvp.presenter.LocalPresenter;
import mvp.presenter.SpecialLocalPresenter;
import proj.metier.Local;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class LocalViewConsole extends AbstractViewConsole<Local> implements SpecialLocalViewConsole{
    public LocalViewConsole(){

    }

    protected void modifier() {
        int nl = choixListe(ldatas) - 1;

        Local local = ldatas.get(nl);
        String sigle = modifyIfNotBlank("sigle", local.getSigle());
        int places = Integer.parseInt(modifyIfNotBlank("places", ""+local.getPlaces()));
        presenter.update(new Local(local.getId(), sigle,places));
        ldatas = presenter.getAll();
        affListe(ldatas);
    }

    protected void rechercher() {
        System.out.println("idLocal : ");
        int idLocal = sc.nextInt();
        presenter.search(idLocal);
    }


    protected void retirer() {
        int del = choixListe(ldatas)-1;
        Local Local=ldatas.get(del);
        presenter.remove(Local);
        ldatas=presenter.getAll();
        affList(ldatas);
    }

    protected void ajouter() {
        System.out.println("sigle :");
        String sigle = sc.nextLine();
        System.out.println("places :");
        int places = sc.nextInt();
        presenter.add(new Local(0,sigle, places));
        ldatas=presenter.getAll();
    }
    protected void special() {
        do {
            int ch = choixListe(Arrays.asList("locaux disponibles pour n élèves", "fin" ));

            switch (ch) {
                case 1:
                    GetAvailableLocals();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);    }


    @Override
    public void GetAvailableLocals() {
        System.out.println("nombre d'élèves inscrits : ");
        int inscrits=sc.nextInt();
        ((SpecialLocalPresenter)presenter).GetAvailableLocals(inscrits);
    }
}
