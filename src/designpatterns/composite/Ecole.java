package designpatterns.composite;

public class Ecole {
    public static void main(String[] args)  {
            Cours c1 = new Cours(1, "POO1", 60);
            Cours c2 = new Cours(2, "DROIT", 12);
            Cours c3 = new Cours(3, "API1", 45);
            Cours c4 = new Cours(4, "MATH3", 20);
            Categorie ca1 = new Categorie(5, "générale");
            Categorie ca2 = new Categorie(6, "gestion");
            Categorie ca3 = new Categorie(7, "info");
            ca1.getElts().add(c4);
            ca1.getElts().add(ca2);
            ca1.getElts().add(ca3);
            ca2.getElts().add(c2);
            ca3.getElts().add(c3);
            ca3.getElts().add(c1);
            System.out.println(ca1);
    }
}
    
