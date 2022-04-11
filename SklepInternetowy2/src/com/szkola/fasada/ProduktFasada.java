package com.szkola.fasada;

import com.szkola.collections.ProduktCollection;
import com.szkola.enums.Bransoleta;
import com.szkola.enums.SystemOperacyjny;
import com.szkola.obiekty.Kategoria;
import com.szkola.obiekty.Koszyk;
import com.szkola.obiekty.Produkt;
import com.szkola.repositories.KoszykRepository;
import com.szkola.singletony.BransoletaSingleton;
import com.szkola.singletony.KategoriaSingleton;
import com.szkola.singletony.SystemOperacyjnySingleton;

import java.math.BigDecimal;
import java.util.List;

public class ProduktFasada {

    private final KategoriaSingleton kategoriaSingleton;

    private final SystemOperacyjnySingleton systemOperacyjnySingleton;

    private final BransoletaSingleton bransoletaSingleton;

    private final KoszykRepository koszykRepository;

    private final ProduktCollection produktCollection;

    public ProduktFasada() {
        this.systemOperacyjnySingleton = new SystemOperacyjnySingleton();
        this.kategoriaSingleton = new KategoriaSingleton();
        this.bransoletaSingleton = new BransoletaSingleton();
        this.koszykRepository = new KoszykRepository();
        this.produktCollection = new ProduktCollection();
    }

    public List<Kategoria> pobierzKategorieProduktow() {
        return kategoriaSingleton.pobierzWszystkieKategorie();
    }

    public List<SystemOperacyjny> pobierzSystemyOperacyjne() {
        return systemOperacyjnySingleton.pobierzWszystkieSystemyOperacyjne();
    }

    public List<Bransoleta> pobierzBransolety() {
        return bransoletaSingleton.pobierzWszystkieRodzajeBransolet();
    }

    public Koszyk pobierzKoszyk() {
        return koszykRepository.getKoszyk();
    }

    public boolean koszykJestPusty() {
        return koszykRepository.getKoszyk().getListaProduktow().isEmpty();
    }

    public List<Produkt> pobierzListeProduktow() {
        return produktCollection.getProduktList();
    }

    public BigDecimal pobierzWartoscKoszyka() {
        return koszykRepository.getKoszyk().getListaProduktow().stream().map(Produkt::getCena)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void dodajDoKoszyka(Produkt produkt) {
        koszykRepository.dodajDoKoszyka(produkt);
    }

    public Produkt znajdzProduktPoId(Long id) {
        return (Produkt) produktCollection.getIterator().znajdzObiektPoId(id);
    }

    public List<Produkt> znajdzProduktyPoNazwie(String nazwa) {
        return produktCollection.getIterator().znajdzPoNazwie(nazwa);
    }

    public void dodajProdukt(Produkt produkt) {
        produktCollection.dodajProdukt(produkt);
    }

    public Long pobierzOstatnieId() {
        return produktCollection.getIterator().getLastId();
    }
}
