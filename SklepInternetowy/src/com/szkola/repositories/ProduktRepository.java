package com.szkola.repositories;

import com.szkola.iterators.Container;
import com.szkola.iterators.Iterator;
import com.szkola.obiekty.Produkt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProduktRepository implements Container {
    private final List<Produkt> produktList = new ArrayList<>();

    public List<Produkt> getProduktList() {
        return produktList;
    }

    public Long getLastId() {
        if (produktList.isEmpty()) {
            return 1L;
        } else {
            return produktList.get(produktList.size() - 1).getId() + 1;
        }
    }

    public void dodajProdukt(Produkt produkt) {
        produktList.add(produkt);
    }

    public List<Produkt> znajdzProduktPoNazwie(String nazwa) {
        return produktList.stream().filter(produkt -> produkt.getNazwa().equalsIgnoreCase(nazwa)).collect(Collectors.toList());
    }

    public Produkt znajdzProduktPoId(Long id) {
        return produktList.stream().filter(produkt -> produkt.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Iterator getIterator() {
        return null;
    }

    private class ProduktIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            return index < produktList.size();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return produktList.get(index++);
            }
            return null;
        }
    }
}
