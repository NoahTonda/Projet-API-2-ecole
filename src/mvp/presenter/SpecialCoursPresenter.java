package mvp.presenter;

import proj.metier.Cours;
import proj.metier.Maitrise;

public interface SpecialCoursPresenter {
    void sessionLoc(Cours cours);
    void specialistes(Cours cours);
    void sessionDate(Cours cours);
    }
