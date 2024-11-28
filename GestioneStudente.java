import java.io.*;
import java.util.List;
import java.util.Scanner;

public class GestioneStudente {

    private static Facolta facolta = new Facolta();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "Facolta.dat";

    public static void main(String[] args) {
    	Studente s1 = new Studente("Mario", "Rossi", "3382323");
        Studente s2 = new Studente("Luigi", "Bianchi", "3382344");
        Studente s3 = new Studente("Lucia", "Gialli", "3382300");

        facolta.addStudente(s1);
        facolta.addStudente(s2);
        facolta.addStudente(s3);
        caricaFacolta();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu Gestione Studenti ---");
            System.out.println("1. Aggiungere un nuovo studente");
            System.out.println("2. Aggiungere un esame a uno studente");
            System.out.println("3. Rimuovere uno studente");
            System.out.println("4. Cercare uno studente");
            System.out.println("5. Stampare tutti gli studenti");
            System.out.println("6. Modificare la matricola di uno studente");
            System.out.println("7. Salvare i dati");
            System.out.println("8. Caricare i dati");
            System.out.println("0. Uscire");
            System.out.print("Scegli un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1:
                    aggiungiStudente();
                    break;
                case 2:
                    aggiungiEsame();
                    break;
                case 3:
                    rimuoviStudente();
                    break;
                case 4:
                    cercaStudente();
                    break;
                case 5:
                    facolta.stampaRubrica();
                    break;
                case 6:
                    modificaMatricola();
                    break;
                case 7:
                    salvaFacolta();
                    break;
                case 8:
                    caricaFacolta();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opzione non valida.");
            }
        }
        System.out.println("Uscita dal programma.");
    }

    private static void aggiungiStudente() {
        System.out.print("Inserisci il nome dello studente: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome dello studente: ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci la matricola dello studente: ");
        String matricola = scanner.nextLine();

        if (facolta.cercaStudente(nome, cognome) != null) {
            System.out.println("Studente già esistente!");
        } else {
            Studente studente = new Studente(nome, cognome, matricola);
            facolta.addStudente(studente);
            System.out.println("Studente aggiunto con successo.");
        }
    }

    private static void aggiungiEsame() {
        facolta.aggiungiEsame();
    }

    private static void rimuoviStudente() {
        System.out.print("Inserisci il nome dello studente: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome dello studente: ");
        String cognome = scanner.nextLine();

        Studente studente = facolta.cercaStudente(nome, cognome);
        if (studente != null) {
            facolta.rimuoviStudente(nome, cognome);
        } else {
            System.out.println("Studente non trovato.");
        }
    }

    private static void cercaStudente() {
        System.out.print("Inserisci il nome dello studente: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome dello studente: ");
        String cognome = scanner.nextLine();

        Studente studente = facolta.cercaStudente(nome, cognome);
        if (studente != null) {
            System.out.println(studente);
            studente.getEsami().forEach(Facolta.GestioneEsame::stampaRisultato);
        } else {
            System.out.println("Studente non trovato.");
        }
    }

    private static void modificaMatricola() {
        System.out.print("Inserisci il nome dello studente: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome dello studente: ");
        String cognome = scanner.nextLine();

        Studente studente = facolta.cercaStudente(nome, cognome);
        if (studente != null) {
            System.out.print("Inserisci la nuova matricola: ");
            String nuovaMatricola = scanner.nextLine();
            facolta.modificaMatricola(nome, cognome, nuovaMatricola);
        } else {
            System.out.println("Studente non trovato.");
        }
    }

    private static void salvaFacolta() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(facolta);
            System.out.println("Dati salvati con successo su " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio dei dati.");
            e.printStackTrace();
        }
    }

    private static void caricaFacolta() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            facolta = (Facolta) in.readObject();
            System.out.println("Dati caricati con successo da " + FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("Nessun file trovato. Partendo da una nuova Facolta.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore durante il caricamento dei dati.");
            e.printStackTrace();
        }
    }
}

