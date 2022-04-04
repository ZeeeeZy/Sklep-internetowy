package com.szkola.produkty;

import com.szkola.obiekty.ProduktZEkranem;

public class Telewizor extends ProduktZEkranem {

    private boolean smartTv;

    public boolean isSmartTv() {
        return smartTv;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }

    @Override
    public String toString() {
        return "Telewizor: " +
                "smartTv: " + (smartTv ? "tak" : "nie") + " " + super.toString();
    }
}
