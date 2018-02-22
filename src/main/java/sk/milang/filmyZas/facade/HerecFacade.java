/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
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
        List<Herec> results = em.createNamedQuery("Herec.najdiMeno").setParameter("meno", meno).getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
