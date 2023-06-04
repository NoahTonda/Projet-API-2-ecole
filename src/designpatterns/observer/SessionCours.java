package designpatterns.observer;

import java.time.LocalDate;

public class SessionCours extends Subject{
    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nbreInscrits;
    private Cours cours;
    private Formateur formateur;
    private Local local;

    public SessionCours(int id, LocalDate dateDebut, LocalDate dateFin, int nbreInscrits, Cours cours, Formateur formateur, Local local) {
        this.id=id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbreInscrits = nbreInscrits;
        this.cours = cours;
        this.formateur = formateur;
        this.local = local;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setNbreInscrits(int nbreInscrits) {
        this.nbreInscrits = nbreInscrits;
        notifyObservers();
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public void setLocal(Local local) {
        this.local = local;
        notifyObservers();
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public int getNbreInscrits() {
        return nbreInscrits;
    }
    public Cours getCours() {
        return cours;
    }
    public Formateur getFormateur() {
        return formateur;
    }

    public Local getLocal() {
        return local;
    }


    @Override
    public String toString() {
        return "cours :" + cours + "dateDebut :" + dateDebut + ", dateFin :" + dateFin + ", nbreInscrits :" + nbreInscrits +
                "\nformateur = " + formateur +
                "local = " + local;
    }

    @Override
    public String getNotification() {
        return "Des changements ont étés effectués\nles nouvelles données sont : \n"+this;
    }
}

