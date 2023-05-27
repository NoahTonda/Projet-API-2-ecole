package mvp.presenter;

import mvp.model.DAO;
import mvp.view.ViewInterface;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Maitrise;

import java.util.List;

public class MaitrisePresenter extends Presenter<Maitrise> implements SpecialMaitrisePresenter{
    private Presenter<Cours> coursPresenter;
    private Presenter<Formateur> formateurPresenter;

    public MaitrisePresenter(DAO<Maitrise> model, ViewInterface<Maitrise> view) {
        super(model, view);
    }
    @Override
    public void setCoursPresenter(Presenter<Cours> coursPresenter){this.coursPresenter=coursPresenter;}
    @Override
    public void setFormateurPresenter(Presenter<Formateur> formateurPresenter){this.formateurPresenter=formateurPresenter;}
    @Override
    public Cours choixCours() {
        return coursPresenter.selection();
    }
    @Override
    public Formateur choixFormateur() {
        return formateurPresenter.selection();
    }
}
