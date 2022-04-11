package com.szkola.factory;

import com.szkola.obiekty.IProdukt;
import com.szkola.obiekty.Produkt;
import com.szkola.produkty.*;

public class ProduktFactory extends ProduktAbstractFactory {

    @Override
    protected IProdukt utworzProdukt(Produkt produkt) {
        switch (produkt.getKategoria()) {
            case TELEWIZORY:
                return new Telewizor();
            case LAPTOPY:
                return new Laptop();
            case KOMPUTERY:
                return new Komputer();
            case ZEGARKI:
                return new Zegarek();
            case TELEFONY:
                return new Telefon();
            default:
                return null;
        }
    }
}
