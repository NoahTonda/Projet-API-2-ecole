package mvp.view;


import mvp.presenter.Presenter;

import java.util.List;

public interface ViewInterface<T> {
    void setPresenter(Presenter<T> presenter);

    void setListDatas(List<T> datas);

    void affMsg(String msg);

    void affList(List l);

    T selectionner(List<T> l);
}
