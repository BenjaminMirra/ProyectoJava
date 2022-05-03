package primerParcial.modelo;

public class Odontologo {

    private Integer id;
    private Integer nroMatricula;
    private String nombre;
    private String apellido;

    public Odontologo(Integer id, Integer nroMatricula, String nombre, String apellido) {
        this.id = id;
        this.nroMatricula = nroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Integer nroMatricula, String nombre, String apellido) {
        this.nroMatricula = nroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getNroMatricula() {
        return nroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "Odontólogo, id:" + id +
                ", nroMatricula:" + nroMatricula +
                ", nombre: " + nombre + '\'' +
                ", apellido: " + apellido;
    }
}
