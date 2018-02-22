/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.weblogic;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.ejb.Stateless;

/**
 *
 * @author milan
 */
@Stateless
public class CucacInfo {
    HashMap<String, String> x = null;
    List<FilmWebTemp> list = null;
    private String obsaznik = "";
    
    public void zrobHladanie(String search) {
        list = nacitajZoznam(search);
    }
    
    public String getObsaznik() {
        return obsaznik;
    }
    
    public List<FilmWebTemp> getZoznam() {
        return list;
    }
    
    public List<FilmWebTemp> nacitajZoznam(String searchString) {
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
            List<FilmWebTemp> retval = new ArrayList<FilmWebTemp>();
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
                    link = content.substring(pos2+6, pos1-1).replace("\"", "").trim();
                    nazov = content.substring(pos1+16, pos3);
                    rok = 0;
                    if (pos4 > 0) {
                        try {
                            rok = Integer.parseInt(content.substring(pos4-4, pos4));
                        } catch (Exception e) {rok=0;}
                    }
                    FilmWebTemp x = new FilmWebTemp();
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

    public FilmWebTemp nacitajFilm(String link) {
        String content = null;
        URLConnection connection = null;
        FilmWebTemp nacitany = null;
        link = "https://www.csfd.cz" + link + "prehled/";
        try {
            connection =  new URL(link).openConnection();
            InputStream instr = null;
            if (connection.getContentEncoding().equalsIgnoreCase("gzip")) {
                instr = new GZIPInputStream(connection.getInputStream());
            } else {
                instr = new InflaterInputStream(connection.getInputStream(),new Inflater(true));
            }
            Scanner scanner = new Scanner(instr);
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            content = connection.getContentEncoding() + " --- " + content;
        }catch ( Exception ex ) {
        }
        if (content != null) {
            nacitany = new FilmWebTemp();
            nacitany.setLink(link);
            nacitany.setContent(content);
            int pos1 = 0;
            int pos2 = 0;
            int posPom = 0;
            // NAZOV
            pos1 = content.indexOf("<h1 itemprop=\"name\">");
            if (pos1>0) {
                pos2 = content.indexOf("</h1>", pos1);
                if (pos2>0) {
                    nacitany.setNazov(content.substring(pos1+20, pos2).trim());
                    posPom = pos2;
                }
            }

            // ZANRE
            pos1 = content.indexOf("<p class=\"genre\">");
            if (pos1>0) {
                pos2 = content.indexOf("</p>", pos1);
                if (pos2>0) {
                    nacitany.setZaner(content.substring(pos1+17, pos2).trim());
                    posPom = pos2;
                }
            }

            // KRAJINY
            pos1 = content.indexOf("<p class=\"origin\">");
            if (pos1>0) {
                pos2 = content.indexOf("</p>", pos1);
                if (pos2>0) {
                    String krajiny = content.substring(pos1+18, pos2).replace(",", "");
                    nacitany.setKrajina(krajiny);
                    posPom = pos2;
                }
            }

            // ROK
            pos1 = content.indexOf("<span itemprop=\"dateCreated\">");
            if (pos1>0) {
                pos2 = content.indexOf("</span>", pos1);
                if (pos2>0) {
                    nacitany.setRok(Integer.parseInt(content.substring(pos1+29, pos2).trim()));
                    posPom = pos2;
                    
                    // MINUTAZ
                    pos2 = content.indexOf("</p>", pos2);
                    pos1 = content.lastIndexOf("</span>,", pos2);
                    if (pos1 > 0 && pos2 > 0) {
                        int minutaz = 0;
                        try {
                            minutaz = Integer.parseInt(content.substring(pos1+8, pos2).replace("min", ""));
                        } catch (Exception e) {}
                        nacitany.setMinutaz(minutaz);
                    }
                }
            }
            

            // ALT NAZVY
            List<String> alt = new ArrayList<String>();
            pos1 = content.indexOf("<ul class=\"names\">", posPom);
            if (pos1>0) {
                pos2 = content.indexOf("</ul>", pos1);
                if (pos2>0) {
                    posPom = pos2;
                    String nazvy = content.substring(pos1+18, pos2);
                    pos1=0; pos2=0;
                    while (nazvy.indexOf("<h3>", pos1)>0) {
                        pos1 = nazvy.indexOf("<h3>", pos1);
                        pos2 = nazvy.indexOf("</h3>", pos1);
                        if (pos1 > 0 && pos2 >0) {
                            alt.add(nazvy.substring(pos1+3, pos2).trim());
                        }
                        pos1 = pos1+1;
                    }
                    nacitany.setAltNazvyList(alt);
                }
            }
            
            // HERCI
            List<String> h = new ArrayList<String>();
            pos1 = content.indexOf("<h4>Hrají:</h4>", posPom);
            if (pos1>0) {
                pos2 = content.indexOf("</span>", pos1);
                if (pos2>0) {
                    posPom = pos2;
                    String herci = content.substring(pos1, pos2);
                    pos1=0; pos2=0;
                    while (herci.indexOf("</a>", pos2)>0) {
                        pos2 = herci.indexOf("</a>", pos2);     // tu hladam najprv koniec
                        pos1 = herci.lastIndexOf(">", pos2);    // a odneho zaciatok
                        if (pos1 > 0 && pos2 >0) {
                            h.add(herci.substring(pos1+1, pos2).trim());
                        }
                        pos2 = pos2+1;
                    }
                    nacitany.setHerciList(h);
                }
            }
        }



        return nacitany;
    }
}
