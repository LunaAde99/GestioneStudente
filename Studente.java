import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Studente implements Serializable {
    private String nome;
    private String cognome;
    private String matricola;
    private List<Facolta.GestioneEsame> esami;

    public Studente(String nome, String cognome, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.esami = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public List<Facolta.GestioneEsame> getEsami() {
        return esami;
    }

    public void aggiungiEsame(Facolta.GestioneEsame esame) {
        this.esami.add(esame);
    }

    @Override
    public String toString() {
        return "Studente [nome=" + nome + ", cognome=" + cognome + ", matricola=" + matricola + "]";
    }
}

