package com.szkola;

import com.szkola.buildery.KomputerBuilder;
import com.szkola.buildery.ZegarekBuilder;
import com.szkola.enums.Bransoleta;
import com.szkola.enums.RodzajEkranu;
import com.szkola.enums.SystemOperacyjny;
import com.szkola.enums.TypZegarka;
import com.szkola.fasada.ProduktFasada;
import com.szkola.obiekty.Kategoria;
import com.szkola.obiekty.Koszyk;
import com.szkola.obiekty.Produkt;
import com.szkola.obiekty.ProduktZEkranem;
import com.szkola.produkty.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static ProduktFasada produktFasada;

    public static void main(String[] args) {
        wyswietlGlowneMenu();
        produktFasada = new ProduktFasada();

        do {
            String input = pobierzOdpowiedz();
            switch (input) {
                case "1":
                    wyswietlKategorie();
                    wyswietlGlowneMenu();
                    break;
                case "2":
                    wyswietlProdukty();
                    wyswietlGlowneMenu();
                    break;
                case "3":
                    dodajProdukt();
                    wyswietlGlowneMenu();
                    break;
                case "4":
                    szukajProduktPoNazwie();
                    wyswietlGlowneMenu();
                    break;
                case "5":
                    Koszyk koszyk = produktFasada.pobierzKoszyk();
                    if (produktFasada.koszykJestPusty()) {
                        System.out.println("Twój koszyk jest pusty.");
                    } else {
                        System.out.println(koszyk.getListaProduktow());
                    }
                    wyswietlGlowneMenu();
                    break;
                case "6":
                    System.out.println("Wpisz ID produktu który chcesz dodać do koszyka:");
                    wyswietlProdukty();
                    Long id = Long.valueOf(pobierzOdpowiedz());
                    Produkt produkt = produktFasada.znajdzProduktPoId(id);
                    if (produkt == null) {
                        System.out.println("Nieprawidłowe ID produktu");
                    } else {
                        System.out.println("Podaj ilosc: ");
                        Integer ilosc = Integer.valueOf(pobierzOdpowiedz());
                        produkt.setIlosc(ilosc);
                        produktFasada.dodajDoKoszyka(produkt);
                        System.out.println("Pomyślnie dodano produkt do koszyka: ");
                        System.out.println("Twój koszyk: ");
                        System.out.println(produktFasada.pobierzKoszyk());
                    }
                    wyswietlGlowneMenu();
                    break;
                case "7":
                    if (produktFasada.koszykJestPusty()) {
                        System.out.println("Aby złożyć zamówienie dodaj produkty do koszyka");
                    } else {
                        System.out.println("Podsumowanie zamówienia:");
                        System.out.println(produktFasada.pobierzListeProduktow());
                        BigDecimal cenaRazem = produktFasada.pobierzWartoscKoszyka();
                        System.out.println("Razem do zapłaty: " + cenaRazem + " PLN");
                        System.out.println("Czy akceptujesz zamówienie? (tak/nie)");
                        if (pobierzOdpowiedz().equalsIgnoreCase("tak")) {
                            System.out.println("Złożono zamówienie, dziękuję za zakupy!");
                        } else {
                            System.out.println("Zamówienie anulowane");
                        }
                    }
                    wyswietlGlowneMenu();
                    break;
                case "8":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór");
            }
        } while (true);
    }

    private static void szukajProduktPoNazwie() {
        System.out.println("Podaj nazwę produktu:");
        String nazwa = pobierzOdpowiedz();
        List<Produkt> produktyPoNazwie = produktFasada.znajdzProduktyPoNazwie(nazwa);
        if (produktyPoNazwie.isEmpty()) {
            System.out.println("Nie odnaleziono żadnego produktu");
            return;
        } else {
            System.out.println("Znaleziono " + produktyPoNazwie.size() + " produktów");
            System.out.println(produktyPoNazwie);
        }
    }

    private static void dodajProdukt() {
        System.out.println("Podaj Nazwę kategorii");
        wyswietlKategorie();
        Kategoria kategoria = Kategoria.valueOf(pobierzOdpowiedz());
        System.out.println("Wybrana kategoria: " + kategoria);
        switch(kategoria) {
            case KOMPUTERY:
                Produkt komputer = pobierzPodstawoweDane(kategoria);
                Komputer komp = utworzKomputer(komputer);
                produktFasada.dodajProdukt(komp);
                System.out.println("Pomyślnie dodano produkt " + komp);
                break;
            case LAPTOPY:
                Produkt lap = pobierzPodstawoweDane(kategoria);
                ProduktZEkranem produktZEkranem = pobierzDaneOdnosnieEkranu(lap);
                Laptop laptop = utworzLaptop(produktZEkranem);
                produktFasada.dodajProdukt(laptop);
                System.out.println("Pomyślnie dodano produkt " + laptop);
                break;
            case TELEFONY:
                Produkt prod = pobierzPodstawoweDane(kategoria);
                ProduktZEkranem ekranem = pobierzDaneOdnosnieEkranu(prod);
                Telefon telefon = utworzTelefon(ekranem);
                produktFasada.dodajProdukt(telefon);
                System.out.println("Pomyślnie dodano produkt " + telefon);
                break;
            case TELEWIZORY:
                Produkt tel = pobierzPodstawoweDane(kategoria);
                ProduktZEkranem ekran = pobierzDaneOdnosnieEkranu(tel);
                Telewizor telewizor = utworzTelewizor(ekran);
                produktFasada.dodajProdukt(telewizor);
                System.out.println("Pomyślnie dodano produkt " + telewizor);
                break;
            case ZEGARKI:
                Produkt zeg = pobierzPodstawoweDane(kategoria);
                Zegarek zegarek = utworzZegarek(zeg);
                produktFasada.dodajProdukt(zegarek);
                System.out.println("Pomyślnie dodano produkt " + zegarek);
                break;
            default:
                System.out.println("Podano nieprawidłowy rodzaj produktu");
                break;
        }
    }

    private static Zegarek utworzZegarek(Produkt produkt) {
        System.out.println("Podaj rodzaj bransolety: ");
        for (Bransoleta bransoleta : produktFasada.pobierzBransolety()) {
            System.out.println(bransoleta);
        }
        Bransoleta bransoleta = Bransoleta.valueOf(pobierzOdpowiedz());
        System.out.println("Podaj typ zegarka:");
        System.out.println(TypZegarka.KWARCOWY);
        System.out.println(TypZegarka.MECHANICZNY);
        System.out.println(TypZegarka.SMARTWATCH);
        TypZegarka typZegarka = TypZegarka.valueOf(pobierzOdpowiedz());
        return ZegarekBuilder.aZegarek()
                .withCena(produkt.getCena())
                .withId(produkt.getId())
                .withIlosc(produkt.getIlosc())
                .withKategoria(produkt.getKategoria())
                .withBransoleta(bransoleta)
                .withTypZegarka(typZegarka)
                .withNazwa(produkt.getNazwa())
                .build();

    }

    private static Telewizor utworzTelewizor(ProduktZEkranem produktZEkranem) {
        System.out.println("Czy telewizor ma funkcję smart tv? (tak, nie)");
        String odpowiedz = pobierzOdpowiedz();
        Telewizor telewizor = new Telewizor();
        telewizor.setSmartTv(odpowiedz.equalsIgnoreCase("tak"));
        telewizor.setCena(produktZEkranem.getCena());
        telewizor.setIlosc(produktZEkranem.getIlosc());
        telewizor.setNazwa(produktZEkranem.getNazwa());
        telewizor.setKategoria(produktZEkranem.getKategoria());
        telewizor.setId(produktZEkranem.getId());
        telewizor.setPrzekatnaEkranu(produktZEkranem.getPrzekatnaEkranu());
        telewizor.setRodzajEkranu(produktZEkranem.getRodzajEkranu());
        return telewizor;
    }

    private static Laptop utworzLaptop(ProduktZEkranem produktZEkranem) {
        SystemOperacyjny systemOperacyjny = pobierzSystemOperacyjny();
        Laptop laptop = new Laptop();
        laptop.setCena(produktZEkranem.getCena());
        laptop.setIlosc(produktZEkranem.getIlosc());
        laptop.setKategoria(produktZEkranem.getKategoria());
        laptop.setNazwa(produktZEkranem.getNazwa());
        laptop.setSystemOperacyjny(systemOperacyjny);
        laptop.setPrzekatnaEkranu(produktZEkranem.getPrzekatnaEkranu());
        laptop.setRodzajEkranu(produktZEkranem.getRodzajEkranu());
        laptop.setId(produktZEkranem.getId());
        return laptop;
    }

    private static Telefon utworzTelefon(ProduktZEkranem produkt) {
        SystemOperacyjny systemOperacyjny = pobierzSystemOperacyjny();
        Telefon telefon = new Telefon();
        telefon.setCena(produkt.getCena());
        telefon.setIlosc(produkt.getIlosc());
        telefon.setKategoria(produkt.getKategoria());
        telefon.setNazwa(produkt.getNazwa());
        telefon.setId(produkt.getId());
        telefon.setSystemOperacyjny(systemOperacyjny);
        telefon.setPrzekatnaEkranu(produkt.getPrzekatnaEkranu());
        telefon.setRodzajEkranu(produkt.getRodzajEkranu());
        telefon.setId(produkt.getId());
        return telefon;
    }

    private static ProduktZEkranem pobierzDaneOdnosnieEkranu(Produkt produkt) {
        System.out.println("Podaj przekątną ekranu:");
        Double przekatnaEkranu = Double.parseDouble(pobierzOdpowiedz());
        System.out.println("Podaj rodzaj ekranu:");
        System.out.println(RodzajEkranu.AMOLED);
        System.out.println(RodzajEkranu.LCD);
        System.out.println(RodzajEkranu.LED);
        System.out.println(RodzajEkranu.OLED);
        RodzajEkranu rodzajEkranu = RodzajEkranu.valueOf(pobierzOdpowiedz());
        ProduktZEkranem produktZEkranem = new ProduktZEkranem();
        produktZEkranem.setPrzekatnaEkranu(przekatnaEkranu);
        produktZEkranem.setCena(produkt.getCena());
        produktZEkranem.setIlosc(produkt.getIlosc());
        produktZEkranem.setKategoria(produkt.getKategoria());
        produktZEkranem.setNazwa(produkt.getNazwa());
        produktZEkranem.setId(produkt.getId());
        produktZEkranem.setRodzajEkranu(rodzajEkranu);
        return produktZEkranem;
    }

    private static Komputer utworzKomputer(Produkt produkt) {
        System.out.println("Podaj taktowanie procesora w Mhz:");
        Long taktowanieProcesora = Long.valueOf(pobierzOdpowiedz());
        System.out.println("Podaj ilość pamięci RAM w GB:");
        Long iloscPamieciRam = Long.valueOf(pobierzOdpowiedz());
        System.out.println("Podaj nazwę karty graficznej:");
        String nazwaKartyGraficznej = pobierzOdpowiedz();
        SystemOperacyjny systemOperacyjny = pobierzSystemOperacyjny();
        return KomputerBuilder.aKomputer()
                .withCena(produkt.getCena())
                .withId(produkt.getId())
                .withIlosc(produkt.getIlosc())
                .withIloscPamieciRAM(iloscPamieciRam)
                .withSystemOperacyjny(systemOperacyjny)
                .withKartaGraficzna(nazwaKartyGraficznej)
                .withKategoria(produkt.getKategoria())
                .withTaktowanieProcesora(taktowanieProcesora)
                .withNazwa(produkt.getNazwa())
                .build();
    }

    private static SystemOperacyjny pobierzSystemOperacyjny() {
        System.out.println("Podaj system operacyjny:");
        for (SystemOperacyjny systemOperacyjny : produktFasada.pobierzSystemyOperacyjne()) {
            System.out.println(systemOperacyjny);
        }
        return SystemOperacyjny.valueOf(pobierzOdpowiedz());
    }

    private static Produkt pobierzPodstawoweDane(Kategoria kategoria) {
        Produkt produkt = new Produkt();
        System.out.println("Podaj nazwę produktu");
        produkt.setNazwa(pobierzOdpowiedz());
        System.out.println("Podaj cenę produktu:");
        produkt.setCena(BigDecimal.valueOf(Long.parseLong(pobierzOdpowiedz())));
        System.out.println("Podaj stan magazynowy (ilość):");
        produkt.setIlosc(Integer.valueOf(pobierzOdpowiedz()));
        produkt.setKategoria(kategoria);
        produkt.setId(produktFasada.pobierzOstatnieId());
        return produkt;
    }

    private static void wyswietlProdukty() {
        List<Produkt> produkts = produktFasada.pobierzListeProduktow();
        if (produkts.isEmpty()) {
            System.out.println("Brak produktów. Dodaj nowe, aby wyświetlić");
        } else {
            System.out.println(produktFasada.pobierzListeProduktow());
        }
    }

    private static void wyswietlKategorie() {
        System.out.println("==========================================================");
        System.out.println("KATEGORIE:");
        for (Kategoria kategoria : produktFasada.pobierzKategorieProduktow()) {
            System.out.println(kategoria);
        }
        System.out.println("==========================================================");
    }

    private static void wyswietlGlowneMenu() {
        System.out.println("==========================================================");
        System.out.println("Witaj w sklepie!");
        System.out.println("1.Wyświetl wszystkie kategorie");
        System.out.println("2.Wyświetl wszystkie produkty");
        System.out.println("3.Dodaj produkt");
        System.out.println("4.Wyszukaj produkt");
        System.out.println("5.Wyświetl produkty w koszyku");
        System.out.println("6.Dodaj produkty do koszyka");
        System.out.println("7.Złóż zamówienie");
        System.out.println("8.Wyjdź z programu");
        System.out.println("==========================================================");
    }

    private static String pobierzOdpowiedz() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String odpowiedz = "";
        try {
            odpowiedz = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return odpowiedz;
    }
}
