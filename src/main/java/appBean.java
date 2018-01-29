/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    public boolean mameVysledky() {
        return !(obsah.isEmpty());
    }
    
    public void zrobHladanie() {
        ejb.zrobHladanie(search);
        obsah = ejb.getObsaznik();
    }
    
}
