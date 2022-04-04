package com.szkola.produkty;

import com.szkola.enums.SystemOperacyjny;
import com.szkola.obiekty.Produkt;

public class Komputer extends Produkt {

    private Long taktowanieProcesora;

    private Long iloscPamieciRAM;

    private String kartaGraficzna;

    private SystemOperacyjny systemOperacyjny;

    public Long getTaktowanieProcesora() {
        return taktowanieProcesora;
    }

    public void setTaktowanieProcesora(Long taktowanieProcesora) {
        this.taktowanieProcesora = taktowanieProcesora;
    }

    public Long getIloscPamieciRAM() {
        return iloscPamieciRAM;
    }

    public void setIloscPamieciRAM(Long iloscPamieciRAM) {
        this.iloscPamieciRAM = iloscPamieciRAM;
    }

    public String getKartaGraficzna() {
        return kartaGraficzna;
    }

    public void setKartaGraficzna(String kartaGraficzna) {
        this.kartaGraficzna = kartaGraficzna;
    }

    public SystemOperacyjny getSystemOperacyjny() {
        return systemOperacyjny;
    }

    public void setSystemOperacyjny(SystemOperacyjny systemOperacyjny) {
        this.systemOperacyjny = systemOperacyjny;
    }

    @Override
    public String toString() {
        return "Komputer: " +
                "taktowanieProcesora:" + taktowanieProcesora +
                ", iloscPamieciRAM:" + iloscPamieciRAM +
                ", kartaGraficzna:'" + kartaGraficzna + '\'' +
                ", systemOperacyjny:" + systemOperacyjny + " " + super.toString();
    }
}
