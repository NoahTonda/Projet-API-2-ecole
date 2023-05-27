package mvp.presenter;

import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Maitrise;

public interface SpecialMaitrisePresenter {
    void setCoursPresenter(CoursPresenter coursPresenter);
    void setFormateurPresenter(Presenter<Formateur> formateurPresenter);
    Cours choixCours();
    Formateur choixFormateur();
}