package com.szkola.singletony;

import com.szkola.obiekty.Kategoria;

import java.util.ArrayList;
import java.util.List;

public class KategoriaSingleton {

    public List<Kategoria> pobierzWszystkieKategorie() {
        List<Kategoria> kategorie = new ArrayList<>();
        kategorie.add(Kategoria.KOMPUTERY);
        kategorie.add(Kategoria.LAPTOPY);
        kategorie.add(Kategoria.TELEFONY);
        kategorie.add(Kategoria.TELEWIZORY);
        kategorie.add(Kategoria.ZEGARKI);
        return kategorie;
    }
}
