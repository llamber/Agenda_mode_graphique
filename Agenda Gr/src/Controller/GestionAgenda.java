/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Vue.*;
import Modele.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ludo
 */
public class GestionAgenda implements Serializable {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     * @param choix
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     */
    /**
     * @param agenda
     */
    public static void traiterChoixAfficherRDV_Entre2Dates(ArrayList<RendezVous> agenda) {
        AffichageConsole.afficherSaisiDate();
        LocalDate date1 = Date();
        AffichageConsole.afficherSaisiDate();
        LocalDate date2 = Date();
        for (int i = 0; i < agenda.size(); i++) {
            if (agenda.get(i).getDate().isAfter(date1) && agenda.get(i).getDate().isBefore(date2)) {
                AffichageConsole.afficherRDV_Entre2Dates(agenda, i);
            }
        }
    }

    /**
     * @param agenda
     */
    public static void traiterChoixAjouterRDV(ArrayList<RendezVous> agenda) {
        LocalDate date = Date();
        LocalTime hDebut = heureDebut();
        LocalTime hFin;
        do {
            hFin = heureFin();
        } while (hDebut.isBefore(hFin));
        boolean rappel = rappel();
        String libelle = libelle();
        RendezVous rdv = new RendezVous(date, hDebut, hFin, rappel, libelle);
        agenda.add(rdv);
    }

    /**
     * @param agenda
     */
    public static void traiterChoixModifierRDV(ArrayList<RendezVous> agenda) {
        AffichageConsole.afficherRdv(agenda);
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        LocalDate date = Date();
        LocalTime hDebut = heureDebut();
        LocalTime hFin;
        do {
            hFin = heureFin();
        } while (hDebut.isBefore(hFin));
        boolean rappel = rappel();
        String libelle = libelle();
        RendezVous rdv = new RendezVous(date, hDebut, hFin, rappel, libelle);
        agenda.set(index, rdv);
    }

    public static RendezVous traiterChoixAjouterRDV(String saisiDate, String saisiHeureDebut, String saisiHeureFin, String saisiLibelle, String saisiRappel) {
        LocalDate date = LocalDate.parse(saisiDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime heureDebut = LocalTime.parse(saisiHeureDebut, DateTimeFormatter.ofPattern("kk:mm")); //<-- kk pour une date de 0 Ã  24h
        LocalTime heureFin = LocalTime.parse(saisiHeureFin, DateTimeFormatter.ofPattern("kk:mm"));
        boolean rappel;
        do {
            saisiRappel = "N";
        } while (!("O".equals(saisiRappel) || "N".equals(saisiRappel)));
        rappel = "O".equals(saisiRappel);
        RendezVous rdv = new RendezVous(date, heureDebut, heureFin, rappel, saisiLibelle);
        return rdv;
    }

    /**
     * @param agenda
     */
    public static void traiterChoixSupprimerRDV(ArrayList<RendezVous> agenda) {
        AffichageConsole.afficherRdv(agenda);
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        agenda.remove(index);

    }

    /**
     * @param agenda
     */
    public static void traiterChoixSupprimerTousRDV(ArrayList<RendezVous> agenda) {
        for (int i = 0; i < agenda.size(); i++) {
            agenda.remove(i);
        }
    }

    /**
     * @return
     */
    static public LocalDate Date() {
        Scanner sc = new Scanner(System.in);
        AffichageConsole.afficherSaisiDate();
        String saisiDate = sc.nextLine();
        LocalDate date = LocalDate.parse(saisiDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return date;
    }

    /**
     * @return
     */
    public static LocalTime heureDebut() {
        Scanner sc = new Scanner(System.in);
        AffichageConsole.afficherSaisiHeureDebut();
        String saisiHeureDebut = sc.nextLine();
        LocalTime heureDebut = LocalTime.parse(saisiHeureDebut, DateTimeFormatter.ofPattern("kk:mm")); //<-- kk pour une date de 0 Ã  24h
        return heureDebut;
    }

    /**
     * @return
     */
    public static LocalTime heureFin() {
        LocalTime heureFin;
        Scanner sc = new Scanner(System.in);
        AffichageConsole.afficherSaisiHeureFin();
        String saisiHeureFin = sc.nextLine();
        heureFin = LocalTime.parse(saisiHeureFin, DateTimeFormatter.ofPattern("kk:mm"));
        return heureFin;
    }

    /**
     * @return
     */
    public static String libelle() {
        Scanner sc = new Scanner(System.in);
        AffichageConsole.afficherSaisiLibelle();
        String libelle = sc.nextLine();
        return libelle;
    }

    /**
     * @return
     */
    public static boolean rappel() {
        Scanner sc = new Scanner(System.in);

        String saisiRappel;
        boolean rappel;
        do {
            AffichageConsole.afficherSaisiRappel();
            saisiRappel = sc.nextLine();
        } while (!("O".equals(saisiRappel) || "N".equals(saisiRappel)));
        rappel = "O".equals(saisiRappel);
        if (rappel) {
            AffichageConsole.afficherRappelOui();
        } else {
            AffichageConsole.afficherRappelNon();
        }
        return rappel;
    }

    /**
     * @param agenda
     * @param nomFichier
     * @throws IOException
     */
    public static void save(ArrayList agenda, String nomFichier) throws IOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        fos = new FileOutputStream(nomFichier);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(agenda);
        oos.flush();
        oos.close();
    }

    /**
     * @param nomAgenda
     * @return
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static ArrayList load(String nomAgenda) throws IOException, ClassNotFoundException {
        FileInputStream fis;
        ObjectInputStream ois;
        fis = new FileInputStream(nomAgenda);
        ois = new ObjectInputStream(fis);
        ArrayList ag;
        ag = (ArrayList) ois.readObject();
        ois.close();
        return ag;
    }

}
