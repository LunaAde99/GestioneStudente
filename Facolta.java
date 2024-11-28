import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;
public class Facolta implements Serializable {
	private static final long serialVersionUID = 1L;
    private List<Studente> studenti;
    private static final Scanner scanner = new Scanner(System.in);

    public Facolta() {
        studenti = new ArrayList<>();
    }

    public void addStudente(Studente s) {
        studenti.add(s);
    }

    public void rimuoviStudente(String nome, String cognome) {
        Studente studente = trovaStudente(nome, cognome);
        if (studente != null) {
            studenti.remove(studente);
            System.out.println("Studente rimosso con successo.");
        } else {
            System.out.println("Studente non trovato.");
        }
    }

    public void stampaRubrica() {
        if (studenti.isEmpty()) {
            System.out.println("Non ci sono studenti registrati.");
        } else {
            studenti.forEach(System.out::println);
        }
    }

    public Studente cercaStudente(String nome, String cognome) {
        return trovaStudente(nome, cognome);
    }

    private Studente trovaStudente(String nome, String cognome) {
        for (Studente studente : studenti) {
            if (studente.getNome().equalsIgnoreCase(nome) && studente.getCognome().equalsIgnoreCase(cognome)) {
                return studente;
            }
        }
        return null;
    }

    public void aggiungiEsame() {
        System.out.print("Inserisci il nome dello studente a cui aggiungere l'esame: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome dello studente: ");
        String cognome = scanner.nextLine();

        Studente studente = trovaStudente(nome, cognome);
        if (studente != null) {
            System.out.print("Inserisci il nome della materia: ");
            String materia = scanner.nextLine().trim();
            if (materia.isEmpty()) {
                System.out.println("La materia non può essere vuota. Riprova.");
                return;
            }

            System.out.print("Inserisci il tipo di esame (scritto o orale): ");
            String tipoEsameInput = scanner.nextLine().trim().toUpperCase();
            GestioneEsame.TipoEsame tipoEsame;
            if (tipoEsameInput.equals("SCRITTO")) {
                tipoEsame = GestioneEsame.TipoEsame.SCRITTO;
            } else if (tipoEsameInput.equals("ORALE")) {
                tipoEsame = GestioneEsame.TipoEsame.ORALE;
            } else {
                System.out.println("Tipo di esame non valido, riprova.");
                return;
            }

            System.out.print("L'esame è stato dato? (sì o no): ");
            String statoEsameInput = scanner.nextLine().trim().toLowerCase();
            GestioneEsame.StatoEsame statoEsame;
            if (statoEsameInput.equals("sì") || statoEsameInput.equals("si")) {
                statoEsame = GestioneEsame.StatoEsame.DATO;
            } else if (statoEsameInput.equals("no")) {
                statoEsame = GestioneEsame.StatoEsame.NONDATO;
            } else {
                System.out.println("Stato dell'esame non valido, riprova.");
                return;
            }

            int voto = -1;
            if (statoEsame == GestioneEsame.StatoEsame.DATO) {
                System.out.print("Inserisci il voto: ");
                while (true) {
                    if (scanner.hasNextInt()) {
                        voto = scanner.nextInt();
                        scanner.nextLine();
                        if (voto >= 0 && voto <= 30) {
                            break;
                        } else {
                            System.out.println("Il voto deve essere compreso tra 0 e 30. Riprova.");
                        }
                    } else {
                        System.out.println("Input non valido, inserisci un numero intero.");
                        scanner.next();
                    }
                }
            }

            GestioneEsame esame = new GestioneEsame(materia, tipoEsame, statoEsame, voto);
            studente.aggiungiEsame(esame);
            esame.stampaRisultato();
        } else {
            System.out.println("Studente non trovato.");
        }
    }

    public void modificaMatricola(String nome, String cognome, String nuovaMatricola) {
        Studente studente = trovaStudente(nome, cognome);
        if (studente != null) {
            studente.setMatricola(nuovaMatricola);
            System.out.println("Matricola modificata con successo.");
        } else {
            System.out.println("Studente non trovato.");
        }
    }

    public class GestioneEsame implements Serializable {
    	private static final long serialVersionUID = 1L;
        public enum TipoEsame { SCRITTO, ORALE }
        public enum StatoEsame { DATO, NONDATO, PROMOSSO, BOCCIATO }

        private TipoEsame tipoEsame;
        private StatoEsame statoEsame;
        private int voto;
        private String materia;

        public GestioneEsame(String materia, TipoEsame tipoEsame, StatoEsame statoEsame, int voto) {
            this.materia = materia;
            this.tipoEsame = tipoEsame;
            this.statoEsame = statoEsame;
            this.voto = voto;
        }

        public void stampaRisultato() {
            System.out.println("\n--- Dettagli dell'esame ---");
            System.out.println("Materia: " + (materia != null ? materia : "Non specificata"));
            System.out.println("Tipo di esame: " + (tipoEsame != null ? tipoEsame : "Non specificato"));
            System.out.println("Stato dell'esame: " + statoEsame);
            if (statoEsame == StatoEsame.DATO || statoEsame == StatoEsame.PROMOSSO || statoEsame == StatoEsame.BOCCIATO) {
                System.out.println("Voto: " + voto);
                System.out.println("Esito: " + (voto >= 18 ? "PROMOSSO" : "BOCCIATO"));
            }
            }
        
        }
}


