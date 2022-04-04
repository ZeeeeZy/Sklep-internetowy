package com.szkola.buildery;

import com.szkola.enums.Bransoleta;
import com.szkola.enums.TypZegarka;
import com.szkola.obiekty.Kategoria;
import com.szkola.produkty.Zegarek;

import java.math.BigDecimal;

public final class ZegarekBuilder {
    private Long id;
    private Kategoria kategoria;
    private BigDecimal cena;
    private Integer ilosc;
    private String nazwa;
    private TypZegarka typZegarka;
    private Bransoleta bransoleta;

    private ZegarekBuilder() {
    }

    public static ZegarekBuilder aZegarek() {
        return new ZegarekBuilder();
    }

    public ZegarekBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ZegarekBuilder withKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
        return this;
    }

    public ZegarekBuilder withCena(BigDecimal cena) {
        this.cena = cena;
        return this;
    }

    public ZegarekBuilder withIlosc(Integer ilosc) {
        this.ilosc = ilosc;
        return this;
    }

    public ZegarekBuilder withNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    public ZegarekBuilder withTypZegarka(TypZegarka typZegarka) {
        this.typZegarka = typZegarka;
        return this;
    }

    public ZegarekBuilder withBransoleta(Bransoleta bransoleta) {
        this.bransoleta = bransoleta;
        return this;
    }

    public Zegarek build() {
        Zegarek zegarek = new Zegarek();
        zegarek.setId(id);
        zegarek.setKategoria(kategoria);
        zegarek.setCena(cena);
        zegarek.setIlosc(ilosc);
        zegarek.setNazwa(nazwa);
        zegarek.setTypZegarka(typZegarka);
        zegarek.setBransoleta(bransoleta);
        return zegarek;
    }
}
