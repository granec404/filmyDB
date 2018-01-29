/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author milan
 */
@Named(value = "appBean")
@ApplicationScoped
public class appBean {
    private String privitanie = "hello";

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
    
}
