package designpatterns.composite;

import java.util.HashSet;
import java.util.Set;

public class Categorie extends Element{
    private String nom;
    private Set<Element> elts=new HashSet<>();

    public Categorie(int id, String nom) {
        super(id);
        this.nom = nom;
    }

    @Override
    public String toString() {
        StringBuilder aff= new StringBuilder(getId()+" "+nom+"\n");

        for(Element e:elts){
            aff.append(e+"\n");
        }
        return aff+"valeur totale " +nom +" = "+nbreHeuresTotales()+"\n";
    }

    @Override
    public int nbreHeuresTotales() {
        int somme=0;
        for(Element h:elts){
            somme+=h.nbreHeuresTotales();
        }
        return somme;
    }

    public Set<Element> getElts() {
        return elts;
    }
}
