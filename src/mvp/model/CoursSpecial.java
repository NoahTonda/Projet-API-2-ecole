package mvp.model;

import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.SessionCours;

import java.util.List;

public interface CoursSpecial {
    List<SessionCours> sessionLoc(Cours cours);
    List<String> specialistes(Cours cours);

    List<SessionCours> sessionDate(Cours cours);
    List<Formateur> get_available_formateurs(Cours cours);

    }
