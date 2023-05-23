package mvp.presenter;

import mvp.model.DAOSessionCours;
import mvp.view.SessionCoursViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.SessionCours;

import java.util.List;

public class SessionCoursPresenter {
    private DAOSessionCours model;
    private SessionCoursViewInterface view;

    private static final Logger logger = LogManager.getLogger(SessionCoursPresenter.class);

    public SessionCoursPresenter(DAOSessionCours model, SessionCoursViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }
    public void start() {
        view.setListDatas(getAll());
    }

    public List<SessionCours> getAll(){
        return model.getSessionCours();
    }
    public void addSessionCours(SessionCours SessionCours) {
        SessionCours f = model.addSessionCours(SessionCours);
        if(f!=null) view.affMsg("création de :"+f);
        else view.affMsg("erreur de création");
    }


    public void removeSessionCours(SessionCours SessionCours) {
        boolean ok = model.removeSessionCours(SessionCours);
        if(ok) view.affMsg("SessionCours effacé");
        else view.affMsg("SessionCours non effacé");
    }

    public SessionCours selectionner() {
        logger.info("appel de sélection");
        SessionCours f = view.selectionner(model.getSessionCours());
        return f;
    }

    public void update(SessionCours SessionCours) {
        SessionCours f =model.updateSessionCours(SessionCours);
        if(f==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+f);
    }

    public SessionCours search(int idSessionCours) {
        SessionCours f = model.readSessionCours(idSessionCours);
        if(f==null) view.affMsg("recherche infructueuse");
        else view.affMsg(f.toString());
        return f;
    }
}
