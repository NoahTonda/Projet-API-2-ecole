package mvp.model;

import proj.metier.Local;

import java.time.LocalDate;
import java.util.List;

public interface LocalSpecial {
    List<Local> GetAvailableLocals(int inscrits);
    int is_local_available(Local local, LocalDate debut, LocalDate fin);

}
