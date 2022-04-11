package com.szkola.produkty;

import com.szkola.enums.Bransoleta;
import com.szkola.enums.TypZegarka;
import com.szkola.obiekty.Produkt;

public class Zegarek extends Produkt {

    private TypZegarka typZegarka;

    private Bransoleta bransoleta;

    public TypZegarka getTypZegarka() {
        return typZegarka;
    }

    public void setTypZegarka(TypZegarka typZegarka) {
        this.typZegarka = typZegarka;
    }

    public Bransoleta getBransoleta() {
        return bransoleta;
    }

    public void setBransoleta(Bransoleta bransoleta) {
        this.bransoleta = bransoleta;
    }

    @Override
    public String toString() {
        return "Zegarek: " +
                "typZegarka=" + typZegarka +
                ", bransoleta=" + bransoleta + " " + super.toString() + "\n";
    }

}
