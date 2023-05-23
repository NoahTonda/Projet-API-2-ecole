package projetdb;

import myconnections.DBConnection;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Local;
import proj.metier.SessionCours;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;


public class GestCours {
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.tous\n6.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }
    private void ajout() {
        System.out.println("matière : ");
        String matière = sc.nextLine();
        System.out.println("nombre d'heures : ");
        int nbreHeures = sc.nextInt();
        String query1 = "insert into APICOURS(matière,nbreHeures) values(?,?)";
        String query2="select id_cours from APICOURS where matière= ? and nbreHeures=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
            ){
            pstm1.setString(1,matière);
            pstm1.setInt(2,nbreHeures);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if (n==1){
                pstm2.setString(1,matière);
                pstm2.setInt(2,nbreHeures);
                ResultSet rs= pstm2.executeQuery();
                if (rs.next()){
                    int idcours= rs.getInt(1);
                    System.out.println("idcours = "+idcours);
                }
                else System.out.println("record introuvable");
            }
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
        }
    }
    private void recherche() {
        System.out.println("id du cours cherché ");
        int idrech = sc.nextInt();
        boolean flag=false;
        String query = "select * from APICOURS where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                String nm = rs.getString(2);
                int nh = rs.getInt(3);
                Cours c= new Cours(idrech,nm,nh);
                System.out.println(c);
                opSpeciales(c);
                flag=true;
            }
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
        }
        if (!flag){
            System.out.println("aucun cours ne correspond a cet id");
        }
    }
    private void modification() {
        System.out.println("id du cours recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("Nouvel intitulé du cours ");
        String nintitule=sc.nextLine();
        String query = "update APICOURS set matière=? where id_cours=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setString(1,nintitule);
            pstm.setInt(2,idrech);
            int n=pstm.executeUpdate();
            if (n!=0) System.out.println(n+" ligne mise a jour");
            else System.out.println("record introuvable");
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
        }
    }
    private void suppression() {
        System.out.println("id du cours recherché");
        int idrech = sc.nextInt();
        String query = "delete from APICOURS where id_cours=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,idrech);
            int n=pstm.executeUpdate();
            if (n!=0) System.out.println(n+" ligne supprimée");
            else System.out.println("record introuvable");
        }catch (SQLException e){
            System.out.println("erreur SQL : "+e);
        }
    }
    private void tous() {
        String query="select * from APICOURS";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_cours = rs.getInt(1);
                String matière = rs.getString(2);
                int nbreHeures = rs.getInt(3);
                Cours c = new Cours(id_cours,matière,nbreHeures);
                System.out.println(c);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }
    private void opSpeciales(Cours cours) {
        do {
            System.out.println("1.spécialistes du cours\n2.sessions avec local\n3.sessions entre 2 dates\n4.menu principal");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    specialistes(cours);
                    break;
                case 2:
                    sessionLoc(cours);
                    break;
                case 3:
                    sessionDate(cours);
                    break;
                case 4: return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }

    private void sessionDate(Cours cours) {
        System.out.println("date minimum :");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate datemin = LocalDate.of(a,m,j);
        System.out.println("date maximum");
        jma = sc.nextLine().split(" ");
        j = Integer.parseInt(jma[0]);
        m = Integer.parseInt(jma[1]);
        a = Integer.parseInt(jma[2]);
        LocalDate datemax = LocalDate.of(a,m,j);
        String query="select * from APISESSIONCOURS where id_cours=? and datefin>? and datefin<?";
        rechercheSessionDate(cours,query,datemin,datemax);
    }

    private void rechercheSessionDate(Cours cours, String query,LocalDate datemin,LocalDate datemax) {
        int i=1;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cours.getId());
            pstm.setDate(2, Date.valueOf(datemin));
            pstm.setDate(3, Date.valueOf(datemax));
            rechercheSession(cours, i, pstm);
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
        System.out.println("\n");
    }

    private void rechercheSessionCours(Cours cours, String query) {
        int i=1;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cours.getId());
            rechercheSession(cours, i, pstm);
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
        System.out.println("\n");
    }

    private void rechercheSession(Cours cours, int i, PreparedStatement pstm) throws SQLException {
        ResultSet rs = pstm.executeQuery();
        boolean trouve= false;
        while(rs.next()){
            trouve=true;
            int sid = rs.getInt(1);
            LocalDate dateDebut = rs.getDate(2).toLocalDate();
            LocalDate dateFin = rs.getDate(3).toLocalDate();
            int nbinscrits= rs.getInt(4);
            int lid = rs.getInt(6);
            Local local=rechercheLocal(lid);
            int fid = rs.getInt(7);
            Formateur form=rechercheFormateur(fid);
            SessionCours sc=new SessionCours(sid,dateDebut,dateFin,nbinscrits,cours,form,local);
            System.out.println(i+++"."+sc);
        }
        if(!trouve) System.out.println("aucune session trouvée");
    }

    private Formateur rechercheFormateur(int fid) {
        String query = "select * from APIFORMATEUR where id_formateur=?";
        Formateur formateur;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, fid);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String mail = rs.getString(2);
                String nom = rs.getString(3);
                String prénom = rs.getString(4);
                formateur = new Formateur(fid, mail, nom, prénom);
                return formateur;
            } else System.out.println("record formateur introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
        return null;
    }

    private Local rechercheLocal(int lid) {
        String query = "select * from APILOCAL where id_local=?";
        Local local=null;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, lid);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String sigle = rs.getString(2);
                int places = rs.getInt(3);
                local = new Local(lid, sigle, places);
            } else System.out.println("record local introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
        return local;
    }

    private void sessionLoc(Cours cours) {
        String query="select * from APISESSIONCOURS where id_cours=?";
        rechercheSessionCours(cours,query);
    }

    private void specialistes(Cours cours) {
        String query="select * from APIMAITRISE_SPE where matière=?";
        rechercheVueCours(cours,query);
    }

    public static void main(String[] args) {

        GestCours g = new GestCours();
        g.gestion();
    }
    private void rechercheVueCours(Cours cours,String query){
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,cours.getMatiere());
            ResultSet rs = pstm.executeQuery();
            boolean trouve= false;
            while(rs.next()){
                trouve=true;
                String nm = rs.getString(1);
                String nf = rs.getString(2);
                String maitrise= rs.getString(3);
                System.out.println("matière : "+nm+", formateur : "+nf+", maitrise : "+maitrise);
            }
            if(!trouve) System.out.println("aucun cours trouvé");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }
}
