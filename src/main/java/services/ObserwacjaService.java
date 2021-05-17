package services;

import DAO.DaoFactory;
import DAO.ObserwacjaDAO;
import DAO.RolaDAO;
import obiekty.Obserwacja;

import java.util.List;

public class ObserwacjaService {
    private ObserwacjaDAO dao = DaoFactory.createObserwacjaDao();

    public Obserwacja saveOrUpdate(Obserwacja obj) {
        return dao.save(obj);
    }

    public void remove(Obserwacja obj) {
        dao.remove(obj);
    }

    public Obserwacja findById(int id) {
        return dao.findById(id);
    }

    public List<Obserwacja> findAll() {
        return dao.findAll();
    }
}
