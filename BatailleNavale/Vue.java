package BatailleNavale;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;


public class Vue extends JFrame {

    String path = "textures/"; // Ecrire le bon chemin qui mène au dossier des icones


    private final Model model;

    JButton[][] boutons;
    JLabel lab;
    JButton tourFini;

    // BACKGROUNDS
    ImageIcon titleScreen;
    ImageIcon gameBackground;
    ImageIcon rules;

    ImageIcon blueTransition;
    ImageIcon redTransition;

    // BOUTONS
    ImageIcon buttonback;
    ImageIcon buttonplay;
    ImageIcon buttonquit;
    ImageIcon buttonrules;


    // CASES
    ImageIcon BOOM;
    ImageIcon inconnu;
    ImageIcon naviredetruit;
    ImageIcon vide;
    ImageIcon navirecoule;


    public Vue(Model model) {

        this.model = model;
        initAttribut();
        refresh();

        setTitle("Bataille navale");
        setLocation(200, 200);
        setSize(845, 640);
        setResizable(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initAttribut() {

        titleScreen = new ImageIcon(path + "backgrounds/titlescreenFinal.gif");
        gameBackground = new ImageIcon(path + "backgrounds/gamebackground.png");
        rules = new ImageIcon(path + "backgrounds/rules.gif");

        blueTransition = new ImageIcon(path + "background/bluetransition");
        redTransition = new ImageIcon(path + "background/redtransition");

        buttonback = new ImageIcon(path + "boutons/buttonback.gif");
        buttonplay = new ImageIcon(path + "boutons/buttonplay.gif");
        buttonquit = new ImageIcon(path + "boutons/buttonquit.gif");
        buttonrules = new ImageIcon(path + "boutons/buttonrules.gif");

        BOOM = new ImageIcon(path + "cases/BOOM.gif");
        inconnu = new ImageIcon(path + "cases/inconnu.gif");
        naviredetruit = new ImageIcon(path + "cases/naviredétruit.gif");
        navirecoule = new ImageIcon(path + "cases/navirecoulé.gif");
        vide = new ImageIcon(path + "cases/vide.gif");

        //***********************************************************

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));



        JPanel plateau = new JPanel(new GridLayout(10, 10));











        boutons = new JButton[10][10];
        Border borders = BorderFactory.createEmptyBorder();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boutons[i][j] = new JButton("");
                boutons[i][j].setPreferredSize(new Dimension(50, 50));
                boutons[i][j].setBorder(borders);
                plateau.add(boutons[i][j]);
            }
        }

        tourFini = new JButton("Fin de tour");

        mainPanel.add(tourFini);
        mainPanel.add(plateau);




        setContentPane(mainPanel);


    }

    public void refresh() {
        Coordonnee[] tab;
        Bateau[] tabBateaux;
        int indice;

        if (model.getJoueurEnCours() == Model.JOUEUR_1) {
            tab = model.getTir1();
            tabBateaux = model.getJoueur2();
            indice = model.getIndiceDernierCoupJoueur1();
        } else {
            tab = model.getTir2();
            tabBateaux = model.getJoueur1();
            indice = model.getIndiceDernierCoupJoueur2();
        }


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boutons[i][j].setIcon(inconnu);
            }
        }
        for (int k = 0; k < indice; k++) {
            if (model.isBateauAt(tab[k])) {
                boutons[tab[k].getLigne()][tab[k].getColone()].setIcon(naviredetruit);
            } else {
                boutons[tab[k].getLigne()][tab[k].getColone()].setIcon(vide);
            }
        }
    }


    void dialog(String message, String titre, int option) {
        JOptionPane d = new JOptionPane();
        d.showMessageDialog(this, message, titre, option); //JOPtionPane.
    }


    public void setButtonsControl(ActionListener actionListener) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boutons[i][j].addActionListener(actionListener);
            }
        }
        tourFini.addActionListener(actionListener);
    }

    public JButton getButtonAt(int i, int j) {
        return boutons[i][j];
    }

    public JButton getTourFini() { return tourFini; }


    public void display() {
        setVisible(true);
    }
}
