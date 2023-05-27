package mvp.presenter;

import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Local;

public interface SpecialSessionCoursPresenter {
    void setCoursPresenter(Presenter<Cours> coursPresenter);
    void setFormateurPresenter(Presenter<Formateur> formateurPresenter);
    void setLocalPresenter(Presenter<Local> localPresenter);

    Cours choixCours();
    Formateur choixFormateur();
    Local choixLocal();
}
