package DAO.impl;

import DAO.PracownikDAO;
import obiekty.Pracownik;

import javax.persistence.EntityManager;
import java.util.List;

public class PracownikDAOJpa implements PracownikDAO {

    private EntityManager em;

    public PracownikDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Pracownik save(Pracownik entity) {
        em.getTransaction().begin();
        if (entity.getId_pracownika() == 1) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Pracownik entity) {
        entity = findById(entity.getId_pracownika());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Pracownik findById(int id) {
        return em.find(Pracownik.class, id);
    }

    @Override
    public List<Pracownik> znajdzLekarza() {
        return em.createNativeQuery("SELECT * FROM pracownik WHERE id_rola = 1", Pracownik.class).getResultList();
    }


    @Override
    public List<Pracownik> findAll() {
        return em.createNativeQuery("SELECT * FROM pracownik ", Pracownik.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }
}
