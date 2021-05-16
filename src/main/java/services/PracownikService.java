package services;

import DAO.DaoFactory;
import DAO.PracownikDAO;
import obiekty.Pracownik;

import java.util.List;

public class PracownikService {
    private PracownikDAO dao = DaoFactory.createPracownikDao();

    public Pracownik saveOrUpdate(Pracownik obj) {
        return dao.save(obj);
    }

    public void remove(Pracownik obj) {
        dao.remove(obj);
    }

    public Pracownik findById(int id) {
        return dao.findById(id);
    }

    public List<Pracownik> findAll() {
        return dao.findAll();
    }

    public List<Pracownik> znajdzLekarza(){return dao.znajdzLekarza();}

}
