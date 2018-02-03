/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.weblogic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author milan
 */
public class Film {
    private String nazov = "";
    private String link = "";
    private int rok = 0;
    private ArrayList<String> herci = new ArrayList<String>();
    private ArrayList<String> altNazvy = new ArrayList<String>();
    private ArrayList<String> krajina = new ArrayList<String>();
    private ArrayList<String> zaner = new ArrayList<String>();
    private int minutaz = 0;

    public int getMinutaz() {
        return minutaz;
    }

    public void setMinutaz(int minutaz) {
        this.minutaz = minutaz;
    }

    public void setMinutaz(String minutaz) {
        minutaz = minutaz.replace("min", "").trim();
        this.minutaz = Integer.parseInt(minutaz);
    }

    public String getNazovRok() {
        if (rok==0) {
            return nazov;
        }
        return nazov + " (" + String.valueOf(rok) + ")";
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public ArrayList<String> getHerciList() {
        return herci;
    }

    public String getHerci() {
        String r = "";
        if (herci!=null && herci.size()>0) {
            for (String herec : herci) {
                r = r + (r.isEmpty() ? "" : " / ") + herec;
            }
        }
        return r;
    } 

    public void setHerciList(ArrayList<String> herci) {
        this.herci = herci;
    }

    public void setHerci(String herci) {
        this.herci = (ArrayList<String>) Arrays.asList(herci.split("\\s*,\\s*"));
    }

    public ArrayList<String> getAltNazvyList() {
        return altNazvy;
    }

    public String getAltNazvy() {
        String r = "";
        if (altNazvy!=null && altNazvy.size()>0) {
            for (String alt : altNazvy) {
                r = r + (r.isEmpty() ? "" : " / ") + alt;
            }
        }
        return r;
    }

    public void setAltNazvyList(ArrayList<String> altNazvy) {
        this.altNazvy = altNazvy;
    }

    public void setAltNazvy(String nazvy) {
        this.altNazvy = (ArrayList<String>) Arrays.asList(nazvy.split("\\s*,\\s*"));
    }

    public ArrayList<String> getKrajinaList() {
        return krajina;
    }

    public String getKrajina() {
        String r = "";
        if (krajina!=null && krajina.size()>0) {
            for (String k : krajina) {
                r = r + (r.isEmpty() ? "" : " / ") + k;
            }
        }
        return r;
    }

    public void setKrajinaList(ArrayList<String> krajina) {
        this.krajina = krajina;
    }

    public void setKrajina(String krajiny) {
        this.krajina = (ArrayList<String>) Arrays.asList(krajiny.split("\\s*,\\s*"));
    }

    public ArrayList<String> getZanerList() {
        return zaner;
    }

    public String getZaner() {
        String r = "";
        if (zaner!=null && zaner.size()>0) {
            for (String z : zaner) {
                r = r + (r.isEmpty() ? "" : " / ") + z;
            }
        }
        return r;
    }

    public void setZanerList(ArrayList<String> zaner) {
        this.zaner = zaner;
    }
    
    public void setZaner(String zanre) {
        this.zaner = (ArrayList<String>) Arrays.asList(zanre.split("\\s*,\\s*"));
    }
    
}
