package com.szkola.produkty;

import com.szkola.enums.SystemOperacyjny;
import com.szkola.obiekty.ProduktZEkranem;

public class Telefon extends ProduktZEkranem {

    private SystemOperacyjny systemOperacyjny;

    public SystemOperacyjny getSystemOperacyjny() {
        return systemOperacyjny;
    }

    public void setSystemOperacyjny(SystemOperacyjny systemOperacyjny) {
        this.systemOperacyjny = systemOperacyjny;
    }

    @Override
    public String toString() {
        return "Telefon: " +
                "systemOperacyjny: " + systemOperacyjny + " " + super.toString() + "\n";
    }


}
