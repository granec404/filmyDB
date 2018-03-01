/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import sk.milang.filmyZas.model.Zaner;

/**
 *
 * @author fskgranam
 */
@Stateless
public class ZanerFacade extends AbstractFacade<Zaner> {
    @PersistenceContext(unitName = "FilmyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZanerFacade() {
        super(Zaner.class);
    }
    
    public Zaner najdiZanerPodlaNazvu(String nazov) {
        List<Zaner> results = em.createNamedQuery("Zaner.najdiZaner").setParameter("nazov", nazov).getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
