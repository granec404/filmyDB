/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.weblogic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author milan
 */
public class FilmWebTemp {
    private String nazov = "";
    private String link = "";
    private int rok = 0;
    private List<String> herci = new ArrayList<>();
    private List<String> altNazvy = new ArrayList<>();
    private List<String> krajina = new ArrayList<>();
    private List<String> zaner = new ArrayList<>();
    private int minutaz = 0;
    private String content="";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
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

    public List<String> getHerciList() {
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

    public void setHerciList(List<String> herci) {
        this.herci = herci;
    }

    public void setHerci(String herci) {
        this.herci = Arrays.asList(herci.split("\\s*/\\s*"));
    }

    public List<String> getAltNazvyList() {
        return altNazvy;
    }

    public String getAltNazvy() {
        return getAltNazvyAsString(altNazvy);
    }
    
    public static List<String> getAltNazvyAsList(String nazvy) {
        return Arrays.asList(nazvy.split("\\s*/\\s*"));
    }
    
    public static String getAltNazvyAsString(List<String> nazvy) {
        String r = "";
        if (nazvy!=null && nazvy.size()>0) {
            for (String alt : nazvy) {
                r = r + (r.isEmpty() ? "" : " / ") + alt;
            }
        }
        return r;
    }

    public void setAltNazvyList(List<String> altNazvy) {
        this.altNazvy = altNazvy;
    }

    public void setAltNazvy(String nazvy) {
        this.altNazvy = getAltNazvyAsList(nazvy);
    }

    public List<String> getKrajinaList() {
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

    public void setKrajinaList(List<String> krajina) {
        this.krajina = krajina;
    }

    public void setKrajina(String krajiny) {
        this.krajina = Arrays.asList(krajiny.split("\\s*/\\s*"));
    }

    public List<String> getZanerList() {
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

    public void setZanerList(List<String> zaner) {
        this.zaner = zaner;
    }
    
    public void setZaner(String zanre) {
        this.zaner = Arrays.asList(zanre.split("\\s*/\\s*"));
    }
    
}
