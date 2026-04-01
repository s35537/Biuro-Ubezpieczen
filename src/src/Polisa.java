import java.util.Objects;

public class Polisa {

    private String numerPolisy;
    private String klient;
    private double skladkaBazowa;
    private int poziomRyzyka;
    private double wartoscPojazdu;
    private boolean czyMaAlarm;
    private boolean czyBezszkodowyKlient;

    private static int liczbaUtworzonych = 0;
    private static final double OPLATA_ADMINISTRACYJNA = 450.0;

    public Polisa(String numerPolisy, String klient, double skladkaBazowa, int poziomRyzyka, double wartoscPojazdu, boolean czyMaAlarm, boolean czyBezszkodowyKlient) {
        this.numerPolisy = numerPolisy;
        this.klient = klient;
        this.skladkaBazowa = skladkaBazowa;
        this.poziomRyzyka = poziomRyzyka;
        this.wartoscPojazdu = wartoscPojazdu;
        this.czyMaAlarm = czyMaAlarm;
        this.czyBezszkodowyKlient = czyBezszkodowyKlient;
        liczbaUtworzonych++;
    }

    public String getNumerPolisy() { return numerPolisy; }
    public String getKlient() { return klient; }
    public double getSkladkaBazowa() { return skladkaBazowa; }
    public int getPoziomRyzyka() { return poziomRyzyka; }
    public double getWartoscPojazdu() { return wartoscPojazdu; }
    public boolean isCzyMaAlarm() { return czyMaAlarm; }
    public boolean isCzyBezszkodowyKlient() { return czyBezszkodowyKlient; }

    public static int pobierzLiczbeUtworzonychPolis() {
        return liczbaUtworzonych;
    }

    public double obliczSkladkeKoncowa() {
        double s = skladkaBazowa + OPLATA_ADMINISTRACYJNA;
        s += poziomRyzyka * 95;

        if (wartoscPojazdu > 70_000) s += 240;
        if (czyMaAlarm) s -= 180;
        if (czyBezszkodowyKlient) s *= 0.88;
        if (s < skladkaBazowa) s = skladkaBazowa;

        return Math.round(s * 100.0) / 100.0;
    }

    public double obliczSkladkeOdnowieniowa() {
        double biezaca = obliczSkladkeKoncowa();
        double odnow = biezaca;

        if (poziomRyzyka == 4) odnow *= 1.30;
        else if (poziomRyzyka >= 5) odnow *= 1.50;

        if (wartoscPojazdu > 70_000) odnow *= 240;
        if (czyBezszkodowyKlient) odnow *= 0.88;
        if (czyMaAlarm) odnow *= 0.92;

        double min = biezaca * 0.90;
        double max = biezaca * 1.25;
        if (odnow < min) odnow = min;
        if (odnow > max) odnow = max;

        return Math.round(odnow * 100.0) / 100.0;
    }

    public String pobierzPodsumowanieRyzyka() {
        String ocena;
        if (poziomRyzyka <= 2) ocena = "NISKIE";
        else if (poziomRyzyka <= 3) ocena = "SREDNIE";
        else ocena = "WYSOKIE";

        return "Polisa " + numerPolisy + " [" + klient + "]" + " - ryzyko: " + ocena + "(poziom" + poziomRyzyka + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Polisa other = (Polisa) obj;
        return Objects.equals(this.numerPolisy, other.numerPolisy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerPolisy);
    }

    @Override
    public String toString() {
        return "Polisa{" + "nr='" + numerPolisy + '\'' + ", klient='" + klient + '\'' + ", bazowa=" + skladkaBazowa + ", ryzyko=" + poziomRyzyka + ", pojazd=" + wartoscPojazdu + ", alarm=" + czyMaAlarm + ", bezszkodowy=" + czyBezszkodowyKlient + ", skladka=" +obliczSkladkeKoncowa() + '}';
    }
}
