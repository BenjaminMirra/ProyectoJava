package primerParcial.service;

import primerParcial.dao.IDao;
import primerParcial.modelo.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo buscarPorId(int id){
        return odontologoDao.buscarPorId(id);
    }

    public Odontologo guardar(Odontologo odontologo){
        return odontologoDao.guardar(odontologo);
    }

    public List<Odontologo> listar(){
        return odontologoDao.listar();
    }
}
