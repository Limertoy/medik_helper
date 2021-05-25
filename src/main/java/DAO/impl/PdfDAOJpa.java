package DAO.impl;

import DAO.PdfDAO;
import obiekty.Pdf;

import javax.persistence.EntityManager;
import java.util.List;

public class PdfDAOJpa implements PdfDAO {
    private final EntityManager em;

    public PdfDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Pdf save(Pdf entity) {
        em.getTransaction().begin();
        if (entity.getId() == 1) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Pdf entity) {
        entity = findById(entity.getId());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Pdf findById(int id) {
        return em.find(Pdf.class, id);
    }


    @Override
    public List<Pdf> findAll() {
        return em.createNativeQuery("SELECT * FROM pdf", Pdf.class).getResultList();
    }


    public EntityManager getPersistenceContext() {
        return this.em;
    }
}
