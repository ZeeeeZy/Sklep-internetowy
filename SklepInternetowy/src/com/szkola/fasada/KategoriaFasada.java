package com.szkola.fasada;

import com.szkola.obiekty.Kategoria;
import com.szkola.singletony.KategoriaSingleton;

import java.util.List;
import java.util.Optional;

public class KategoriaFasada {

    private final KategoriaSingleton kategoriaSingleton;

    private Long id = Long.valueOf(1);

    public KategoriaFasada() {
        this.kategoriaSingleton = new KategoriaSingleton();
    }

    public List<Kategoria> pobierzKategorie() {
        return kategoriaSingleton.getKategorie();
    }

    public void dodajKategorie(Kategoria kategoria) {
        kategoriaSingleton.dodajKategorie(kategoria, id);
        id++;
    }

    public Kategoria pobierzKategoriePoId(Long id) {
        Optional<Kategoria> kategoria = kategoriaSingleton.getKategorie().stream().filter(kat -> kat.getId().equals(id)).findFirst();
        return kategoria.orElse(null);
    }

    public void usunKategoriePoId(Long id) {
        kategoriaSingleton.usunKategorie(id);
    }
}
