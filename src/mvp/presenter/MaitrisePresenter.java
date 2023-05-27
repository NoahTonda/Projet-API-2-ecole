package mvp.presenter;

import mvp.model.DAO;
import mvp.view.ViewInterface;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Maitrise;


public class MaitrisePresenter extends Presenter<Maitrise> implements SpecialMaitrisePresenter{
    private CoursPresenter coursPresenter;
    private Presenter<Formateur> formateurPresenter;

    public MaitrisePresenter(DAO<Maitrise> model, ViewInterface<Maitrise> view) {
        super(model, view);
    }
    @Override
    public void setCoursPresenter(CoursPresenter coursPresenter){this.coursPresenter=coursPresenter;}
    @Override
    public void setFormateurPresenter(Presenter<Formateur> formateurPresenter){this.formateurPresenter=formateurPresenter;}
    @Override
    public void add(Maitrise elt) {
        Maitrise nelt = model.add(elt);
        if(nelt!=null) {
            coursPresenter.ajouterSpecialiste(elt, elt.getSpecialite());
            view.affMsg("création de :" + nelt);
        }
        else view.affMsg("erreur de création");
    }
    @Override
    public Cours choixCours() {
        return coursPresenter.selection();
    }
    @Override
    public Formateur choixFormateur() {
        return formateurPresenter.selection();
    }
}
