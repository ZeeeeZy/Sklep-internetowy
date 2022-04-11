package com.szkola.obiekty;

import com.szkola.enums.RodzajEkranu;

public class ProduktZEkranem extends Produkt {

    private Double przekatnaEkranu;

    private RodzajEkranu rodzajEkranu;

    public Double getPrzekatnaEkranu() {
        return przekatnaEkranu;
    }

    public void setPrzekatnaEkranu(Double przekatnaEkranu) {
        this.przekatnaEkranu = przekatnaEkranu;
    }

    public RodzajEkranu getRodzajEkranu() {
        return rodzajEkranu;
    }

    public void setRodzajEkranu(RodzajEkranu rodzajEkranu) {
        this.rodzajEkranu = rodzajEkranu;
    }

    @Override
    public String toString() {
        return "przekatnaEkranu: " + przekatnaEkranu +
                ", rodzajEkranu: " + rodzajEkranu + " " + super.toString();
    }
}
