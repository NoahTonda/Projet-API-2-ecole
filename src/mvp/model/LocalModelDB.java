package mvp.model;
import myconnections.DBConnection;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Local;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalModelDB implements DAO<Local>,LocalSpecial {
    private static final Logger logger = LogManager.getLogger(LocalModelDB.class);
    protected Connection dbConnect;

    public LocalModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            logger.error("erreur de connexion");
            System.exit(1);
        }
        logger.info("connexion établie");
    }
    @Override
    public Local add(Local local) {
        String sigle = local.getSigle();
        int places = local.getPlaces();
        String query1 = "insert into APILOCAL(sigle,places) values(?,?)";
        String query2="select id_Local from APILOCAL where sigle= ? and places=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,sigle);
            pstm1.setInt(2,places);
            int n = pstm1.executeUpdate();
            if (n==1){
                pstm2.setString(1,sigle);
                pstm2.setInt(2,places);
                ResultSet rs= pstm2.executeQuery();
                if (rs.next()){
                    int idLocal= rs.getInt(1);
                    local.setId(idLocal);
                    return local;
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
    public Local read(int idLocal){
        boolean flag=false;
        String query = "select * from APILOCAL where id_Local = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setInt(1,idLocal);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                String sigle = rs.getString(2);
                int places = rs.getInt(3);
                Local l= new Local(idLocal,sigle,places);
                return l;
            }
            else return null;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return null;
        }
    }
    @Override
    public Local update(Local local) {
        String query = "update APILOCAL set sigle=?,places=? where id_Local=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setString(1, local.getSigle());
            pstm.setInt(2,local.getPlaces());
            pstm.setInt(3,local.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return read(local.getId());
            else return null;
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
            return null;
        }
    }
    @Override
    public boolean remove(Local local) {
        String query = "delete from APILOCAL where id_Local=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,local.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return true;
            else return false;
        }catch (SQLException e){
            System.out.println("erreur SQL : "+e);
            return false;
        }
    }
    @Override
    public List<Local> getAll() {
        List<Local> ll=new ArrayList<>();
        String query="select * from APILOCAL";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_Local = rs.getInt(1);
                String sigle = rs.getString(2);
                int places = rs.getInt(3);
                Local l = new Local(id_Local,sigle,places);
                ll.add(l);
            }
            return ll;
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
            return null;
        }
    }

    //utilisation d'une fonction sql de mme Legrand
    @Override
    public List<Local> GetAvailableLocals(int inscrits) {
        List<Local> ll=new ArrayList<>();
        try (CallableStatement stmt = dbConnect.prepareCall("{? = call GetAvailableLocals(?)}")) {
            try {
                // Enregistrer le type de retour de la fonction
                stmt.registerOutParameter(1, OracleTypes.CURSOR);
                stmt.setInt(2,inscrits);
                // Exécuter la fonction
                stmt.execute();
                // Récupérer le curseur de résultat
                ResultSet rs = (ResultSet) stmt.getObject(1);
                while (rs.next()) {
                    int id_Local = rs.getInt(1);
                    String sigle = rs.getString(2);
                    int places = rs.getInt(3);
                    Local l = new Local(id_Local,sigle,places);
                    ll.add(l);
                }
            } catch (SQLException e) {
                System.out.println("erreur sql :" + e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ll;    }
}

