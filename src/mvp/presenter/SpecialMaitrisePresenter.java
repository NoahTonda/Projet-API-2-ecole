package mvp.presenter;

import proj.metier.Cours;
import proj.metier.Formateur;

public interface SpecialMaitrisePresenter {
    void setCoursPresenter(Presenter<Cours> coursPresenter);
    void setFormateurPresenter(Presenter<Formateur> formateurPresenter);
    Cours choixCours();
    Formateur choixFormateur();
}
