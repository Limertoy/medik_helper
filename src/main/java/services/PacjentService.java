package services;

import DAO.DaoFactory;
import DAO.PacjentDAO;
import obiekty.Pacjent;

import java.util.List;

public class PacjentService {
    private PacjentDAO dao = DaoFactory.createPacjentDao();

    public Pacjent saveOrUpdate(Pacjent obj) {
        return dao.save(obj);
    }

    public void remove(Pacjent obj) {
        dao.remove(obj);
    }

    public Pacjent findById(int id) {
        return dao.findById(id);
    }



    public List<Pacjent> findAll() {
        return dao.findAll();
    }
}
