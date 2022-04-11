package com.szkola.singletony;

import com.szkola.enums.Bransoleta;
import com.szkola.enums.SystemOperacyjny;

import java.util.ArrayList;
import java.util.List;

public class BransoletaSingleton {

    public List<Bransoleta> pobierzWszystkieRodzajeBransolet() {
        List<Bransoleta> bransolety = new ArrayList<>();
        bransolety.add(Bransoleta.MATERIAŁOWA);
        bransolety.add(Bransoleta.METALOWA);
        bransolety.add(Bransoleta.SKORZANA);
        return bransolety;
    }
}
