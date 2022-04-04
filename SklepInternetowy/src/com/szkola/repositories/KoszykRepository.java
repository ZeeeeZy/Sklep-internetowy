package com.szkola.repositories;

import com.szkola.obiekty.Koszyk;
import com.szkola.obiekty.Produkt;

public class KoszykRepository {

    private final Koszyk koszyk = new Koszyk();

    public Koszyk getKoszyk() {
        return koszyk;
    }

    public void dodajDoKoszyka(Produkt produkt) {
        koszyk.getListaProduktow().add(produkt);
    }
}
