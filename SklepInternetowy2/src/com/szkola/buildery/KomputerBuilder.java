package com.szkola.buildery;

import com.szkola.enums.SystemOperacyjny;
import com.szkola.obiekty.Kategoria;
import com.szkola.produkty.Komputer;

import java.math.BigDecimal;

public final class KomputerBuilder {
    private Long id;
    private Kategoria kategoria;
    private BigDecimal cena;
    private Integer ilosc;
    private String nazwa;
    private Long taktowanieProcesora;
    private Long iloscPamieciRAM;
    private String kartaGraficzna;
    private SystemOperacyjny systemOperacyjny;

    private KomputerBuilder() {
    }

    public static KomputerBuilder aKomputer() {
        return new KomputerBuilder();
    }

    public KomputerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public KomputerBuilder withKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
        return this;
    }

    public KomputerBuilder withCena(BigDecimal cena) {
        this.cena = cena;
        return this;
    }

    public KomputerBuilder withIlosc(Integer ilosc) {
        this.ilosc = ilosc;
        return this;
    }

    public KomputerBuilder withNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    public KomputerBuilder withTaktowanieProcesora(Long taktowanieProcesora) {
        this.taktowanieProcesora = taktowanieProcesora;
        return this;
    }

    public KomputerBuilder withIloscPamieciRAM(Long iloscPamieciRAM) {
        this.iloscPamieciRAM = iloscPamieciRAM;
        return this;
    }

    public KomputerBuilder withKartaGraficzna(String kartaGraficzna) {
        this.kartaGraficzna = kartaGraficzna;
        return this;
    }

    public KomputerBuilder withSystemOperacyjny(SystemOperacyjny systemOperacyjny) {
        this.systemOperacyjny = systemOperacyjny;
        return this;
    }

    public Komputer build() {
        Komputer komputer = new Komputer();
        komputer.setId(id);
        komputer.setKategoria(kategoria);
        komputer.setCena(cena);
        komputer.setIlosc(ilosc);
        komputer.setNazwa(nazwa);
        komputer.setTaktowanieProcesora(taktowanieProcesora);
        komputer.setIloscPamieciRAM(iloscPamieciRAM);
        komputer.setKartaGraficzna(kartaGraficzna);
        komputer.setSystemOperacyjny(systemOperacyjny);
        return komputer;
    }
}
