/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.weblogic;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.ejb.Stateless;

/**
 *
 * @author milan
 */
@Stateless
public class cucaInfo {
    HashMap<String, String> x = null;
    ArrayList<Film> list = null;
    private String obsaznik = "";
    
    public void zrobHladanie(String search) {
        list = nacitajZoznam(search);
    }
    
    public String getObsaznik() {
        return obsaznik;
    }
    
    public ArrayList<Film> getZoznam() {
        return list;
    }
    
    public ArrayList<Film> nacitajZoznam(String searchString) {
        String content = null;
        URLConnection connection = null;
        searchString = searchString.trim().replace(" ", "+");
        try {
          connection =  new URL("https://www.csfd.cz/hledat/?q="+searchString).openConnection();
          Scanner scanner = new Scanner(connection.getInputStream());
          scanner.useDelimiter("\\Z");
          content = scanner.next();
        }catch ( Exception ex ) {
        }
        if (content != null) {
            ArrayList<Film> retval = new ArrayList<Film>();
            int pos1 = 0;
            int pos2 = 0;
            int pos3 = 0;
            int pos4 = 0;
            String link = "";
            String nazov = "";
            int rok = 0;
            while (content.indexOf("class=\"film c1\">", pos1) > 0) {
                pos1 = content.indexOf("class=\"film c1\">", pos1);
                pos2 = content.lastIndexOf("href=\"", pos1);
                pos3 = content.indexOf("</a>", pos1);
                pos4 = content.indexOf("</p>", pos3);
                if (pos1 > 0 && pos2 > 0 && pos3 > 0) {
                    link = content.substring(pos2+6, pos1);
                    nazov = content.substring(pos1+16, pos3);
                    rok = 0;
                    if (pos4 > 0) {
                        try {
                            rok = Integer.parseInt(content.substring(pos4-4, pos4));
                        } catch (Exception e) {rok=0;}
                    }
                    Film x = new Film();
                    x.setNazov(nazov);
                    x.setLink(link);
                    x.setRok(rok);
                    retval.add(x);
                }
                pos1=pos1+1;
            }
            return retval; 
        }
        return null;
    }

    public Film nacitajFilm(String link) {
        String content = null;
        URLConnection connection = null;
        Film nacitany = null;
        try {
          connection =  new URL("link").openConnection();
          Scanner scanner = new Scanner(connection.getInputStream());
          scanner.useDelimiter("\\Z");
          content = scanner.next();
        }catch ( Exception ex ) {
        }
        if (content != null) {
            
        }
        return nacitany;
    }
}
