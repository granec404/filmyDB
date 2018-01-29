/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.weblogic;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import javax.ejb.Stateless;

/**
 *
 * @author milan
 */
@Stateless
public class cucaInfo {
    HashMap<String, String> x = null;
    private String obsaznik = "";
    
    public void zrobHladanie(String search) {
        x = dajZoznam(search);
        if (x!=null) {
            String a = "";
            Iterator it = x.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                a = a + "<a href='"+pair.getValue()+"'>"+pair.getKey()+"</a><br/>";
                it.remove(); // avoids a ConcurrentModificationException
            }
            obsaznik = a;
        }
    }
    
    public String getObsaznik() {
        return obsaznik;
    }
    
    public HashMap<String, String> dajZoznam(String searchString) {
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
            HashMap<String, String> retval = new HashMap<String, String>();
            retval.put("obsah", content);
            return retval; 
        }
        return null;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
