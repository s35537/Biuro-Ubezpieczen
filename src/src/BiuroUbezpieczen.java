import java.util.ArrayList;

public class BiuroUbezpieczen {

    private String nazwa;
    private ArrayList<Polisa> polisy;

    public BiuroUbezpieczen(String nazwa) {
        this.nazwa  = nazwa;
        this.polisy = new ArrayList<>();
    }

    public void dodajPolise(Polisa polisa) {
        polisy.add(polisa);
    }

    public double policzLacznaSkladke() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeKoncowa();
        }
        return Math.round(suma * 100.0) / 100.0;
    }

    public double policzLacznaPrognozeOdnowien() {
        double suma = 0;
        for (Polisa p : polisy) {
            suma += p.obliczSkladkeOdnowieniowa();
        }
        return Math.round(suma * 100.0) / 100.0;
    }

    public int policzPolisyWysokiegoRyzyka() {
        int licznik = 0;
        for (Polisa p : polisy) {
            if (p.getPoziomRyzyka() >= 4) licznik++;
        }
        return licznik;
    }

    public Polisa znajdzPoNumerze(String numerPolisy) {
        for (Polisa p : polisy) {
            if (p.getNumerPolisy().equals(numerPolisy)) return p;
        }
        return null;
    }

    public void wypiszTanszeNiz(double prog) {
        System.out.println("Polisy z skladka koncowa < " + prog + " zl:");
        for (Polisa p : polisy) {
            if (p.obliczSkladkeKoncowa() < prog) {
                System.out.println("  " + p);
            }
        }
    }

    public void wypiszRaport() {
        System.out.println("============================================");
        System.out.println("  RAPORT BIURA: " + nazwa);
        System.out.println("============================================");
        System.out.printf("%-10s %-18s %10s %7s %12s%n",
                "Numer", "Klient", "Skladka", "Ryzyko", "Odnowienie");
        System.out.println("--------------------------------------------");
        for (Polisa p : polisy) {
            System.out.printf("%-10s %-18s %10.2f %7d %12.2f%n",
                    p.getNumerPolisy(),
                    p.getKlient(),
                    p.obliczSkladkeKoncowa(),
                    p.getPoziomRyzyka(),
                    p.obliczSkladkeOdnowieniowa());
        }
        System.out.println("============================================");
        System.out.println("Liczba polis: " + polisy.size());
        System.out.println("Laczna skladka:     " + policzLacznaSkladke() + " zl");
        System.out.println("Laczna prognoza:    " + policzLacznaPrognozeOdnowien() + " zl");
        System.out.println("Polisy wys. ryzyka: " + policzPolisyWysokiegoRyzyka());
        System.out.println("============================================");
    }
}