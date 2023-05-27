package mvp.model;

import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Cours;
import proj.metier.Formateur;
import proj.metier.Maitrise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaitriseModelDB implements DAO<Maitrise> {
    private static final Logger logger = LogManager.getLogger(SessionCoursModelDB.class);
    protected Connection dbConnect;
    public MaitriseModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }
        logger.info("connexion établie");
    }

    @Override
    public Maitrise add(Maitrise maitrise) {
        Formateur formateur = maitrise.getSpecialiste();
        Cours cours = maitrise.getSpecialite();
        String description = maitrise.getDescription();
        String query1 = "insert into APIMAITRISE(id_formateur,id_cours,description) values(?,?,?)";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
        ){
            pstm1.setInt(1,formateur.getId());
            pstm1.setInt(2,cours.getId());
            pstm1.setString(3,description);
            int n = pstm1.executeUpdate();
            if (n==1){
                return maitrise;
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return null;
        }
    }

    @Override
    public boolean remove(Maitrise maitrise) {
        String query = "delete from APIMAITRISE where id_formateur=? and id_cours=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,maitrise.getSpecialiste().getId());
            pstm.setInt(2,maitrise.getSpecialite().getId());
            int n=pstm.executeUpdate();
            if (n!=0) {
                return true;
            }
            else return false;
        }catch (SQLException e){
            System.out.println("erreur SQL : "+e);
            return false;
        }    }

    @Override
    public Maitrise update(Maitrise maitrise) {
        String query = "update APIMAITRISE set description where id_formateur=? and id_cours=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            System.out.println(maitrise);
            pstm.setString(1, maitrise.getDescription());
            pstm.setInt(2,maitrise.getSpecialiste().getId());
            pstm.setInt(3,maitrise.getSpecialite().getId());
            int n=pstm.executeUpdate();
            if (n!=0) {
                return read(maitrise.getSpecialiste().getId());
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
            return null;
        }    }

    @Override
    public Maitrise read(int id_formateur) {
        boolean flag=false;
        String query = "select * from APIMAITRISE where id_formateur = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,id_formateur);
            Formateur formateur = rechercheFormateur(id_formateur);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                int id_cours = rs.getInt(2);
                Cours cours = rechercheCours(id_cours);
                String description = rs.getString(3);
                Maitrise l= new Maitrise(description,formateur,cours);
                return l;
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return null;
        }
    }

    @Override
    public List<Maitrise> getAll() {
        List<Maitrise> ll=new ArrayList<>();
        String query="select * from APIMAITRISE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_formateur = rs.getInt(1);
                Formateur formateur = rechercheFormateur(id_formateur);
                int id_cours = rs.getInt(2);
                Cours cours = rechercheCours(id_cours);
                String description = rs.getString(3);
                Maitrise l = new Maitrise(description,formateur,cours);
                ll.add(l);
            }
            return ll;
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
