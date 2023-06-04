package mvp.presenter;

import mvp.model.DAO;
import mvp.view.ViewInterface;

import java.util.List;

public abstract class Presenter<T> {
    protected  DAO<T> model;
    protected  ViewInterface<T> view;
    public Presenter(DAO<T> model, ViewInterface<T> view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {

        view.setListDatas(getAll());
    }

    public List<T> getAll(){
        List<T> l = model.getAll();
        return l;
    }

    public void add(T elt) {
        T nelt = model.add(elt);
        if(nelt!=null) view.affMsg("création de :"+nelt);
        else view.affMsg("erreur de création");

    }


    public void remove(T elt) {
        boolean ok = model.remove(elt);
        if(ok) view.affMsg("élément effacé");
        else view.affMsg("élément non effacé");

    }
    public void update(T elt) {
        T nelt  =model.update(elt);
        if(nelt==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+nelt);

    }

    public T search(int rech) {
         T elt= model.read(rech);
        if(elt==null) {
            view.affMsg("recherche infructueuse");
            return null;
        }
        else {
            view.affMsg(elt.toString());
            return elt;
        }
    }

    public T selection(){
       return  view.selectionner(model.getAll());
    }
}
