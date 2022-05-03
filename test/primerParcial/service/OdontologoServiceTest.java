package primerParcial.service;

import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import primerParcial.dao.OdontologoDAOH2;
import primerParcial.modelo.Odontologo;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @BeforeAll
    public static void init() {
        PropertyConfigurator.configure("log4j.properties");
    }

    @BeforeAll
    public static void cargarBD(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:h2:~/parcial;INIT=RUNSCRIPT FROM 'database.sql'","root","");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }


    @Test
    @Order(1)
    public void listarOdontologos(){
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
        Odontologo odont1 = new Odontologo(921,"Benjamín","Valverde");
        Odontologo odont2 = new Odontologo(219,"Nicolás","Valverde");
        Odontologo odont3 = new Odontologo(129,"Jimena","Valverde");
        odontologoService.guardar(odont1);
        odontologoService.guardar(odont2);
        odontologoService.guardar(odont3);
        int resultado = 0;

        resultado = odontologoService.listar().size();
        System.out.println(resultado);
        //ya que acabo de agregar 3, y en el archivo .sql tenía agregado 2, en total 5.
        int rsEsperado = 5;
        Assertions.assertEquals(rsEsperado, resultado);
    }

    @Test
    @Order(2)
    public void buscarPorId1(){
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
        Odontologo odontologo = new Odontologo(921,"Santiago","Mesón");
        Odontologo odontologo1 = new Odontologo(922,"Santi","Mirra");
        Odontologo odontResultado;

        odontologoService.guardar(odontologo);
        odontologoService.guardar(odontologo1);
        odontResultado = odontologoService.buscarPorId(4);

        Assertions.assertEquals(odontResultado.getId(),4);
    }

    @Test
    @Order(3)
    public void guardarOdontologo(){
        OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());
        Odontologo odontologo = new Odontologo(921,"Benjamín","Valverde");
        Odontologo odontResultado;

        odontologoService.guardar(odontologo);
        odontResultado = odontologoService.buscarPorId(3);

        int rsEsperado = 3;

        Assertions.assertEquals(odontResultado.getId(),rsEsperado);
    }

}