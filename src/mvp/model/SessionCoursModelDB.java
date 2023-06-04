package mvp.model;
import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Local;
import proj.metier.SessionCours;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionCoursModelDB implements DAO<SessionCours> {
    private static final Logger logger = LogManager.getLogger(SessionCoursModelDB.class);
    protected Connection dbConnect;

    public SessionCoursModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }
        logger.info("connexion établie");
    }
    //TODO utilisation d'une procédure sql de mme Legrand
    @Override
    public SessionCours add(SessionCours sessionCours) {
        LocalDate dateDebut = sessionCours.getDateDebut();
        LocalDate dateFin = sessionCours.getDateFin();
        int nbreInscrits = sessionCours.getNbreInscrits();
        int idCours = sessionCours.getCours().getId();
        int idLocal = sessionCours.getLocal().getId();
        int idFormateur = sessionCours.getFormateur().getId();
        try (CallableStatement stmt = dbConnect.prepareCall("{CALL insert_session_cours(?, ?, ?, ?, ?, ?, ?)}")){
            // Définir les paramètres d'entrée
            stmt.setDate(1, Date.valueOf(dateDebut));
            stmt.setDate(2, Date.valueOf(dateFin));
            stmt.setInt(3, nbreInscrits);
            stmt.setInt(4, idCours);
            stmt.setInt(5, idLocal);
            stmt.setInt(6, idFormateur);
            // Définir le paramètre de sortie
            stmt.registerOutParameter(7, Types.INTEGER);
            // Exécuter la procédure
            stmt.execute();
            // Récupérer la valeur du paramètre de sortie
            int sessionId = stmt.getInt(7);
            sessionCours.setId(sessionId);
            return sessionCours;
        } catch (SQLException e) {
            System.out.println("erreur sql : "+e);
            return null;
        }
    }

    @Override
    public SessionCours read(int idSessionCours){
        boolean flag=false;
        String query = "select * from APISESSIONCOURS where id_sessionCours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,idSessionCours);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                LocalDate dateDebut = rs.getDate(2).toLocalDate();
                LocalDate dateFin = rs.getDate(3).toLocalDate();
                int nbreInscrits = rs.getInt(4);
                int idCours = rs.getInt(5);
                Cours cours = rechercheCours(idCours);
                int idLocal = rs.getInt(6);
                Local local = rechercheLocal(idLocal);
                int idFormateur = rs.getInt(7);
                Formateur formateur = rechercheFormateur(idFormateur);
                SessionCours s= new SessionCours(idSessionCours,dateDebut,dateFin,nbreInscrits,cours,formateur,local);
                return s;
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return null;
        }
    }
    @Override
    public SessionCours update(SessionCours sessionCours) {
        String query = "update APISESSIONCOURS set dateDebut=?,dateFin=?,nbreInscrits=?,id_Cours=?,id_Local=?,id_Formateur=? where id_sessionCours=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setDate(1,Date.valueOf(sessionCours.getDateDebut()));
            pstm.setDate(2,Date.valueOf(sessionCours.getDateFin()));
            pstm.setInt(3,sessionCours.getNbreInscrits());
            pstm.setInt(4,sessionCours.getCours().getId());
            pstm.setInt(5,sessionCours.getLocal().getId());
            pstm.setInt(6,sessionCours.getFormateur().getId());
            pstm.setInt(7,sessionCours.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return read(sessionCours.getId());
            else return null;
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
            return null;
        }
    }
    @Override
    public boolean remove(SessionCours sessionCours) {
        String query = "delete from APISESSIONCOURS where id_sessionCours=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,sessionCours.getId());
            int n=pstm.executeUpdate();
            return n != 0;
        }catch (SQLException e){
            System.out.println("erreur SQL : "+e);
            return false;
        }
    }
    @Override
    public List<SessionCours> getAll() {
        List<SessionCours> ls=new ArrayList<>();
        String query="select * from APISESSIONCOURS";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_sessionCours = rs.getInt(1);
                LocalDate dateDebut = rs.getDate(2).toLocalDate();
                LocalDate dateFin = rs.getDate(3).toLocalDate();
                int nbreInscrits = rs.getInt(4);
                int idCours = rs.getInt(5);
                Cours cours = rechercheCours(idCours);
                int idLocal = rs.getInt(6);
                Local local = rechercheLocal(idLocal);
                int idFormateur = rs.getInt(7);
                Formateur formateur = rechercheFormateur(idFormateur);
                SessionCours s = new SessionCours(id_sessionCours,dateDebut,dateFin,nbreInscrits,cours, formateur, local);
                ls.add(s);
            }
            return ls;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }

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
    private Cours rechercheCours(int cid) {
        String query = "select * from APICOURS where id_cours=?";
        Cours cours=null;
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, cid);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matière = rs.getString(2);
                int nbreHeures = rs.getInt(3);
                cours = new Cours(cid,matière,nbreHeures);
            } else System.out.println("record cours introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
        return cours;
    }
}

