package services;

import DAO.DaoFactory;
import DAO.PdfDAO;
import obiekty.Pdf;

import java.util.List;

public class PdfService {
    private final PdfDAO dao = DaoFactory.createPdfDao();

    public Pdf saveOrUpdate(Pdf obj) {
        return dao.save(obj);
    }

    public void remove(Pdf obj) {
        dao.remove(obj);
    }

    public Pdf findById(int id) {
        return dao.findById(id);
    }

    public List<Pdf> findAll() {
        return dao.findAll();
    }
}
