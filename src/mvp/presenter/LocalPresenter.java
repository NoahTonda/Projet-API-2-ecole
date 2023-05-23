package mvp.presenter;

import mvp.model.DAOLocal;
import mvp.view.LocalViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Local;

import java.util.List;

public class LocalPresenter {
    private DAOLocal model;
    private LocalViewInterface view;

    private static final Logger logger = LogManager.getLogger(LocalPresenter.class);

    public LocalPresenter(DAOLocal model, LocalViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }
    public void start() {
        view.setListDatas(getAll());
    }

    public List<Local> getAll(){
        return model.getLocal();
    }
    public void addLocal(Local local) {
        Local f = model.addLocal(local);
        if(f!=null) view.affMsg("création de :"+f);
        else view.affMsg("erreur de création");
    }


    public void removeLocal(Local local) {
        boolean ok = model.removeLocal(local);
        if(ok) view.affMsg("Local effacé");
        else view.affMsg("Local non effacé");
    }

    public Local selectionner() {
        logger.info("appel de sélection");
        Local f = view.selectionner(model.getLocal());
        return f;
    }

    public void update(Local local) {
        Local f =model.updateLocal(local);
        if(f==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+f);
    }

    public Local search(int idLocal) {
        Local f = model.readLocal(idLocal);
        if(f==null) view.affMsg("recherche infructueuse");
        else view.affMsg(f.toString());
        return f;
    }
}
