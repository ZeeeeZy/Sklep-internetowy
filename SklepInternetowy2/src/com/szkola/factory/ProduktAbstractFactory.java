package com.szkola.factory;

import com.szkola.obiekty.IProdukt;
import com.szkola.obiekty.Produkt;

public abstract class ProduktAbstractFactory {

    protected abstract IProdukt utworzProdukt(Produkt produkt);

    public IProdukt getProdukt(Produkt produkt) {
        return this.utworzProdukt(produkt);
    }

}
