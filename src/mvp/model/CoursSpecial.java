package mvp.model;

import proj.metier.Cours;
import proj.metier.SessionCours;

import java.util.List;

public interface CoursSpecial {
    public List<SessionCours> sessionLoc(Cours cours);
    public List<String> specialistes(Cours cours);

    public List<SessionCours> sessionDate(Cours cours);
    }
