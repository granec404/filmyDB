/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import sk.milang.weblogic.cucaInfo;

/**
 *
 * @author milan
 */
@Named(value = "appBean")
@SessionScoped
public class appBean implements Serializable {
    private String privitanie = "hello";
    private String search = "";
    private String obsah = "";
    private HashMap<String, String> vysledkyHladania = null;
    private boolean mameVysledky = false;
    @Inject
    cucaInfo ejb;

    /**
     * Creates a new instance of appBean
     */
    public appBean() {
    }
    
    public String getPrivitanie() {
        return privitanie;
    }
    
    public void setPrivitanie(String nove) {
        privitanie = nove;
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
        return (vysledkyHladania!=null && vysledkyHladania.size()>0);
    }
    
    public HashMap<String, String> getVysledkyHladania() {
        return vysledkyHladania;
    }
    
    public setVysledkyHladania(HashMap<String, String> x) {
        vysledkyHladania = x;
    }
    
    public void zrobHladanie() {
        ejb.zrobHladanie(search);
        vysledkyHladania = ejb.getZoznam();
//        HashMap<String, String> zoznam = ejb.getZoznam();
//        if (zoznam!=null) {
//            obsah = "";
//            Iterator it = zoznam.entrySet().iterator();
//            while (it.hasNext()) {
//                HashMap.Entry pair = (HashMap.Entry)it.next();
//                obsah = obsah + pair.getValue() + "<br/>";
//                it.remove(); // avoids a ConcurrentModificationException
//            }
//        }
        
//        obsah = ejb.getObsaznik();
    }
    
}
