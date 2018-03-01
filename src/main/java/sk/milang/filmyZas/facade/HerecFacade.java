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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import sk.milang.filmyZas.model.Herec;

/**
 *
 * @author fskgranam
 */
@Stateless
public class HerecFacade extends AbstractFacade<Herec> {
    @PersistenceContext(unitName = "FilmyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HerecFacade() {
        super(Herec.class);
    }
    
    public Herec najdiHercaPodlaMena(String meno) {
        Query q = em.createNamedQuery("Herec.najdiMeno").setParameter("meno", meno);
        List<Herec> results = q.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
