package cz.czechitas.webapp;

public class Kontakt {

    private Long cislo;
    private String jmeno;
    private String telefon;
    private String email;

    public Kontakt(Long cislo, String jmeno, String telefon, String email) {
        this.cislo = cislo;
        this.jmeno = jmeno;
        this.telefon = telefon;
        this.email = email;
    }

    public Long getCislo() {
        return cislo;
    }

    public void setCislo(Long newValue) {
        cislo = newValue;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String newValue) {
        jmeno = newValue;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String newValue) {
        telefon = newValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }

    @Override
    public String toString() {
        return "Kontakt " +
                "cislo=" + cislo + ", " +
                "jmeno=\"" + jmeno + "\"" + ", " +
                "telefon=\"" + telefon + "\"" + ", " +
                "email=\"" + email + "\"";
    }

}
