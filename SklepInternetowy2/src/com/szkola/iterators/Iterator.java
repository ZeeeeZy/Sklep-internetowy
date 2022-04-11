package com.szkola.iterators;

import java.util.ArrayList;

public interface Iterator {
    public boolean hasNext();
    public Object next();
    public Long getLastId();
    public ArrayList znajdzPoNazwie(String nazwa);
    public Object znajdzObiektPoId(Long id);
}
