package com.szkola.singletony;

import com.szkola.enums.Bransoleta;
import com.szkola.enums.RodzajEkranu;
import com.szkola.enums.SystemOperacyjny;
import com.szkola.enums.TypZegarka;
import com.szkola.obiekty.Kategoria;
import com.szkola.obiekty.Produkt;
import com.szkola.obiekty.ProduktZEkranem;
import com.szkola.produkty.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProduktSingleton {

    private static final String FILENAME = "db.csv";
    private static final String COMMA_DELIMITER = ",";

    File dbFile = new File(FILENAME);

    {
        try {
            dbFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Produkt> getProducts() throws FileNotFoundException {
        List<Produkt> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] values = line.split(COMMA_DELIMITER);
                if (values.length > 0) {
                    Kategoria kategoria = Kategoria.valueOf(values[0]);
                    switch (kategoria) {
                        case LAPTOPY:
                            Laptop laptop = new Laptop();
                            setProduktValues(laptop, values);
                            setEkranValues(laptop, values);
                            laptop.setSystemOperacyjny(SystemOperacyjny.valueOf(values[7]));
                            records.add(laptop);
                            break;
                        case KOMPUTERY:
                            Komputer komputer = new Komputer();
                            setProduktValues(komputer, values);
                            komputer.setIloscPamieciRAM(Long.valueOf(values[5]));
                            komputer.setKartaGraficzna(values[6]);
                            komputer.setSystemOperacyjny(SystemOperacyjny.valueOf(values[7]));
                            komputer.setTaktowanieProcesora(Long.valueOf(values[8]));
                            records.add(komputer);
                            break;
                        case ZEGARKI:
                            Zegarek zegarek = new Zegarek();
                            setProduktValues(zegarek, values);
                            zegarek.setTypZegarka(TypZegarka.valueOf(values[5]));
                            zegarek.setBransoleta(Bransoleta.valueOf(values[6]));
                            records.add(zegarek);
                            break;
                        case TELEFONY:
                            Telefon telefon = new Telefon();
                            setProduktValues(telefon, values);
                            setEkranValues(telefon, values);
                            telefon.setSystemOperacyjny(SystemOperacyjny.valueOf(values[7]));
                            records.add(telefon);
                            break;
                        case TELEWIZORY:
                            Telewizor telewizor = new Telewizor();
                            setProduktValues(telewizor, values);
                            setEkranValues(telewizor, values);
                            telewizor.setSmartTv((values[7].equalsIgnoreCase("tak")));
                            records.add(telewizor);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private void setProduktValues(Produkt produkt, String[] values) {
        produkt.setKategoria(Kategoria.valueOf(values[0]));
        produkt.setId(Long.valueOf(values[1]));
        produkt.setNazwa(values[2]);
        produkt.setCena(BigDecimal.valueOf(Long.parseLong(values[3])));
        produkt.setIlosc(Integer.valueOf(values[4]));
    }

    private void setEkranValues(ProduktZEkranem produkt, String[] values) {
        produkt.setRodzajEkranu(RodzajEkranu.valueOf(values[5]));
        produkt.setPrzekatnaEkranu(Double.valueOf(values[6]));
    }


    public void dodajProdukt(Produkt produkt) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            switch (produkt.getKategoria()) {
                case TELEWIZORY:
                    assert produkt instanceof Telewizor;
                    Telewizor telewizor = (Telewizor) produkt;
                    builder.append(appendProdukt(telewizor))
                            .append(appendEkranValues(telewizor))
                            .append((telewizor.isSmartTv() ? "tak" : "nie"));
                    break;
                case TELEFONY:
                    assert produkt instanceof Telefon;
                    Telefon telefon = (Telefon) produkt;
                    builder.append(appendProdukt(telefon))
                            .append(appendEkranValues(telefon))
                            .append(telefon.getSystemOperacyjny());
                    break;
                case ZEGARKI:
                    assert produkt instanceof Zegarek;
                    Zegarek zegarek = (Zegarek) produkt;
                    builder.append(appendProdukt(zegarek))
                            .append(zegarek.getTypZegarka().name()).append(COMMA_DELIMITER)
                            .append(zegarek.getBransoleta().name());
                    break;
                case KOMPUTERY:
                    assert produkt instanceof Komputer;
                    Komputer komputer = (Komputer) produkt;
                    builder.append(appendProdukt(komputer))
                            .append(komputer.getIloscPamieciRAM()).append(COMMA_DELIMITER)
                            .append(komputer.getKartaGraficzna()).append(COMMA_DELIMITER)
                            .append(komputer.getSystemOperacyjny()).append(COMMA_DELIMITER)
                            .append(komputer.getTaktowanieProcesora());
                    break;
                case LAPTOPY:
                    assert produkt instanceof Laptop;
                    Laptop laptop = (Laptop) produkt;
                    builder.append(appendProdukt(laptop))
                        .append(appendEkranValues(laptop))
                            .append(laptop.getSystemOperacyjny());
                    break;
            }
            writeToFile(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(StringBuilder builder) throws IOException {
        System.out.println("writeToFile");
        System.out.println(builder.toString());
        FileWriter pw = new FileWriter(FILENAME);
        pw.write(builder.toString());
        pw.flush();
        pw.close();
    }

    private String appendProdukt(Produkt produkt) {
        StringBuilder builder = new StringBuilder();
        builder.append(produkt.getKategoria().name()).append(COMMA_DELIMITER)
                .append(produkt.getId()).append(COMMA_DELIMITER)
                .append(produkt.getNazwa()).append(COMMA_DELIMITER)
                .append(produkt.getCena().toString()).append(COMMA_DELIMITER)
                .append(produkt.getIlosc().toString()).append(COMMA_DELIMITER);
        return builder.toString();
    }

    private String appendEkranValues(ProduktZEkranem produktZEkranem) {
        StringBuilder builder = new StringBuilder();
        builder.append(produktZEkranem.getRodzajEkranu().name()).append(COMMA_DELIMITER)
                .append(produktZEkranem.getPrzekatnaEkranu().toString()).append(COMMA_DELIMITER);
        return builder.toString();
    }
}
