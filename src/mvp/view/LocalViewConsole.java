package mvp.view;

import mvp.presenter.LocalPresenter;
import proj.metier.Local;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.choixListe;

public class LocalViewConsole implements LocalViewInterface{
    private LocalPresenter presenter;
    private List<Local> lf;
    private Scanner sc = new Scanner(System.in);
    public LocalViewConsole(){

    }
    @Override
    public void setPresenter(LocalPresenter presenter){this.presenter=presenter;}

    @Override
    public void setListDatas(List<Local> locaux) {
        this.lf = locaux;
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

        Local local = lf.get(nl);
        String sigle = modifyIfNotBlank("sigle", local.getSigle());
        int places = Integer.parseInt(modifyIfNotBlank("places", ""+local.getPlaces()));
        presenter.update(new Local(local.getId(), sigle,places));
        lf = presenter.getAll();
        affListe(lf);
    }

    private void rechercher() {
        System.out.println("idLocal : ");
        int idLocal = sc.nextInt();
        presenter.search(idLocal);
    }


    private void retirer() {
        int del = choixListe(lf)-1;
        Local Local=lf.get(del);
        presenter.removeLocal(Local);
        lf=presenter.getAll();
        affList(lf);
    }

    private void ajouter() {
        System.out.println("sigle :");
        String sigle = sc.nextLine();
        System.out.println("places :");
        int places = sc.nextInt();
        presenter.addLocal(new Local(0,sigle, places));
        lf=presenter.getAll();
    }

    @Override
    public Local selectionner(List<Local> lf) {
        int nl = choixListe(lf);
        Local Local = lf.get(nl - 1);
        return Local;
    }
}
