package primerParcial.dao;

import primerParcial.modelo.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class OdontologoDAOH2 implements IDao<Odontologo> {

    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciando método 'GUARDAR'");
        Connection connection = null;

        try{
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO odontologos (numero_matricula, nombre, apellido) VALUES (?,?,?)");
            preparedStatement.setInt(1,odontologo.getNroMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());

            preparedStatement.executeUpdate();

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
        logger.info("La ejecución del método terminó correctamente");
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        logger.info("Iniciando método 'LISTAR'");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Odontologo odont = new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
                odontologos.add(odont);
            }
        }
        catch (Exception e){
        e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        for (Odontologo odontologo : odontologos) {
            System.out.println(odontologo.toString());
        }
        logger.info("La ejecución del método terminó correctamente");
        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        logger.info("Iniciando método 'BUSCAR_POR_ID'");
        Connection connection = null;
        Odontologo odontologo = null;
        try{
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologos WHERE ID = ?");
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
            }

        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        finally {
            try {
            connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        logger.info("La ejecución del método terminó correctamente");
        return odontologo;
    }

    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/parcial","root","");
    }
}
