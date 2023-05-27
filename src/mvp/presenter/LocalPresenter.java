package mvp.presenter;

import mvp.model.CoursSpecial;
import mvp.model.DAO;
import mvp.model.LocalSpecial;
import mvp.view.ViewInterface;
import proj.metier.Local;
import proj.metier.SessionCours;

import java.util.List;


public class LocalPresenter extends Presenter<Local> implements SpecialLocalPresenter{
    public LocalPresenter(DAO<Local> model, ViewInterface<Local> view) {
        super(model, view);
    }

    @Override
    public void GetAvailableLocals(int inscrits) {
        List<Local> ll =   ((LocalSpecial)model).GetAvailableLocals(inscrits);
        if(ll==null || ll.isEmpty()) view.affMsg("aucun local trouvée");
        else view.affList(ll);

    }
}


