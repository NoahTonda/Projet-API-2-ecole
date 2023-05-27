package mvp.view;

import mvp.presenter.SessionCoursPresenter;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Local;
import proj.metier.SessionCours;
import utilitaires.Utilitaire;

import java.time.LocalDate;

import static utilitaires.Utilitaire.*;
public class SessionCoursViewConsole extends AbstractViewConsole<SessionCours>{
    public SessionCoursViewConsole(){

    }
    protected void modifier() {
        int nl = choixListe(ldatas) - 1;

        SessionCours sessionCours = ldatas.get(nl);
        LocalDate dateDebut = LocalDate.parse(modifyIfNotBlank("date de début", String.valueOf(sessionCours.getDateDebut())));
        LocalDate fin = LocalDate.parse(modifyIfNotBlank("date de fin", String.valueOf(sessionCours.getDateFin())));
        int inscrits = Integer.parseInt(modifyIfNotBlank("nombre d'élèves inscrits", ""+sessionCours.getNbreInscrits()));
        System.out.println("cours");
        Cours c = ((SessionCoursPresenter)presenter).choixCours();
        System.out.println("Local");
        Local l = ((SessionCoursPresenter)presenter).choixLocal();
        System.out.println("Formateur");
        Formateur f = ((SessionCoursPresenter)presenter).choixFormateur();
        presenter.update(new SessionCours(sessionCours.getId(), dateDebut,fin,inscrits,c,f,l));
        ldatas = presenter.getAll();
        affListe(ldatas);
    }

    protected void rechercher() {
        System.out.println("idSessionCours : ");
        int idSessionCours = sc.nextInt();
        presenter.search(idSessionCours);
    }


    protected void ajouter() {
        System.out.println("date début :");
        LocalDate debut= Utilitaire.lecDate();
        System.out.println("date fin :");
        LocalDate fin= Utilitaire.lecDate();
        System.out.println("nombre d'élèves inscrits : ");
        int inscrits = sc.nextInt();
        System.out.println("Cours :");
        Cours c = ((SessionCoursPresenter)presenter).choixCours();
        System.out.println("Local :");
        Local l = ((SessionCoursPresenter)presenter).choixLocal();
        System.out.println("Formateur :");
        Formateur f = ((SessionCoursPresenter)presenter).choixFormateur();
        presenter.add(new SessionCours(0, debut,fin,inscrits,c,f,l));
        ldatas=presenter.getAll();
    }
    protected void special() {
        System.out.println("Il n'y a pas de requetes spéciales");
    }
}
