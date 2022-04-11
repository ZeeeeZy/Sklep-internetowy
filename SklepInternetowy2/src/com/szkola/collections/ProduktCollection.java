package com.szkola.collections;

import com.szkola.iterators.Container;
import com.szkola.iterators.Iterator;
import com.szkola.iterators.ProduktIterator;
import com.szkola.obiekty.Produkt;
import com.szkola.singletony.ProduktSingleton;

import java.io.FileNotFoundException;
import java.util.List;

public class ProduktCollection implements Container {

    private final ProduktSingleton produktSingleton = new ProduktSingleton();

    @Override
    public Iterator getIterator() {
        return new ProduktIterator(getProduktList());
    }

    public List<Produkt> getProduktList() {
        List<Produkt> produktList = null;
        try {
            produktList = produktSingleton.getProducts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return produktList;
    }

    public void dodajProdukt(Produkt produkt) {
        produktSingleton.dodajProdukt(produkt);
    }
}
