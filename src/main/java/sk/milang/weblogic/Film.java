/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.weblogic;

/**
 *
 * @author milan
 */
public class Film {
    private String nazov = "";
    private String link = "";
    private int rok = 0;
    private String herci = "";
    private String altNazvy = "";
    private String krajina = "";
    private String zaner = "";

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

    public String getHerci() {
        return herci;
    }

    public void setHerci(String herci) {
        this.herci = herci;
    }

    public String getAltNazvy() {
        return altNazvy;
    }

    public void setAltNazvy(String altNazvy) {
        this.altNazvy = altNazvy;
    }

    public String getKrajina() {
        return krajina;
    }

    public void setKrajina(String krajina) {
        this.krajina = krajina;
    }

    public String getZaner() {
        return zaner;
    }

    public void setZaner(String zaner) {
        this.zaner = zaner;
    }
    
    
    
}
