package mvp.model;
import myconnections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import proj.metier.Local;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalModelDB implements DAOLocal {
    private static final Logger logger = LogManager.getLogger(LocalModelDB.class);
    protected Connection dbConnect;

    public LocalModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            // System.err.println("erreur de connexion");
            logger.error("erreur de connexion");
            System.exit(1);
        }
        logger.info("connexion établie");
    }
    @Override
    public Local addLocal(Local Local) {
        String sigle = Local.getSigle();
        int places = Local.getPlaces();
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
                    Local.setId(idLocal);
                    return Local;
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
    public Local readLocal(int idLocal){
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
    public Local updateLocal(Local Local) {
        String query = "update APILOCAL set sigle=?,places=? where id_Local=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)){
            pstm.setString(1, Local.getSigle());
            pstm.setInt(2,Local.getPlaces());
            pstm.setInt(3,Local.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return readLocal(Local.getId());
            else return null;
        }catch(SQLException e){
            System.out.println("erreur SQL : "+e);
            return null;
        }
    }
    @Override
    public boolean removeLocal(Local Local) {
        String query = "delete from APILOCAL where id_Local=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,Local.getId());
            int n=pstm.executeUpdate();
            if (n!=0) return true;
            else return false;
        }catch (SQLException e){
            System.out.println("erreur SQL : "+e);
            return false;
        }
    }
    @Override
    public List<Local> getLocal() {
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


}

