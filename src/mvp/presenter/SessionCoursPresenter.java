package mvp.presenter;

import mvp.model.DAO;
import mvp.view.ViewInterface;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Local;
import proj.metier.SessionCours;

public class SessionCoursPresenter extends Presenter<SessionCours> implements SpecialSessionCoursPresenter {
    private Presenter<Cours> coursPresenter;
    private Presenter<Local> localPresenter;
    private Presenter<Formateur> formateurPresenter;

    public SessionCoursPresenter(DAO<SessionCours> model, ViewInterface<SessionCours> view) {
        super(model, view);
    }

    @Override
    public void setCoursPresenter(Presenter<Cours> coursPresenter){this.coursPresenter=coursPresenter;}
    @Override
    public void setLocalPresenter(Presenter<Local> localPresenter){this.localPresenter=localPresenter;}
    @Override
    public void setFormateurPresenter(Presenter<Formateur> formateurPresenter){this.formateurPresenter=formateurPresenter;}
    @Override
    public Cours choixCours() {
        return coursPresenter.selection();
    }

    @Override
    public Local choixLocal() {
        return localPresenter.selection();
    }

    @Override
    public Formateur choixFormateur() {
        return formateurPresenter.selection();
    }
}
