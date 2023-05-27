package mvp.view;

import mvp.presenter.CoursPresenter;
import mvp.presenter.MaitrisePresenter;
import proj.metier.*;
import utilitaires.Utilitaire;

import java.time.LocalDate;

import static utilitaires.Utilitaire.*;

public class MaitriseViewConsole extends AbstractViewConsole<Maitrise> {
    @Override
    protected void rechercher() {
        System.out.println("id du formateur : ");
        int idformateur = sc.nextInt();
        presenter.search(idformateur);
    }

    @Override
    protected void modifier() {
        int nl = choixListe(ldatas) - 1;
        Maitrise maitrise = ldatas.get(nl);
        System.out.println("description de la maitrise : ");
        String description = choixMaitrise();
        presenter.update(new Maitrise(description, maitrise.getSpecialiste(),maitrise.getSpecialite()));
        ldatas = presenter.getAll();
        affListe(ldatas);
    }

    @Override
    protected void ajouter() {
        System.out.println("Formateur :");
        Formateur f = ((MaitrisePresenter)presenter).choixFormateur();
        System.out.println("Cours :");
        Cours c = ((MaitrisePresenter)presenter).choixCours();
        System.out.println("description de la maitrise : ");
        String description = choixMaitrise();
        presenter.add(new Maitrise(description,f,c));
        ldatas=presenter.getAll();
    }

    @Override
    protected void special() {
        System.out.println("Il n'y a pas de requetes spéciales");
    }
}
