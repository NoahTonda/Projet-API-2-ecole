package designpatterns.observer;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ecole {
    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        Formateur f1= new Formateur(1,"michel.poriaux@condorcet.be","Poriaux","Michel");
        Formateur f2= new Formateur(2,"legrand.nathalie@condorcet.be","Legrand","Nathalie");
        Cours c1=new Cours(3, "API2",60);
        Cours c2=new Cours(3, "API1",50);
        Local l1=new Local(2,"F3",30);
        Local l2=new Local(2,"F5",25);
        SessionCours sc1 = new SessionCours(1, LocalDate.parse("17 05 2023",dateFormatter), LocalDate.parse("20 05 2023",dateFormatter), 15,c1 ,f1,l1);
        SessionCours sc2 = new SessionCours(1, LocalDate.parse("17 05 2023",dateFormatter), LocalDate.parse("20 05 2023",dateFormatter), 20,c2 ,f2,l2);
        sc1.addObserver(f1);
        sc1.addObserver(f2);
        sc2.addObserver(f1);

        sc1.setNbreInscrits(17);
        sc2.setNbreInscrits(23);

        sc1.setLocal(l2);
        sc2.setLocal(l1);
    }
}
