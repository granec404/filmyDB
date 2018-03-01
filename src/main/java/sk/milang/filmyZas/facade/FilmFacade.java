/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import sk.milang.filmyZas.model.Film;
import sk.milang.filmyZas.model.Herec;
import sk.milang.filmyZas.model.Krajina;
import sk.milang.filmyZas.model.Zaner;
import sk.milang.filmyZas.weblogic.FilmWebTemp;

/**
 *
 * @author fskgranam
 */

@Stateless
public class FilmFacade extends AbstractFacade<Film> {
    @PersistenceContext(unitName = "FilmyPU")
    private EntityManager em;
    @EJB
    private HerecFacade herecF = new HerecFacade();
    @EJB
    private ZanerFacade zanerF = new ZanerFacade();
    @EJB
    private KrajinaFacade krajinaF = new KrajinaFacade();

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
            for (String herecStr : zoznam) {
                Herec h = herecF.najdiHercaPodlaMena(herecStr);
                if (h==null) {
                    h = new Herec();
                    h.setMeno(herecStr);
                    herecF.create(h);
                }
                novy.addHerec(h);
            }
        }
        
        zoznam = obj.getZanerList();
        if (zoznam != null && !zoznam.isEmpty()) {
            for (String zanerStr : zoznam) {
                Zaner z = zanerF.najdiZanerPodlaNazvu(zanerStr);
                if (z==null) {
                    z = new Zaner();
                    z.setNazov(zanerStr);
                    zanerF.create(z);
                }
                novy.addZaner(z);
            }
        }
        
        zoznam = obj.getKrajinaList();
        if (zoznam != null && !zoznam.isEmpty()) {
            for (String krajinaStr : zoznam) {
                Krajina k = krajinaF.najdiKrajinuPodlaNazvu(krajinaStr);
                if (k==null) {
                    k = new Krajina();
                    k.setNazov(krajinaStr);
                    krajinaF.create(k);
                }
                novy.addKrajina(k);
            }
        }
        create(novy);
        return novy;
    }
}
