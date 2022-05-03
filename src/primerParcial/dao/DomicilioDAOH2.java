package dao;

import modelo.Domicilio;
import org.apache.log4j.Logger;
import primerParcial.dao.IDao;
import primerParcial.dao.OdontologoDAOH2;

import java.sql.*;
import java.util.List;

public class DomicilioDAOH2 implements IDao<Domicilio> {

    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection=null;
        try{
            connection=getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO domicilios (calle, numero, localidad, provincia) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2,domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            preparedStatement.executeUpdate();
            ResultSet clavesGeneradas=preparedStatement.getGeneratedKeys();
            if (clavesGeneradas.next()){
                domicilio.setId(clavesGeneradas.getInt(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscarPorId(int id) {
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            //conectar la base
            connection = getConnection();

            //preparar la consulta
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM domicilios WHERE ID = ?");
            preparedStatement.setInt(1, id);

            //ejecutamos
            ResultSet infoDomicilio = preparedStatement.executeQuery();
            while(infoDomicilio.next()){
                //Utilizar la info que buscamos.
                domicilio = new Domicilio(infoDomicilio.getInt(1),infoDomicilio.getString(2),infoDomicilio.getInt(3),infoDomicilio.getString(4),infoDomicilio.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return domicilio;
    }

    @Override
    public List<Domicilio> listar() {
        return null;
    }

    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/clase15","root","");
    }
}
