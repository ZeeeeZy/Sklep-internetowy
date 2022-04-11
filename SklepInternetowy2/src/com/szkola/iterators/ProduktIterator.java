package com.szkola.iterators;

import com.szkola.obiekty.Produkt;

import java.util.ArrayList;
import java.util.List;

public class ProduktIterator implements Iterator {

        private final List<Produkt> produktList;

        int index;

    public ProduktIterator(List<Produkt> produktList) {
        this.produktList = produktList;
    }

    @Override
        public boolean hasNext() {
            return index < produktList.size();
        }

        @Override
        public Object next() {
            if (this.hasNext()){
                return produktList.get(index++);
            }
            return null;
        }

        @Override
        public Long getLastId() {
            Produkt produkt = null;
            for (Iterator iter = this; iter.hasNext(); ) {
                produkt = (Produkt) iter.next();
            }
            if (produkt == null) {
                return 1L;
            } else {
                return produkt.getId() + 1;
            }
        }

        @Override
        public ArrayList znajdzPoNazwie(String nazwa) {
            List<Produkt> produkty = new ArrayList<>();
            for (Iterator iter = this; iter.hasNext();) {
                Produkt produkt = (Produkt) iter.next();
                assert produkt != null;
                if (produkt.getNazwa().equalsIgnoreCase(nazwa)) {
                    produkty.add(produkt);
                }
            }
            return new ArrayList(produkty);
        }

        @Override
        public Produkt znajdzObiektPoId(Long id) {
            for (Iterator iter = this; iter.hasNext();) {
                Produkt produkt = (Produkt) iter.next();
                assert produkt != null;
                if (produkt.getId().equals(id)) {
                    return produkt;
                }
            }
            return null;
        }
}
