package com.szkola.obiekty;

import com.szkola.factory.OdmianaFactory;

import java.util.ArrayList;
import java.util.List;

public class Kategoria implements OdmianaFactory {

    private Long id;

    private String nazwaKategorii;

    private List<Produkt> produktyWDanejKategorii = new ArrayList<>();

    public List<Produkt> getProduktyWDanejKategorii() {
        return produktyWDanejKategorii;
    }

    public void setProduktyWDanejKategorii(List<Produkt> produktyWDanejKategorii) {
        this.produktyWDanejKategorii = produktyWDanejKategorii;
    }

    public Kategoria(String nazwa) {
        this.nazwaKategorii = nazwa;
    }

    @Override
    public void usunOstatniElement() {
        getProduktyWDanejKategorii().remove(getProduktyWDanejKategorii().size() - 1);
    }

    public String getNazwaKategorii() {
        return nazwaKategorii;
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    @Override
    public String toString() {
        return id.toString() + ". " + nazwaKategorii;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
