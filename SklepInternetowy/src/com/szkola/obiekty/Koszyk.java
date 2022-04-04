package com.szkola.obiekty;

import com.szkola.factory.OdmianaFactory;

import java.util.ArrayList;
import java.util.List;

public class Koszyk implements OdmianaFactory {

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

    @Override
    public void usunOstatniElement() {
        listaProduktow.remove(listaProduktow.size() - 1);
    }
}
