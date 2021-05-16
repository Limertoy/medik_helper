package services;

import DAO.DaoFactory;
import DAO.SlotyDAO;
import obiekty.Sloty;

import java.util.List;

public class SlotyService {
    private SlotyDAO dao = DaoFactory.createSlotyDao();

    public Sloty saveOrUpdate(Sloty obj) {
        return dao.save(obj);
    }

    public void remove(Sloty obj) {
        dao.remove(obj);
    }

    public Sloty findById(int id) {
        return dao.findById(id);
    }

    public List<Sloty> findAll() {
        return dao.findAll();
    }
}
