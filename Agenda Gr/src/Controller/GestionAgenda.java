/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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

public class GestionAgenda implements Serializable {


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

    public static void traiterChoixSupprimerTousRDV(ArrayList agenda,int i) {
        agenda.remove(i);
    }

    public static void save(ArrayList agenda, String nomFichier) throws IOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        fos = new FileOutputStream(nomFichier);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(agenda);
        oos.flush();
        oos.close();
    }

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
        static public LocalDate Date(String saisiDate){
        LocalDate date = LocalDate.parse(saisiDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return date;
    }

}
