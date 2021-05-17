package DAO.impl;

import DAO.RolaDAO;
import DAO.SlotyDAO;
import obiekty.Rola;

import javax.persistence.EntityManager;
import java.util.List;

public class RolaDAOJpa implements RolaDAO {

        private EntityManager em;

        public RolaDAOJpa(EntityManager em) {
            this.em = em;
        }

        @Override
        public Rola save(Rola entity) {
            em.getTransaction().begin();
            if (entity.getId_rola() == 1) {
                em.persist(entity);
            } else {
                entity = em.merge(entity);
            }
            em.getTransaction().commit();
            return entity;
        }

        @Override
        public void remove(Rola entity) {
            entity = findById(entity.getId_rola());
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }

        @Override
        public Rola findById(int id) {
            return em.find(Rola.class, id);
        }


        @Override
        public List<Rola> findAll() {
            return em.createNativeQuery("SELECT * FROM rola ", Rola.class).getResultList();
        }

        public EntityManager getPersistenceContext() {
            return this.em;
        }
}
