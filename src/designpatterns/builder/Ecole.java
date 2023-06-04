package designpatterns.builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ecole {
    public static void main(String[] args)  {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        try {
            //constructeur complet
            SessionCours cl1 = new SessionCours.SessionCoursBuilder().
                    setId(1).
                    setDateDebut(LocalDate.parse("17 05 2023",dateFormatter)).
                    setDateFin(LocalDate.parse("20 05 2023",dateFormatter)).
                    setNbreInscrits(10).
                    setLocal(new Local.LocalBuilder().setId(2).setSigle("F3").setPlaces(20).build()).
                    setCours(new Cours.CoursBuilder().setId(3).setMatiere("API2").setNbreHeures(60).build()).
                    setFormateur(new Formateur.FormateurBuilder().setId(4).setMail("michel.poriaux@condorcet.be").setNom("Poriaux").setPrenom("Michel").build()).
                    build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }

        try {
            //sans Local
            SessionCours cl2 = new SessionCours.SessionCoursBuilder().
                    setId(1).
                    setDateDebut(LocalDate.parse("17 05 2023",dateFormatter)).
                    setDateFin(LocalDate.parse("20 05 2023",dateFormatter)).
                    setNbreInscrits(10).
                    setCours(new Cours.CoursBuilder().setId(3).setMatiere("API2").setNbreHeures(60).build()).
                    setFormateur(new Formateur.FormateurBuilder().setId(4).setMail("michel.poriaux@condorcet.be").setNom("Poriaux").setPrenom("Michel").build()).
                    build();
            System.out.println(cl2);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        try {
            //nombre d'inscrits négatif
            SessionCours cl3 = new SessionCours.SessionCoursBuilder().
                    setId(1).
                    setDateDebut(LocalDate.parse("17 05 2023",dateFormatter)).
                    setDateFin(LocalDate.parse("20 05 2023",dateFormatter)).
                    setNbreInscrits(-1).
                    setLocal(new Local.LocalBuilder().setId(2).setSigle("F3").setPlaces(20).build()).
                    setCours(new Cours.CoursBuilder().setId(3).setMatiere("API2").setNbreHeures(60).build()).
                    setFormateur(new Formateur.FormateurBuilder().setId(4).setMail("michel.poriaux@condorcet.be").setNom("Poriaux").setPrenom("Michel").build()).
                    build();
            System.out.println(cl3);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
        try {
            //sans Cours
            SessionCours cl4 = new SessionCours.SessionCoursBuilder().
                    setId(1).
                    setDateDebut(LocalDate.parse("17 05 2023",dateFormatter)).
                    setDateFin(LocalDate.parse("20 05 2023",dateFormatter)).
                    setNbreInscrits(10).
                    setLocal(new Local.LocalBuilder().setId(2).setSigle("F3").setPlaces(20).build()).
                    setFormateur(new Formateur.FormateurBuilder().setId(4).setMail("michel.poriaux@condorcet.be").setNom("Poriaux").setPrenom("Michel").build()).
                    build();
            System.out.println(cl4);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }

        try {
            //date de début et fin mal ordonnées
            SessionCours cl5 = new SessionCours.SessionCoursBuilder().
                    setId(1).
                    setDateDebut(LocalDate.parse("17 05 2023",dateFormatter)).
                    setDateFin(LocalDate.parse("13 05 2023",dateFormatter)).
                    setNbreInscrits(10).
                    setLocal(new Local.LocalBuilder().setId(2).setSigle("F3").setPlaces(20).build()).
                    setCours(new Cours.CoursBuilder().setId(3).setMatiere("API2").setNbreHeures(60).build()).
                    setFormateur(new Formateur.FormateurBuilder().setId(4).setMail("michel.poriaux@condorcet.be").setNom("Poriaux").setPrenom("Michel").build()).
                    build();
            System.out.println(cl5);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }

        try {
            //sans dat ede début
            SessionCours cl6 = new SessionCours.SessionCoursBuilder().
                    setId(1).
                    setDateFin(LocalDate.parse("20 05 2023",dateFormatter)).
                    setNbreInscrits(10).
                    setLocal(new Local.LocalBuilder().setId(2).setSigle("F3").setPlaces(20).build()).
                    setCours(new Cours.CoursBuilder().setId(3).setMatiere("API2").setNbreHeures(60).build()).
                    setFormateur(new Formateur.FormateurBuilder().setId(4).setMail("michel.poriaux@condorcet.be").setNom("Poriaux").setPrenom("Michel").build()).
                    build();
            System.out.println(cl6);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }

        try {
            //sans date de fin
            SessionCours cl7 = new SessionCours.SessionCoursBuilder().
                    setId(1).
                    setDateDebut(LocalDate.parse("17 05 2023",dateFormatter)).
                    setNbreInscrits(10).
                    setLocal(new Local.LocalBuilder().setId(2).setSigle("F3").setPlaces(20).build()).
                    setCours(new Cours.CoursBuilder().setId(3).setMatiere("API2").setNbreHeures(60).build()).
                    setFormateur(new Formateur.FormateurBuilder().setId(4).setMail("michel.poriaux@condorcet.be").setNom("Poriaux").setPrenom("Michel").build()).
                    build();
            System.out.println(cl7);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
    }
}
