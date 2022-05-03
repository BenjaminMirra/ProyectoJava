import primerParcial.dao.OdontologoDAOH2;
import primerParcial.modelo.Odontologo;
import primerParcial.service.OdontologoService;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:~/parcial;INIT=RUNSCRIPT FROM 'database.sql'","root","");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /*
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());

        Odontologo odontologo = new Odontologo(1254, "Benjamín", "Mirra");
        System.out.println("Odontólogo guardado: ");
        odontologoService.guardar(odontologo);

        System.out.println("\nListado odontólogos:");
        odontologoService.listar();

        System.out.println("\nOdontólogo buscado:");
        odontologoService.buscarPorId(2);

         */
    }



}
