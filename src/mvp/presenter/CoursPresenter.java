package mvp.presenter;

import mvp.model.CoursSpecial;
import mvp.model.DAO;
import mvp.view.ViewInterface;
import proj.metier.Cours;
import proj.metier.Maitrise;
import proj.metier.SessionCours;

import java.util.List;

public class CoursPresenter extends Presenter<Cours> implements SpecialCoursPresenter {

    public CoursPresenter(DAO<Cours> model, ViewInterface<Cours> view) {
        super(model, view);
    }

    public void sessionLoc(Cours cours) {
        List<SessionCours> lsc =   ((CoursSpecial)model).sessionLoc(cours);
        if(lsc==null || lsc.isEmpty()) view.affMsg("aucune session trouvée");
        else view.affList(lsc);
    }

    public void specialistes(Cours cours) {
        List<String> ls =   ((CoursSpecial)model).specialistes(cours);
        if(ls==null || ls.isEmpty()) view.affMsg("aucune session trouvée");
        else view.affList(ls);
    }

    public void sessionDate(Cours cours) {
        List<SessionCours> lsc =   ((CoursSpecial)model).sessionDate(cours);
        if(lsc==null || lsc.isEmpty()) view.affMsg("aucune session trouvée");
        else view.affList(lsc);
    }

}
