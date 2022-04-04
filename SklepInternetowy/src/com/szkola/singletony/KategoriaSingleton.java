package com.szkola.singletony;

import com.szkola.obiekty.Kategoria;

import java.util.ArrayList;
import java.util.List;

public class KategoriaSingleton {

    private List<Kategoria> kategorie = new ArrayList<>();

    public List<Kategoria> getKategorie() {
        return kategorie;
    }

    public void setKategorie(List<Kategoria> kategorie) {
        this.kategorie = kategorie;
    }

    public void dodajKategorie(Kategoria kategoria, Long id) {
        kategoria.setId(id);
        this.kategorie.add(kategoria);
    }

    public void usunKategorie(Long id) {
        this.kategorie.remove(this.kategorie.stream().filter(kat -> kat.getId().equals(id)).findFirst().get());
    }

    public Kategoria pobierzKategoriePoId(Long id) {
        return this.kategorie.stream().filter(kategoria -> kategoria.getId().equals(id)).findFirst().orElse(null);
    }
}
