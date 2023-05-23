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

public class SessionCoursModelDB implements DAOSessionCours {
    private static final Logger logger = LogManager.getLogger(SessionCoursModelDB.class);
    protected Connection dbConnect;

    public SessionCoursModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            // System.err.println("erreur de connexion");
            logger.error("erreur de connexion");
            System.exit(1);
        }
        logger.info("connexion établie");
    }
    @Override
    public SessionCours addSessionCours(SessionCours sessionCours) {
        LocalDate dateDebut = sessionCours.getDateDebut();
        LocalDate dateFin = sessionCours.getDateFin();
        int nbreInscrits = sessionCours.getNbreInscrits();
        int idCours = sessionCours.getCours().getId();
        int idLocal = sessionCours.getLocal().getId();
        int idFormateur = sessionCours.getFormateur().getId();
        String query1 = "insert into APISESSIONCOURS(dateDebut,dateFin,nbreInscrits,id_Cours,id_Local,id_Formateur) values(?,?,?,?,?,?)";
        String query2="select id_sessionCours from APISESSIONCOURS where dateDebut and dateFin and nbreInscrits and id_Cours and id_Local and id_Formateur";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2)
        ){
            pstm1.setDate(1,Date.valueOf(dateDebut));
            pstm1.setDate(2,Date.valueOf(dateFin));
            pstm1.setInt(3,nbreInscrits);
            pstm1.setInt(4,idCours);
            pstm1.setInt(5,idLocal);
            pstm1.setInt(6,idFormateur);
            int n = pstm1.executeUpdate();
            if (n==1){
                pstm1.setDate(1,Date.valueOf(dateDebut));
                pstm1.setDate(2,Date.valueOf(dateFin));
                pstm1.setInt(3,nbreInscrits);
                pstm1.setInt(4,idCours);
                pstm1.setInt(5,idLocal);
                pstm1.setInt(6,idFormateur);
                ResultSet rs= pstm2.executeQuery();
                if (rs.next()){
                    int idSessionCours= rs.getInt(1);
                    sessionCours.setId(idSessionCours);
                    return sessionCours;
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
    @Override
    public SessionCours readSessionCours(int idSessionCours){
        boolean flag=false;
        String query = "select * from APISESSIONCOURS where id_sessionCours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,idSessionCours);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                LocalDate dateDebut = rs.getDate(2).toLocalDate();
                LocalDate dateFin = rs.getDate(3).toLocalDate();
                int nbreInscrits = rs.getInt(4);
                Cours cours = rs.getObject(5, Cours.class);
                Formateur formateur = rs.getObject(6, Formateur.class);
                Local local = rs.getObject(7, Local.class);
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
    public SessionCours updateSessionCours(SessionCours sessionCours) {
        String query = "update APISESSIONCOURS set dateDebut=?,dateFin=?,nbreInscrits=?,id_Cours=?,id_Local=?,id_Formateur=? where id_sessionCours=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setDate(1,Date.valueOf(sessionCours.getDateDebut()));
            pstm.setDate(2,Date.valueOf(sessionCours.getDateFin()));
            pstm.setInt(3,sessionCours.getNbreInscrits());
            pstm.setInt(4,sessionCours.getCours().getId());
            pstm.setInt(5,sessionCours.getLocal().getId());
            pstm.setInt(6,sessionCours.getFormateur().getId());
            int n=pstm.executeUpdate();
            if (n!=0) return readSessionCours(sessionCours.getId());
            else return null;
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
            return null;
        }
    }
    @Override
    public boolean removeSessionCours(SessionCours sessionCours) {
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
    public List<SessionCours> getSessionCours() {
        List<SessionCours> ls=new ArrayList<>();
        String query="select * from APISESSIONCOURS";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_sessionCours = rs.getInt(1);
                LocalDate dateDebut = rs.getDate(2).toLocalDate();
                LocalDate dateFin = rs.getDate(3).toLocalDate();
                int nbreInscrits = rs.getInt(4);
                Cours cours = rs.getObject(5, Cours.class);
                Formateur formateur = rs.getObject(6, Formateur.class);
                Local local = rs.getObject(7, Local.class);
                SessionCours s = new SessionCours(id_sessionCours,dateDebut,dateFin,nbreInscrits,cours, formateur, local);
                ls.add(s);
            }
            return ls;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
    }


}

