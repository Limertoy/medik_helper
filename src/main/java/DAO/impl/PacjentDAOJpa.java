package DAO.impl;

import DAO.PacjentDAO;
import obiekty.Pacjent;
import obiekty.Wyposazenie;

import javax.persistence.EntityManager;
import java.util.List;

public class PacjentDAOJpa implements PacjentDAO {

    private EntityManager em;

    public PacjentDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Pacjent save(Pacjent entity) {
        em.getTransaction().begin();
        if (entity.getId_pacjenta() == 0) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Pacjent entity) {
        entity = findById(entity.getId_pacjenta());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Pacjent findById(int id) {
        return em.find(Pacjent.class, id);
    }


    @Override
    public List<Pacjent> findAll() {
        return em.createNativeQuery("SELECT * FROM pacjent ", Pacjent.class).getResultList();
    }

    @Override
    public List<Pacjent> findRecepta() {
        return em.createNativeQuery("SELECT * FROM pacjent ", Pacjent.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }
}
