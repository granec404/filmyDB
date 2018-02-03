/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import sk.milang.weblogic.Film;
import sk.milang.weblogic.cucaInfo;

/**
 *
 * @author milan
 */
@Named(value = "novyBean")
@ViewScoped
public class NovyBean implements Serializable {
    private String privitanie = "hello";
    private String search = "";
    private String obsah = "";
    private String log = "";
    private String zvoleny = "";
    private Film film = new Film();
    private ArrayList<Film> vysledkyHladania = null;
    private boolean mameVysledky = false;
    @Inject
    cucaInfo ejb;

    public String getZvoleny() {
        return zvoleny;
    }

    public void setZvoleny(String zvoleny) {
        this.zvoleny = zvoleny;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
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
    
    public ArrayList<Film> getVysledkyHladania() {
        return vysledkyHladania;
    }
    
    public void setVysledkyHladania(ArrayList<Film> x) {
        vysledkyHladania = x;
    }
    
    public void natiahniUdaje() {
        if (zvoleny==null || zvoleny.isEmpty()) {
            return;
        }
        film = ejb.nacitajFilm(zvoleny);
        return;
    }
    
    public void zrobHladanie() {
        ejb.zrobHladanie(search);
        vysledkyHladania = ejb.nacitajZoznam(search);
    }
    
}
