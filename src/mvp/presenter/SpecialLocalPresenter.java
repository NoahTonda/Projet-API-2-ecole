package mvp.presenter;

import proj.metier.Local;

import java.time.LocalDate;

public interface SpecialLocalPresenter {
    void GetAvailableLocals(int inscrits);
    void is_local_available(Local local, LocalDate debut, LocalDate fin);

}
