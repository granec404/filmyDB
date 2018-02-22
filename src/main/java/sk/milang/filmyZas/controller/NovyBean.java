package sk.milang.filmyZas.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.List;
//import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import sk.milang.filmyZas.facade.FilmFacade;
import sk.milang.filmyZas.model.Film;
import sk.milang.filmyZas.weblogic.FilmWebTemp;
import sk.milang.filmyZas.weblogic.CucacInfo;

/**
 *
 * @author milan
 */
@Named(value = "novyBean")
@ViewScoped
public class NovyBean implements Serializable {
    private String search = "";
    private String obsah = "";
    private String log = "";
    private String zvoleny = "";
    private FilmWebTemp film = new FilmWebTemp();
    private List<FilmWebTemp> vysledkyHladania = null;
    @Inject
    CucacInfo ejb;
    @Inject
    FilmFacade filmDB;

    public String getZvoleny() {
        return zvoleny;
    }

    public void setZvoleny(String zvoleny) {
        this.zvoleny = zvoleny;
    }

    public FilmWebTemp getFilm() {
        return film;
    }

    public void setFilm(FilmWebTemp film) {
        this.film = film;
    }

    public NovyBean() {
    }
    
    public String getLog() {
        return log;
    }
    
    public void setLog(String nove) {
        log = nove;
    }
    
    public void pripisLog(String x) {
        log = log + x + ";";
    }
    
    public void setSearch(String s) {
        search = s;
    }
    
    public String getSearch() {
        return search;
    }
    
    public void setObsah(String o) {
        obsah = o;
    }
    
    public String getObsah() {
        return obsah;
    }
    
    public boolean isMameVysledky() {
//        mameVysledky = !(obsah.isEmpty());
//        return mameVysledky;
//        pripisLog("mame?: "+String.valueOf(vysledkyHladania!=null && vysledkyHladania.size()>0));
        return (vysledkyHladania!=null && vysledkyHladania.size()>0);
    }
    
    public List<FilmWebTemp> getVysledkyHladania() {
        return vysledkyHladania;
    }
    
    public void setVysledkyHladania(List<FilmWebTemp> x) {
        vysledkyHladania = x;
    }
    
    public void natiahniUdaje() {
        if (zvoleny==null || zvoleny.isEmpty()) {
            return;
        }
        setLog("ideme natiahnut link "+zvoleny);
        film = ejb.nacitajFilm(zvoleny);
        if (film!=null) {
            setLog("film nie je null "+film.getNazov());
        }
        return;
    }
    
    public void zrobHladanie() {
        ejb.zrobHladanie(search);
        vysledkyHladania = ejb.nacitajZoznam(search);
    }
    
    public boolean zalozFilm() {
        Film novy = filmDB.createFromObject(film);
        if (novy==null) {
            return false;
        } else {
            vynuluj();
        }
        return true;
    }
    
    public void vynuluj() {
        search = "";
        obsah = "";
        log = "";
        zvoleny = "";
        film = new FilmWebTemp();
        vysledkyHladania = null;
    }
    
}
