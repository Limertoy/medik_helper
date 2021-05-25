package DAO;

import obiekty.Pdf;

import java.util.List;

public interface PdfDAO {
    Pdf save(Pdf  entity);
    void remove(Pdf entity);
    Pdf findById(int id);
    List<Pdf> findAll();
}
