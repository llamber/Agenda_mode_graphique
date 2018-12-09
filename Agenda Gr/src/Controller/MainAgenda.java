/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Vue.*;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ludo
 */
public class MainAgenda {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args)throws IOException, ClassNotFoundException {
        AffichageGraphique maFenetre = new AffichageGraphique();
        maFenetre.setVisible(true);
    }
}

