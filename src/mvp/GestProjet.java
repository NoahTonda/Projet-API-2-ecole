package mvp;

import mvp.model.*;
import mvp.presenter.CoursPresenter;
import mvp.presenter.FormateurPresenter;
import mvp.presenter.LocalPresenter;
import mvp.view.*;
import proj.metier.SessionCours;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestProjet {
    private DAOCours cm;
    private DAOFormateur fm;
    private DAOLocal lm;
    private DAOSessionCours sm;
    private CoursPresenter cp;
    private FormateurPresenter fp;
    private LocalPresenter lp;
    private SessionCoursPresenter sp;
    private CoursViewInterface cv;
    private FormateurViewInterface fv;
    private LocalViewInterface lv;
    private SessionCoursViewInterface sv;

    public void gestion(){
        cm=new CoursModelDB();
        fm=new FormateurModelDB();
        lm=new LocalModelDB();
        sm=new SessionCoursModelDB();

        cv=new CoursViewConsole();
        fv=new FormateurViewConsole();
        lv=new LocalViewConsole();
        sv=new SessionCoursViewConsole();

        cp=new CoursPresenter(cm,cv);
        fp=new FormateurPresenter(fm,fv);
        lp=new LocalPresenter(lm,lv);
        sp=new SessionCoursPresenter(sm,sv);

        List<String> loptions = Arrays.asList("Cours","formateur","Fin");
        do{
            int ch= Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cp.start();
                    break;
                case 2: fp.start();
                    break;
                case 3: lp.start();
                    break;
                case 4: sp.start();
                    break;
                case 5: System.exit(0);
            }
        }
        while(true);
    }
    public static void main(String[] args) {
        GestProjet gp = new GestProjet();
        gp.gestion();
    }
}
