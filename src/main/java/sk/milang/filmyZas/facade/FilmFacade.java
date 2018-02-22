/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sk.milang.filmyZas.model.Film;
import sk.milang.filmyZas.weblogic.FilmWebTemp;

/**
 *
 * @author fskgranam
 */

@Stateless
public class FilmFacade extends AbstractFacade<Film> {
    @PersistenceContext(unitName = "FilmyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilmFacade() {
        super(Film.class);
    }
    
    public Film createFromObject(FilmWebTemp obj) {
        if (obj==null || !(obj instanceof FilmWebTemp)) {
            return null;
        }
        Film novy = new Film();
        novy.setNazov(obj.getNazov());
        novy.setAltNazvy(obj.getAltNazvy());
        novy.setRok(obj.getRok());
        novy.setMinutaz(obj.getMinutaz());
        novy.setLink(obj.getLink());
        List<String> zoznam = obj.getHerciList();
        if (zoznam != null && !zoznam.isEmpty()) {
            for (String herec : zoznam) {
                
            }
        }
        return null;
    }
}
