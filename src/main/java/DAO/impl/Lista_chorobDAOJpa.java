package DAO.impl;

import DAO.Lista_chorobDAO;
import obiekty.Lista_chorob;
import obiekty.Sloty;

import javax.persistence.EntityManager;
import java.util.List;

public class Lista_chorobDAOJpa implements Lista_chorobDAO {
    private EntityManager em;

    public Lista_chorobDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Lista_chorob save(Lista_chorob entity) {
        em.getTransaction().begin();
        if (entity.getId_choroby() == 1) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Lista_chorob entity) {
        entity = findById(entity.getId_choroby());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Lista_chorob findById(int id) {
        return em.find(Lista_chorob.class, id);
    }


    @Override
    public List<Lista_chorob> findAll() {
        return em.createNativeQuery("SELECT * FROM lista_chorob ", Lista_chorob.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }

}
