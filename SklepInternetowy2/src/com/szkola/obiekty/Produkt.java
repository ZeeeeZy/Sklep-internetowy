package com.szkola.obiekty;

import java.math.BigDecimal;

public class Produkt implements IProdukt {

    private Long id;

    private Kategoria kategoria;

    private BigDecimal cena;

    private Integer ilosc;

    private String nazwa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public String toString() {
        return
                "id: " + id +
                ", kategoria: " + kategoria +
                ", cena: " + cena +
                ", ilosc: " + ilosc +
                ", nazwa: '" + nazwa + '\'';
    }
}
