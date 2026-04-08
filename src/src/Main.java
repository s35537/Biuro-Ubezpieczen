public class Main {
    public static void main(String[] args) {

        BiuroUbezpieczen biuro = new BiuroUbezpieczen("Secure Future");

        Polisa p1 = new Polisa("CAR-1", "Kajetan Mozdzen",    900.0, 3, 72000.0, true,  true);
        Polisa p2 = new Polisa("CAR-2", "Pawel Kuc",     840.0, 4, 54000.0, false, false);
        Polisa p3 = new Polisa("CAR-3", "Kasia Dabek",  780.0, 2, 46000.0, true,  false);
        Polisa p4 = new Polisa("CAR-4", "Rafal Nowak",  1100.0, 5, 88000.0, false, false);
        Polisa p5 = new Polisa("CAR-5", "Ewa Dabrowska", 650.0, 1, 32000.0, true,  true);

        biuro.dodajPolise(p1);
        biuro.dodajPolise(p2);
        biuro.dodajPolise(p3);
        biuro.dodajPolise(p4);
        biuro.dodajPolise(p5);

        System.out.println("=== STATYSTYKI STATYCZNE ===");
        System.out.println("Lacznie utworzonych polis (static): "
                + Polisa.pobierzLiczbeUtworzonychPolis());
        Polisa testowa = new Polisa("CAR-999", "Test Test", 500.0, 2, 20000.0, false, false);
        System.out.println("Po utworzeniu polisy testowej (poza biurem): "
                + Polisa.pobierzLiczbeUtworzonychPolis());
        System.out.println();

        biuro.wypiszRaport();
        System.out.println();

        System.out.println("=== SZCZEGOLY OBLICZEN ===");
        Polisa[] wszystkie = {p1, p2, p3, p4, p5};
        for (Polisa p : wszystkie) {
            System.out.println("-- " + p.getNumerPolisy() + " (" + p.getKlient() + ") --");
            System.out.println("   Skladka koncowa:      " + p.obliczSkladkeKoncowa() + " zl");
            System.out.println("   Skladka odnowieniowa: " + p.obliczSkladkeOdnowieniowa() + " zl");
            System.out.println("   " + p.pobierzPodsumowanieRyzyka());
        }
        System.out.println();

        System.out.println("=== STATYSTYKI PORTFELA ===");
        System.out.println("Laczna skladka koncowa:      " + biuro.policzLacznaSkladke() + " zl");
        System.out.println("Laczna prognoza odnowien:    " + biuro.policzLacznaPrognozeOdnowien() + " zl");
        System.out.println("Liczba polis wys. ryzyka:    " + biuro.policzPolisyWysokiegoRyzyka());
        System.out.println();

        System.out.println("=== POLISY TANSZE NIZ 1500 ZL ===");
        biuro.wypiszTanszeNiz(1500.0);
        System.out.println();

        System.out.println("=== toString() ===");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p4);
        System.out.println();

        System.out.println("=== equals() ===");
        Polisa duplikat = new Polisa("CAR-1", "Zupelnie Inny", 200.0, 1, 10000.0, false, false);
        System.out.println("p1 vs duplikat (ten sam numer, inne dane): " + p1.equals(duplikat));
        System.out.println("p1 vs p2 (rozne numery):                   " + p1.equals(p2));
        System.out.println("p3 vs p3 (ten sam obiekt):                 " + p3.equals(p3));
        System.out.println();

        System.out.println("=== znajdzPoNumerze() ===");
        Polisa znaleziona = biuro.znajdzPoNumerze("CAR-3");
        System.out.println("Szukam CAR-3: " + znaleziona);

        Polisa nieznaleziona = biuro.znajdzPoNumerze("CAR-999");
        System.out.println("Szukam CAR-999 (nie ma w biurze): " + nieznaleziona);
        System.out.println();

        System.out.println("=== WPLYW LOGIKI BIZNESOWEJ (baza vs koncowa) ===");
        for (Polisa p : wszystkie) {
            double roznica = p.obliczSkladkeKoncowa() - p.getSkladkaBazowa();
            System.out.printf("%-10s | baza: %7.2f | koncowa: %7.2f | roznica: %+.2f zl%n",
                    p.getNumerPolisy(), p.getSkladkaBazowa(),
                    p.obliczSkladkeKoncowa(), roznica);
        }
    }
}