package mvp.presenter;

import mvp.model.DAO;

import mvp.view.ViewInterface;
import proj.metier.Formateur;


public class FormateurPresenter extends Presenter<Formateur> {

    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view) {
        super(model, view);
    }
}
