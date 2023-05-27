package mvp.presenter;

import proj.metier.Cours;

public interface SpecialCoursPresenter {
    void sessionLoc(Cours cours);
    void specialistes(Cours cours);
    void sessionDate(Cours cours);
    void get_available_formateurs(Cours cours);
}
