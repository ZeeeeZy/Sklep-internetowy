package com.szkola.singletony;

import com.szkola.enums.SystemOperacyjny;
import com.szkola.obiekty.Kategoria;

import java.util.ArrayList;
import java.util.List;

public class SystemOperacyjnySingleton {

    public List<SystemOperacyjny> pobierzWszystkieSystemyOperacyjne() {
        List<SystemOperacyjny> systemyOperacyjne = new ArrayList<>();
        systemyOperacyjne.add(SystemOperacyjny.IOS);
        systemyOperacyjne.add(SystemOperacyjny.LINUX);
        systemyOperacyjne.add(SystemOperacyjny.MACOS);
        systemyOperacyjne.add(SystemOperacyjny.ANDROID);
        return systemyOperacyjne;
    }
}
