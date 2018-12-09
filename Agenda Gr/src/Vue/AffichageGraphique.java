/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.*;
import Controller.*;
import static Controller.GestionAgenda.load;
import static Controller.GestionAgenda.save;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ludo
 */
public class AffichageGraphique extends JFrame {

    private final JLabel label1 = new JLabel("Bonjour et bienvenue dans le L&P calendar");
    //private JPanel conteneur = new JPanel();
    private final JButton bouton1 = new JButton("Créer agenda");
    private final JButton bouton2 = new JButton("Ouvrir agenda");
    private final JButton bouton3 = new JButton("Annuler");
    //private JTextField texte1 = new JTextField("Nom");

    public AffichageGraphique() {
        //String nomAg = ag.getNomPersonne();
        //app.Agenda(ag);
        CardLayout cardL = new CardLayout();
        JPanel content = new JPanel();
        setTitle("L&P Calendar");
        //Modifier la taille
        setSize(600, 600);
        //Taille nom modifiable par l'utilisateur
        setResizable(false);
        //Cliquer sur crois permet la fermeture du programme
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Centrer la fentre par rapport à l'écran de  l'odinateur
        setLocationRelativeTo(null);

        //Creation du panneau bouton pane qui va contenir nos boutons ****************************************************************
        //Gestion de la creation de l'agenda ----------------------------------------------------------------------
        JPanel boutonPane1 = new JPanel();
        bouton1.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
            String nom = JOptionPane.showInputDialog(null, "Bonjour veuillez saisir le nom de votre agenda", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            File file = null;
            while ((file = new File(nom)).exists()) {
                nom = JOptionPane.showInputDialog(null, "Ce nom existe déjà, veuillez en saisir un nouveau", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "L'agenda est créé au nom de " + nom, "Identité", JOptionPane.INFORMATION_MESSAGE);
            ArrayList<RendezVous> agenda = new ArrayList<>();
            try {
                save(agenda, nom);
            } catch (IOException ex) {
                Logger.getLogger(GestionAgenda.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //Gestion de l'ouverture de l'agenda ----------------------------------------------------------------------
        JPanel boutonPane2 = new JPanel();
        bouton2.addActionListener((java.awt.event.ActionEvent e) -> {
            JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
            String nom = JOptionPane.showInputDialog(null, "Bonjour veuillez saisir le nom de votre agenda", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            File file = null;
            while (!(file = new File(nom)).exists()) {
                nom = JOptionPane.showInputDialog(null, "Cet agenda n'existe pas", "L&P calendar", JOptionPane.QUESTION_MESSAGE);
            }
            JOptionPane.showMessageDialog(
                    null, "vous avez récupérer l'agenda de " + nom, "Identité", JOptionPane.INFORMATION_MESSAGE);
            ArrayList agendaOuvert;
            try {
                agendaOuvert = load(nom);
                if (nom != null) {
                    if ((JButton) e.getSource() == bouton2) {
                        setVisible(false);
                        new AffichageMenu2(agendaOuvert,nom).setVisible(true);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(AffichageGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //Gestion de l'annulation de la page
        JPanel boutonPane3 = new JPanel();

        bouton3.addActionListener((java.awt.event.ActionEvent e) -> {
            System.exit(0);
        });
        //Ajout du bouton sur le panneau
        boutonPane1.add(bouton1);

        boutonPane2.add(bouton2);

        boutonPane3.add(bouton3);

        //4 ligne sur 1 colonnes
        GridLayout gl = new GridLayout();

        gl.setColumns(4);
        gl.setRows(2);
        setLayout(gl);

        //ajout des panneaux boutonPane et content à notre fenetre
        getContentPane()
                .add(label1);
        getContentPane()
                .add(bouton1);
        getContentPane()
                .add(bouton2);
        getContentPane()
                .add(bouton3);
        pack();
    }

}
