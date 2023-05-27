package mvp.presenter;

import mvp.model.CoursSpecial;
import mvp.model.DAO;
import mvp.model.LocalSpecial;
import mvp.view.ViewInterface;
import proj.metier.Local;
import proj.metier.SessionCours;

import java.time.LocalDate;
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

    @Override
    public void is_local_available(Local local, LocalDate debut, LocalDate fin) {
        int i =   ((LocalSpecial)model).is_local_available(local,debut,fin);
        if(i!=0) view.affMsg("local indisponnible");
        else System.out.println("local disponnible");;
    }
}


