package com.szkola;

import com.szkola.buildery.KomputerBuilder;
import com.szkola.buildery.ZegarekBuilder;
import com.szkola.enums.Bransoleta;
import com.szkola.enums.RodzajEkranu;
import com.szkola.enums.SystemOperacyjny;
import com.szkola.enums.TypZegarka;
import com.szkola.fasada.KategoriaFasada;
import com.szkola.obiekty.Kategoria;
import com.szkola.obiekty.Koszyk;
import com.szkola.obiekty.Produkt;
import com.szkola.obiekty.ProduktZEkranem;
import com.szkola.produkty.*;
import com.szkola.repositories.KoszykRepository;
import com.szkola.repositories.ProduktRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static KategoriaFasada kategoriaFasada;

    private static ProduktRepository produktRepository;

    private static KoszykRepository koszykRepository;

    public static void main(String[] args) {
        wyswietlGlowneMenu();
        kategoriaFasada = new KategoriaFasada();
        produktRepository = new ProduktRepository();
        koszykRepository = new KoszykRepository();
        do {
            String input = pobierzOdpowiedz();
            switch (input) {
                case "1":
                    wyswietlKategorie();
                    wyswietlGlowneMenu();
                    break;
                case "2":
                    dodajKategorie();
                    wyswietlGlowneMenu();
                    break;
                case "3":
                    usunKategorie();
                    wyswietlKategorie();
                    wyswietlGlowneMenu();
                    break;
                case "4":
                    wyswietlProdukty();
                    wyswietlGlowneMenu();
                    break;
                case "5":
                    dodajProdukt();
                    wyswietlGlowneMenu();
                    break;
                case "6":
                    szukajProduktPoNazwie();
                    wyswietlGlowneMenu();
                    break;
                case "7":
                    Koszyk koszyk = koszykRepository.getKoszyk();
                    if (koszyk.getListaProduktow().isEmpty()) {
                        System.out.println("Tw??j koszyk jest pusty.");
                    } else {
                        System.out.println(koszyk.getListaProduktow());
                    }
                    wyswietlGlowneMenu();
                case "8":
                    System.out.println("Wpisz ID produktu kt??ry chcesz doda?? do koszyka:");
                    wyswietlProdukty();
                    Long id = Long.valueOf(pobierzOdpowiedz());
                    Produkt produkt = produktRepository.znajdzProduktPoId(id);
                    if (produkt == null) {
                        System.out.println("Nieprawid??owe ID produktu");
                    } else {
                        System.out.println("Podaj ilosc: ");
                        Integer ilosc = Integer.valueOf(pobierzOdpowiedz());
                        produkt.setIlosc(ilosc);
                        koszykRepository.dodajDoKoszyka(produkt);
                        System.out.println("Pomy??lnie dodano produkt do koszyka: ");
                        System.out.println("Tw??j koszyk: ");
                        System.out.println(koszykRepository.getKoszyk());
                    }
                    wyswietlGlowneMenu();
                    break;
                case "9":
                    if (koszykRepository.getKoszyk().getListaProduktow().isEmpty()) {
                        System.out.println("Aby z??o??y?? zam??wienie dodaj produkty do koszyka");
                    } else {
                        System.out.println("Podsumowanie zam??wienia:");
                        System.out.println(koszykRepository.getKoszyk().getListaProduktow());
                        BigDecimal cenaRazem = koszykRepository.getKoszyk().getListaProduktow().stream().map(Produkt::getCena)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                        System.out.println("Razem do zap??aty: " + cenaRazem + " PLN");
                        System.out.println("Czy akceptujesz zam??wienie? (tak/nie)");
                        if (pobierzOdpowiedz().equalsIgnoreCase("tak")) {
                            System.out.println("Z??o??ono zam??wienie, dzi??kuj?? za zakupy!");
                        } else {
                            System.out.println("Zam??wienie anulowane");
                        }
                    }
                    wyswietlGlowneMenu();
                    break;
                case "10":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieprawid??owy wyb??r");
            }
        } while (true);
    }

    private static void szukajProduktPoNazwie() {
        System.out.println("Podaj nazw?? produktu:");
        String nazwa = pobierzOdpowiedz();
        List<Produkt> produktyPoNazwie = produktRepository.znajdzProduktPoNazwie(nazwa);
        if (produktyPoNazwie.isEmpty()) {
            System.out.println("Nie odnaleziono ??adnego produktu");
            return;
        } else {
            System.out.println("Znaleziono " + produktyPoNazwie.size() + " produkt??w");
            System.out.println(produktyPoNazwie);
        }
    }

    private static void dodajProdukt() {
        if (kategoriaFasada.pobierzKategorie().isEmpty()) {
            System.out.println("Dodaj najpierw przynajmniej jedn?? kategori??");
            wyswietlGlowneMenu();
            return;
        }
        System.out.println("Podaj ID kategorii");
        wyswietlKategorie();
        Long idKategorii = Long.valueOf(pobierzOdpowiedz());
        Kategoria kategoria = kategoriaFasada.pobierzKategoriePoId(idKategorii);
        if (kategoria == null) {
            System.out.println("Nieprawid??owe ID kategorii");
            wyswietlGlowneMenu();
            return;
        }
        System.out.println("Wybrana kategoria: " + kategoria.getNazwaKategorii());
        System.out.println("Podaj rodzaj produktu:");
        System.out.println("1.Komputer");
        System.out.println("2.Laptop");
        System.out.println("3.Telefon");
        System.out.println("4.Telewizor");
        System.out.println("5.Zegarek");
        String rodzaj = pobierzOdpowiedz();
        switch(rodzaj) {
            case "1":
                Produkt komputer = pobierzPodstawoweDane(kategoria);
                Komputer komp = utworzKomputer(komputer);
                produktRepository.dodajProdukt(komp);
                System.out.println("Pomy??lnie dodano produkt " + komp);
                break;
            case "2":
                Produkt lap = pobierzPodstawoweDane(kategoria);
                ProduktZEkranem produktZEkranem = pobierzDaneOdnosnieEkranu(lap);
                Laptop laptop = utworzLaptop(produktZEkranem);
                produktRepository.dodajProdukt(laptop);
                System.out.println("Pomy??lnie dodano produkt " + laptop);
                break;
            case "3":
                Produkt prod = pobierzPodstawoweDane(kategoria);
                ProduktZEkranem ekranem = pobierzDaneOdnosnieEkranu(prod);
                Telefon telefon = utworzTelefon(ekranem);
                produktRepository.dodajProdukt(telefon);
                System.out.println("Pomy??lnie dodano produkt " + telefon);
                break;
            case "4":
                Produkt tel = pobierzPodstawoweDane(kategoria);
                ProduktZEkranem ekran = pobierzDaneOdnosnieEkranu(tel);
                Telewizor telewizor = utworzTelewizor(ekran);
                produktRepository.dodajProdukt(telewizor);
                System.out.println("Pomy??lnie dodano produkt " + telewizor);
                break;
            case "5":
                Produkt zeg = pobierzPodstawoweDane(kategoria);
                Zegarek zegarek = utworzZegarek(zeg);
                produktRepository.dodajProdukt(zegarek);
                System.out.println("Pomy??lnie dodano produkt " + zegarek);
                break;
            default:
                System.out.println("Podano nieprawid??owy rodzaj produktu");
                break;
        }
    }

    private static Zegarek utworzZegarek(Produkt produkt) {
        System.out.println("Podaj rodzaj bransolety: ");
        System.out.println(Bransoleta.METALOWA);
        System.out.println(Bransoleta.MATERIA??OWA);
        System.out.println(Bransoleta.SKORZANA);
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
        System.out.println("Czy telewizor ma funkcj?? smart tv? (tak, nie)");
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
        System.out.println("Podaj przek??tn?? ekranu:");
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
        System.out.println("Podaj ilo???? pami??ci RAM w GB:");
        Long iloscPamieciRam = Long.valueOf(pobierzOdpowiedz());
        System.out.println("Podaj nazw?? karty graficznej:");
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
        System.out.println(SystemOperacyjny.LINUX.name());
        System.out.println(SystemOperacyjny.ANDROID.name());
        System.out.println(SystemOperacyjny.IOS.name());
        System.out.println(SystemOperacyjny.MACOS);
        System.out.println(SystemOperacyjny.WINDOWS);
        return SystemOperacyjny.valueOf(pobierzOdpowiedz());
    }

    private static Produkt pobierzPodstawoweDane(Kategoria kategoria) {
        Produkt produkt = new Produkt();
        System.out.println("Podaj nazw?? produktu");
        produkt.setNazwa(pobierzOdpowiedz());
        System.out.println("Podaj cen?? produktu:");
        produkt.setCena(BigDecimal.valueOf(Long.parseLong(pobierzOdpowiedz())));
        System.out.println("Podaj stan magazynowy (ilo????):");
        produkt.setIlosc(Integer.valueOf(pobierzOdpowiedz()));
        produkt.setKategoria(kategoria);
        produkt.setId(produktRepository.getLastId());
        return produkt;
    }

    private static void dodajKategorie() {
        System.out.println("Podaj nazw?? Kategorii");
        String nazwaKategorii = pobierzOdpowiedz();
        kategoriaFasada.dodajKategorie(new Kategoria(nazwaKategorii));
        System.out.println("Poprawnie dodano kategori??: " + nazwaKategorii);
    }

    private static void usunKategorie() {
        List<Kategoria> kategorieLista = kategoriaFasada.pobierzKategorie();
        if (kategorieLista.isEmpty()) {
            System.out.println("Brak istniej??cych kategorii. Aby usun???? kategori?? musisz doda?? przynajmniej jedn??");
        } else {
            System.out.println("Kategorie:");
            System.out.println(kategorieLista);
            System.out.println("Podaj ID kategorii kt??r?? chcesz usun????");
            Long idKategorii = Long.valueOf(pobierzOdpowiedz());
            Kategoria kat = kategoriaFasada.pobierzKategoriePoId(idKategorii);
            if (kat == null) {
                System.out.println("Nieprawid??owy numer kategorii");
            } else {
                kategoriaFasada.usunKategoriePoId(idKategorii);
                System.out.println("Poprawnie usunieto kategori??: " + kat);
            }
        }
    }

    private static void wyswietlProdukty() {
        List<Produkt> produkts = produktRepository.getProduktList();
        if (produkts.isEmpty()) {
            System.out.println("Brak produkt??w. Dodaj nowe, aby wy??wietli??");
        } else {
            System.out.println(produktRepository.getProduktList());
        }
    }

    private static void wyswietlKategorie() {
        List<Kategoria> kategorie = kategoriaFasada.pobierzKategorie();
        if (kategorie.isEmpty()) {
            System.out.println("Brak kategorii, dodaj now??");
        } else {
            System.out.println("Kategorie:");
            System.out.println(kategorie);
        }
    }

    private static void wyswietlGlowneMenu() {
        System.out.println("==========================================================");
        System.out.println("Witaj w sklepie!");
        System.out.println("1.Wy??wietl wszystkie kategorie");
        System.out.println("2.Dodaj kategori??");
        System.out.println("3.Usu?? kategori??");
        System.out.println("4.Wy??wietl wszystkie produkty");
        System.out.println("5.Dodaj produkt");
        System.out.println("6.Wyszukaj produkt");
        System.out.println("7.Wy??wietl produkty w koszyku");
        System.out.println("8.Dodaj produkty do koszyka");
        System.out.println("9.Z?????? zam??wienie");
        System.out.println("10.Wyjd?? z programu");
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
