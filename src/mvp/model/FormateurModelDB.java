package mvp.model;
import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Formateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormateurModelDB implements DAOFormateur {
    private static final Logger logger = LogManager.getLogger(FormateurModelDB.class);
    protected Connection dbConnect;

    public FormateurModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            // System.err.println("erreur de connexion");
            logger.error("erreur de connexion");
            System.exit(1);
        }
        logger.info("connexion établie");
    }
    @Override
    public Formateur addFormateur(Formateur formateur) {
        String mail = formateur.getMail();
        String nom = formateur.getNom();
        String prenom = formateur.getPrenom();
        String query1 = "insert into APIFORMATEUR(mail,nom,prénom) values(?,?,?)";
        String query2="select id_formateur from APIFORMATEUR where mail= ? and nom=? and prénom=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,mail);
            pstm1.setString(2,nom);
            pstm1.setString(3,prenom);
            int n = pstm1.executeUpdate();
            if (n==1){
                pstm2.setString(1,mail);
                pstm2.setString(2,nom);
                pstm2.setString(3,prenom);
                ResultSet rs= pstm2.executeQuery();
                if (rs.next()){
                    int idFormateur= rs.getInt(1);
                    formateur.setId(idFormateur);
                    return formateur;
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
    public Formateur readFormateur(int idFormateur){
        boolean flag=false;
        String query = "select * from APIFORMATEUR where id_formateur = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,idFormateur);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                String mail = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                Formateur f= new Formateur(idFormateur,mail,nom,prenom);
                return f;
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return null;
        }
    }
    @Override
    public Formateur updateFormateur(Formateur formateur) {
        String query = "update APIFORMATEUR set mail=?,nom=?,prénom=? where id_formateur=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setString(1, formateur.getMail());
            pstm.setString(2,formateur.getNom());
            pstm.setString(3,formateur.getPrenom());
            pstm.setInt(4,formateur.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return readFormateur(formateur.getId());
            else return null;
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
            return null;
        }
    }
    @Override
    public boolean removeFormateur(Formateur formateur) {
        String query = "delete from APIFORMATEUR where id_formateur=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,formateur.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return true;
            else return false;
        }catch (SQLException e){
            System.out.println("erreur SQL : "+e);
            return false;
        }
    }
    @Override
    public List<Formateur> getFormateur() {
        List<Formateur> lf=new ArrayList<>();
        String query="select * from APIFORMATEUR";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_formateur = rs.getInt(1);
                String mail = rs.getString(2);
                String nom = rs.getString(3);
                String prénom = rs.getString(4);
                Formateur f = new Formateur(id_formateur,mail,nom,prénom);
                lf.add(f);
            }
            return lf;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
    }


}

