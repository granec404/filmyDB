/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.controller;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sk.milang.filmyZas.facade.FilmFacade;
import sk.milang.filmyZas.facade.ZanerFacade;
import sk.milang.filmyZas.model.Film;
import sk.milang.filmyZas.model.Zaner;

/**
 *
 * @author fskgranam
 */

@Named(value = "vyhladavanieBean")
@ViewScoped
public class Vyhladavanie implements Serializable {
    @EJB
    ZanerFacade zanerF;
    @EJB
    FilmFacade filmDB;
    private List<String> zanre = new ArrayList<>();
    private List<Film> filmy = new ArrayList<>();
    
    private String nazov="";
    private String herec="";
    private String krajina="";
    private int rok1 = 0;
    private int rok2 = 9999;
    private int minutaz1 = 0;
    private int minutaz2 = 1000;
    private String zaner = "";
    
    public boolean isSuVysledky() {
        return (filmy!=null && filmy.size()>0);
    }
    
    public void resetZanre() {
        zanre = new ArrayList<>();
    }
    
    public void addZaner(String z) {
        zanre.add(z);
    }
    
    public void nacitajZanre() {
        resetZanre();
        addZaner("");
        List<Zaner> all = zanerF.findAll();
        if (all!=null) {
            for(Zaner z: all) {
                addZaner(z.getNazov());
            }
        }
    }
    
    public void search() {
        filmy = filmDB.najdiFilmy(nazov, rok1, rok2, minutaz1, minutaz2, zaner, herec, krajina);
    }

    public List<Film> getFilmy() {
        return filmy;
    }

    public void setFilmy(List<Film> filmy) {
        this.filmy = filmy;
    }
    

    public List<String> getZanre() {
        return zanre;
    }

    public void setZanre(List<String> zanre) {
        this.zanre = zanre;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getHerec() {
        return herec;
    }

    public void setHerec(String herec) {
        this.herec = herec;
    }

    public String getKrajina() {
        return krajina;
    }

    public void setKrajina(String krajina) {
        this.krajina = krajina;
    }

    public String getRok() {
        return String.valueOf(rok1)+"-"+String.valueOf(rok2);
    }

    public void setRok(String rok) {
        if (!rok.contains("-")) {
            return;
        }
        String[] l = rok.split("-");
        int b1, b2;
        try {
            b1 = Integer.parseInt(l[0].trim());
            b2 = Integer.parseInt(l[1].trim());
        } catch (Exception e) {
            return;
        }
        rok1 = b1;
        rok2 = b2;
    }
    
    public String getMinutaz() {
        return String.valueOf(minutaz1)+"-"+String.valueOf(minutaz2);
    }
    
    public void setMinutaz(String minutaz) {
        if (!minutaz.contains("-")) {
            return;
        }
        String[] l = minutaz.split("-");
        int b1, b2;
        try {
            b1 = Integer.parseInt(l[0].trim());
            b2 = Integer.parseInt(l[1].trim());
        } catch (NumberFormatException e) {
            return;
        }
        minutaz1 = b1;
        minutaz2 = b2;
    }

    public int getMinutaz1() {
        return minutaz1;
    }

    public void setMinutaz1(int minutaz1) {
        this.minutaz1 = minutaz1;
    }

    public int getMinutaz2() {
        return minutaz2;
    }

    public void setMinutaz2(int minutaz2) {
        this.minutaz2 = minutaz2;
    }

    public String getZaner() {
        return zaner;
    }

    public void setZaner(String zaner) {
        this.zaner = zaner;
    }
    
    
    
}
