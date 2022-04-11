package com.szkola.obiekty;

import java.util.ArrayList;
import java.util.List;

public class Koszyk {

    List<Produkt> listaProduktow = new ArrayList<>();

    public List<Produkt> getListaProduktow() {
        return listaProduktow;
    }

    public void setListaProduktow(List<Produkt> listaProduktow) {
        this.listaProduktow = listaProduktow;
    }

    @Override
    public String toString() {
        return "listaProduktow:" + listaProduktow;
    }

}
