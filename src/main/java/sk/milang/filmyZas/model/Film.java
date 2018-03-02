/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author fskgranam
 */
@Entity
@Table(name = "Film")
@NamedQueries({
    @NamedQuery(name = "Film.najdiVsetky", query = "SELECT f FROM Film f"),
    @NamedQuery(name = "Film.najdiId", query = "SELECT f FROM Film f WHERE f.id = :id"),
    @NamedQuery(name = "Film.najdiNazov", query = "SELECT f FROM Film f WHERE f.nazov LIKE :nazov"),
    @NamedQuery(name = "Film.najdiPokrocile", query = "SELECT DISTINCT f FROM Film f JOIN f.herecList h JOIN f.krajinaList k JOIN f.zanerList z WHERE (lower(trim(f.nazov)) LIKE lower(trim(:nazov)) OR lower(trim(f.altNazvy)) LIKE lower(trim(:nazov))) AND (f.rok >= :rokOd AND f.rok <= :rokDo) AND (f.minutaz >= :minutazOd AND f.minutaz <= :minutazDo) AND lower(trim(h.meno)) LIKE lower(trim(:herec)) AND lower(trim(z.nazov)) LIKE lower(trim(:zaner)) AND lower(trim(k.nazov)) LIKE lower(trim(:krajina)) ")})
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Nazov")
    private String nazov;
    @Column(name = "AltNazvy")
    private String altNazvy;
    @Column(name = "Link")
    private String link;
    @Column(name = "Rok")
    private int rok;
    @Column(name = "Minutaz")
    private int minutaz;
    
    @ManyToMany
    @JoinTable(
        name="FilmHerec",
        joinColumns=@JoinColumn(name="FilmId", referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(name="HerecId", referencedColumnName="ID"))
    private List<Herec> herecList;
    
    @ManyToMany
    @JoinTable(
        name="FilmZaner",
        joinColumns=@JoinColumn(name="FilmId", referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(name="ZanerId", referencedColumnName="ID"))
    private List<Zaner> zanerList;
    
    @ManyToMany
    @JoinTable(
        name="FilmKrajina",
        joinColumns=@JoinColumn(name="FilmId", referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(name="KrajinaId", referencedColumnName="ID"))
    private List<Krajina> krajinaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getAltNazvy() {
        return altNazvy;
    }

    public void setAltNazvy(String altNazvy) {
        this.altNazvy = altNazvy;
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

    public int getMinutaz() {
        return minutaz;
    }

    public void setMinutaz(int minutaz) {
        this.minutaz = minutaz;
    }
    
    public List<Herec> getHerecList() {
        return herecList;
    }

    public void setHerecList(List<Herec> herecList) {
        this.herecList = herecList;
    }
    
    public void addHerec(Herec h) {
        if (this.herecList==null) {
            this.herecList = new ArrayList<Herec>();
        }
        this.herecList.add(h);
    }

    public List<Zaner> getZanerList() {
        return zanerList;
    }

    public void setZanerList(List<Zaner> zanerList) {
        this.zanerList = zanerList;
    }
    
    public void addZaner(Zaner z) {
        if (this.zanerList==null) {
            this.zanerList = new ArrayList<Zaner>();
        }
        this.zanerList.add(z);
    }

    public List<Krajina> getKrajinaList() {
        return krajinaList;
    }

    public void setKrajinaList(List<Krajina> krajinaList) {
        this.krajinaList = krajinaList;
    }
    
    public void addKrajina(Krajina k) {
        if (this.krajinaList==null) {
            this.krajinaList = new ArrayList<Krajina>();
        }
        this.krajinaList.add(k);
    }
    
    public String getZanerListAsString() {
        if (zanerList==null) {
            return "";
        }
        String result="";
        for (Zaner z:zanerList) {
            result = result + (result.isEmpty()?"":" / ") + z.getNazov();
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sk.milang.filmyZas.model.Film[ id=" + id + " ]";
    }
    
}
