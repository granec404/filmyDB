/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Iterator;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import sk.milang.weblogic.cucaInfo;

/**
 *
 * @author milan
 */
@Named(value = "appBean")
@ApplicationScoped
public class appBean {
    private String privitanie = "hello";
    private String search = "";
    private String obsah = "";
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
        mameVysledky = !(obsah.isEmpty());
        return mameVysledky;
    }
    
    public void zrobHladanie() {
        ejb.zrobHladanie(search);
        HashMap<String, String> zoznam = ejb.getZoznam();
        if (zoznam!=null) {
            obsah = "";
            Iterator it = zoznam.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                obsah = obsah + pair.getValue() + "<br/>";
//                it.remove(); // avoids a ConcurrentModificationException
            }
        }
//        obsah = ejb.getObsaznik();
    }
    
}
