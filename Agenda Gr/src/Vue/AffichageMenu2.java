/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controller.GestionAgenda;
import Modele.RendezVous;
import java.awt.GridLayout;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ludo
 */
public class AffichageMenu2 extends JFrame {

    private final JButton bouton0 = new JButton("Afficher tous les RDV");
    private final JButton bouton1 = new JButton("Afficher tous les RDV entre 2 dates");
    private final JButton bouton2 = new JButton("Modifier un RDV");
    private final JButton bouton3 = new JButton("Ajouter un RDV");
    private final JButton bouton4 = new JButton("Supprimer un RDV");
    private final JButton bouton5 = new JButton("Supprimer tous les RDV");
    private final JButton bouton6 = new JButton("Sauvegarder & fermer l’agenda");

    public AffichageMenu2(ArrayList<RendezVous> agenda, String nom) {

        //titre de la fenetre
        setTitle("Creation de l'agenda");
        //Modifier la taille
        setSize(500, 400);
        //Taille nom modifiable par l'utilisateur
        setResizable(false);
        //Cliquer sur crois permet la fermeture du programme
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Centrer la fentre par rapport à l'écran de  l'odinateur
        setLocationRelativeTo(null);

        //Affiche tous les RDV a faire
        bouton0.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop = new JOptionPane();
            Collections.sort(agenda, RendezVous.Comparator);
        for (int i = 0; i < agenda.size(); i++) {
            JOptionPane.showMessageDialog(null,i + " - Date: " + agenda.get(i).getDate()+ "\nHeure: " + agenda.get(i).getHeureDebut() + " - " + agenda.get(i).getHeureFin()+"\nLibelle: " + agenda.get(i).getLibelle()  + "\nRappel:  " + agenda.get(i).isRappel() + "\n" + "\n", "Liste de RendezVous", JOptionPane.INFORMATION_MESSAGE);
        }
        });

        //Afficher les RDV entre 2 dates a faire 
            bouton1.addActionListener((java.awt.event.ActionEvent e) -> {
            String AfficherDateRDV = JOptionPane.showInputDialog(null, "Afficher tous les RDV entre 2 dates" + "\n" + "Première date", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            LocalDate date1 = GestionAgenda.Date(AfficherDateRDV);
            String AfficherDateRDV2 = JOptionPane.showInputDialog(null, "Afficher tous les RDV entre 2 dates" + "\n" + "Seconde date", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            LocalDate date2 = GestionAgenda.Date(AfficherDateRDV2);
            Collections.sort(agenda, RendezVous.Comparator);
            for (int i = 0; i < agenda.size(); i++) {
                if (agenda.get(i).getDate().isAfter(date1) && agenda.get(i).getDate().isBefore(date2)) {
                    JOptionPane.showMessageDialog(null, "Les RDV entre ces deux dates qui sont : " + AfficherDateRDV + "et le" + AfficherDateRDV2 + "\n" + i + " - Date: " + agenda.get(i).getDate() + "\n" + "Heure: " + agenda.get(i).getHeureDebut() + " - " + agenda.get(i).getHeureFin() + "\n" + "Libelle: " + agenda.get(i).getLibelle() + "\n" + "Rappel:  " + agenda.get(i).isRappel() + "\n" + "\n", "Agenda", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            });
        //Modifier un RDV
        bouton2.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
            String modifRDV1 = JOptionPane.showInputDialog(null, "Mettez la date du RDV que vous voulez modifier", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String modifDate = JOptionPane.showInputDialog(null, "Mettez la nouvelle date du RDV", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String modifhDebut = JOptionPane.showInputDialog(null, "Ajouter la nouvelle heure de début", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String modifhFin = JOptionPane.showInputDialog(null, "Ajouter la nouvelle heure de fin", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String modiflibelle = JOptionPane.showInputDialog(null, "Ajouter le nouveau commentaire", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Le rendez-vous est le " + modifDate + " de " + modifhDebut + " a " + modifhFin + "\n" + "Commentaire : " + modiflibelle, "Identité", JOptionPane.INFORMATION_MESSAGE);
        });

        //Ajouter un RDV
        bouton3.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop3 = new JOptionPane(), jop4 = new JOptionPane();
            String saisiDate = JOptionPane.showInputDialog(null, "Donnez une date", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String saisiHeureDebut = JOptionPane.showInputDialog(null, "Ajouter une heure de début", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String saisiHeureFin = JOptionPane.showInputDialog(null, "Ajouter une heure de fin", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String saisiLibelle = JOptionPane.showInputDialog(null, "Ajouter un commentaire", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            String saisiRappel = JOptionPane.showInputDialog(null, "Rappel ? ", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            //Creation du RDV il manque l'ajout de ce rendez vous dans l'agenda
            agenda.add(GestionAgenda.traiterChoixAjouterRDV(saisiDate, saisiHeureDebut, saisiHeureFin, saisiLibelle, saisiRappel));
            try {
                GestionAgenda.save(agenda, nom);
            } catch (IOException ex) {
                Logger.getLogger(AffichageMenu2.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Le rendez-vous est le " + saisiDate + " de " + saisiHeureDebut + " a " + saisiHeureFin + "\n" + "Commentaire : " + saisiLibelle, "Identité", JOptionPane.INFORMATION_MESSAGE);
        });

        //Supprimer un RDV
        bouton4.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop5 = new JOptionPane(), jop6 = new JOptionPane();
            String DateRDVsupprimer = JOptionPane.showInputDialog(null, "Quelle date de RDV voulez vous supprimer ?", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            JOptionPane.showMessageDialog(null, "La date" + DateRDVsupprimer + "est supprimé", "Identité", JOptionPane.INFORMATION_MESSAGE);
        });

        //Supprimer tous les RDV
        bouton5.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop7 = new JOptionPane();
            for(int i=0; i<agenda.size();i++)
            {
            GestionAgenda.traiterChoixSupprimerTousRDV(agenda,i);
            }
            JOptionPane.showMessageDialog(null,"Les RDV sont supprimés", "Suppression tous les RDV",JOptionPane.INFORMATION_MESSAGE);
        });

        //Sauvegarder et quitter
        bouton6.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop8 = new JOptionPane();
            try {
                GestionAgenda.save(agenda, nom);
            } catch (IOException ex) {
                Logger.getLogger(AffichageMenu2.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "RDV enregistré", null, JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
        //gestion ergonomie
        //4 ligne sur 1 colonnes
        GridLayout GL = new GridLayout();
        GL.setColumns(1);
        GL.setRows(7);
        setLayout(GL);
        //ajout des panneaux sur la fenetre
        getContentPane().add(bouton0);
        getContentPane().add(bouton1);
        getContentPane().add(bouton2);
        getContentPane().add(bouton3);
        getContentPane().add(bouton4);
        getContentPane().add(bouton5);
        getContentPane().add(bouton6);
    }
}
