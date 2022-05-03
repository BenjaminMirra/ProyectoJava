package primerParcial.dao;

import java.util.List;

public interface IDao <T> {
    T guardar(T t);
    List<T> listar();
    T buscarPorId(int id);
}
