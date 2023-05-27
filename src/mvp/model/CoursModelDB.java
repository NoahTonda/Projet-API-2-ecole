package mvp.model;
import myconnections.DBConnection;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Local;
import proj.metier.SessionCours;
import utilitaires.Utilitaire;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CoursModelDB implements DAO<Cours>,CoursSpecial {
    private static final Logger logger = LogManager.getLogger(CoursModelDB.class);
    protected Connection dbConnect;

    public CoursModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }
        logger.info("connexion établie");
    }
    @Override
    public Cours add(Cours cours) {
        String matière = cours.getMatiere();
        int nbreHeures = cours.getNbreHeures();
        String query1 = "insert into APICOURS(matière,nbreHeures) values(?,?)";
        String query2="select id_cours from APICOURS where matière= ? and nbreHeures=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,matière);
            pstm1.setInt(2,nbreHeures);
            int n = pstm1.executeUpdate();
            if (n==1){
                pstm2.setString(1,matière);
                pstm2.setInt(2,nbreHeures);
                ResultSet rs= pstm2.executeQuery();
                if (rs.next()){
                    int idcours= rs.getInt(1);
                    cours.setId(idcours);
                    return cours;
                }
                else System.out.println("record introuvable");
                return null;
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return null;
        }
    }
    public Cours read(int idCours){
        String query = "select * from APICOURS where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,idCours);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                String nm = rs.getString(2);
                int nh = rs.getInt(3);
                Cours c= new Cours(idCours,nm,nh);
                return c;
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return null;
        }
    }
    @Override
    public Cours update(Cours cours) {
        String query = "update APICOURS set matière=?,nbreheures=? where id_cours=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setString(1, cours.getMatiere());
            pstm.setInt(2,cours.getNbreHeures());
            pstm.setInt(3,cours.getId());
            int n=pstm.executeUpdate();
            if (n!=0) {
                return read(cours.getId());
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
            return null;
        }
    }
    @Override
    public boolean remove(Cours cours) {
        String query = "delete from APICOURS where id_cours=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,cours.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return true;
            else return false;
        }catch (SQLException e){
            System.out.println("erreur SQL : "+e);
            return false;
        }
    }
    //utilisation d'une fonction sql de mme Legrand
    @Override
    public List<Cours> getAll() {
        List<Cours> lc=new ArrayList<>();
        try (CallableStatement stmt = dbConnect.prepareCall("{? = call GetAllCours}")) {
            try {
                // Enregistrer le type de retour de la fonction
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                // Exécuter la fonction
                stmt.execute();
                // Récupérer le curseur de résultat
                ResultSet rs = (ResultSet) stmt.getObject(1);
                while (rs.next()) {
                    int idCours = rs.getInt("id_cours");
                    String matiere = rs.getString("matière");
                    int nbreHeures = rs.getInt("nbreheures");
                    lc.add(new Cours(idCours,matiere,nbreHeures));
                }
            } catch (SQLException e) {
                System.out.println("erreur sql :" + e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lc;
    }
        @Override
    public List<SessionCours> sessionLoc(Cours cours) {
        String query="select * from APISESSIONCOURS where id_cours=?";
        return rechercheSessionCours(cours,query);
    }
    @Override
    public List<String> specialistes(Cours cours) {
        String query="select * from APIMAITRISE_SPE where matière=?";
        return rechercheVueCours(cours,query);
    }
    @Override
    public List<SessionCours> sessionDate(Cours cours){
        String query="select * from APISESSIONCOURS where id_cours=? and datefin>? and datefin<?";
        return rechercheSessionDate(cours,query);
    }
    //utilisation d'une fonction sql de mme Legrand
    @Override
    public List<Formateur> get_available_formateurs(Cours cours) {
        List<Formateur> lf=new ArrayList<>();
        try (CallableStatement stmt = dbConnect.prepareCall("{?=call get_available_formateur(?)")) {
            try {
                // Enregistrer le type de retour de la fonction
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                stmt.setInt(2,cours.getId());
                // Exécuter la fonction
                stmt.execute();
                // Récupérer le curseur de résultat
                ResultSet rs = (ResultSet) stmt.getObject(1);
                while (rs.next()) {
                    int id_Formateur = rs.getInt(1);
                    String mail = rs.getString(2);
                    String nom = rs.getString(3);
                    String prenom = rs.getString(4);
                    Formateur f = new Formateur(id_Formateur,mail,nom,prenom);
                    lf.add(f);
                }
            } catch (SQLException e) {
                System.out.println("erreur sql :" + e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lf;
    }

    private List<String> rechercheVueCours(Cours cours,String query){
        List<String> ls=new ArrayList<>();
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,cours.getMatiere());
            ResultSet rs = pstm.executeQuery();
            boolean trouve= false;
            while(rs.next()){
                trouve=true;
                String nm = rs.getString(1);
                String nf = rs.getString(2);
                String maitrise= rs.getString(3);
                ls.add("matière : "+nm+", formateur : "+nf+", maitrise : "+maitrise);
            }
            if(!trouve) System.out.println("aucun cours trouvé");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
        return ls;
    }
    public List<SessionCours> rechercheSessionCours(Cours cours, String query) {
        List<SessionCours> ls;
        int i=1;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cours.getId());
            ls=rechercheSession(cours, i, pstm);
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
        return ls;
    }
    private List<SessionCours> rechercheSession(Cours cours, int i, PreparedStatement pstm) throws SQLException {
        List<SessionCours> ls=new ArrayList<>();
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
            ls.add(sc);
        }
        if(!trouve) System.out.println("aucune session trouvée");
        return ls;
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
    private List<SessionCours> rechercheSessionDate(Cours cours,String query) {
        int i=1;
        System.out.println("date minimum :");
        LocalDate datemin= Utilitaire.lecDate();
        System.out.println("date maximum");
        LocalDate datemax= Utilitaire.lecDate();
        List<SessionCours> lsc;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,cours.getId());
            pstm.setDate(2, java.sql.Date.valueOf(datemin));
            pstm.setDate(3, java.sql.Date.valueOf(datemax));
            lsc=rechercheSession(cours,i,pstm);
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
        return lsc;
    }
}
