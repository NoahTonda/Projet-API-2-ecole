package mvp.presenter;

import mvp.model.DAOFormateur;
import mvp.view.FormateurViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Formateur;

import java.util.List;

public class FormateurPresenter {
    private DAOFormateur model;
    private FormateurViewInterface view;

    private static final Logger logger = LogManager.getLogger(FormateurPresenter.class);

    public FormateurPresenter(DAOFormateur model, FormateurViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }
    public void start() {
        view.setListDatas(getAll());
    }

    public List<Formateur> getAll(){
        return model.getFormateur();
    }
    public void addFormateur(Formateur formateur) {
        Formateur f = model.addFormateur(formateur);
        if(f!=null) view.affMsg("création de :"+f);
        else view.affMsg("erreur de création");
    }


    public void removeFormateur(Formateur formateur) {
        boolean ok = model.removeFormateur(formateur);
        if(ok) view.affMsg("Formateur effacé");
        else view.affMsg("Formateur non effacé");
    }

    public Formateur selectionner() {
        logger.info("appel de sélection");
        Formateur f = view.selectionner(model.getFormateur());
        return f;
    }

    public void update(Formateur formateur) {
        Formateur f =model.updateFormateur(formateur);
        if(f==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+f);
    }

    public Formateur search(int idFormateur) {
        Formateur f = model.readFormateur(idFormateur);
        if(f==null) view.affMsg("recherche infructueuse");
        else view.affMsg(f.toString());
        return f;
    }
}
