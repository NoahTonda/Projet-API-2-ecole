package mvp;

import mvp.model.*;
import mvp.presenter.*;
import mvp.view.*;
import proj.metier.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestProjet {
    private DAO<Cours> cm;
    private DAO<Formateur> fm;
    private DAO<Local> lm;
    private DAO<SessionCours> sm;
    private DAO<Maitrise> mm;
    private CoursPresenter cp;
    private FormateurPresenter fp;
    private LocalPresenter lp;
    private SessionCoursPresenter sp;
    private MaitrisePresenter mp;
    private ViewInterface<Cours> cv;
    private ViewInterface<Formateur> fv;
    private ViewInterface<Local> lv;
    private ViewInterface<SessionCours> sv;
    private ViewInterface<Maitrise> mv;

    public void gestion(){
        cm=new CoursModelDB();
        cv=new CoursViewConsole();
        cp=new CoursPresenter(cm,cv);

        fm=new FormateurModelDB();
        fv=new FormateurViewConsole();
        fp=new FormateurPresenter(fm,fv);

        lm=new LocalModelDB();
        lv=new LocalViewConsole();
        lp=new LocalPresenter(lm,lv);

        sm=new SessionCoursModelDB();
        sv=new SessionCoursViewConsole();
        sp=new SessionCoursPresenter(sm,sv);

        mm=new MaitriseModelDB();
        mv=new MaitriseViewConsole();
        mp=new MaitrisePresenter(mm,mv);

        ((SpecialSessionCoursPresenter)sp).setCoursPresenter(cp);
        ((SpecialSessionCoursPresenter)sp).setFormateurPresenter(fp);
        ((SpecialSessionCoursPresenter)sp).setLocalPresenter(lp);

        ((SpecialMaitrisePresenter)mp).setCoursPresenter(cp);
        ((SpecialMaitrisePresenter)mp).setFormateurPresenter(fp);

        List<String> loptions = Arrays.asList("Cours","formateur","Local","Session Cours","Maitrise","Fin");
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
                case 5: mp.start();
                    break;
                case 6: System.exit(0);
            }
        }
        while(true);
    }
    public static void main(String[] args) {
        GestProjet gp = new GestProjet();
        gp.gestion();
    }
}
