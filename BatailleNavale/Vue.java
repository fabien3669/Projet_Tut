package BatailleNavale;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;


public class Vue extends JFrame {

    String path = "textures/"; // Ecrire le bon chemin qui mène au dossier des icones


    private final Model model;


    // BACKGROUNDS
    ImageIcon titleScreen;
    ImageIcon gameBackground;
    ImageIcon rules;
    ImageIcon blueTransition;
    ImageIcon redTransition;
    ImageIcon gameBackgroundBateaux;
    // BOUTONS
    ImageIcon buttonback;
    ImageIcon buttonplay;
    ImageIcon buttonquit;
    ImageIcon buttonrules;
    ImageIcon buttondone;
    ImageIcon buttoneye;
    // CASES
    ImageIcon BOOM;
    ImageIcon inconnu;
    ImageIcon naviredetruit;
    ImageIcon vide;
    ImageIcon navirecoule;
    ImageIcon navire;


    JPanel cards;

    // JEU
    // PRINCIPAL
    JLabel labBackground;
    JPanel plateau;
    JLayeredPane layeredPaneJeu;

    JButton JBtourFini;
    JButton JBviewbateaux;
    JButton[][] boutons;

    JPanel panJeu;

    // SES BATEAUX
    JLabel labTOPSECRET;
    JLayeredPane layeredPaneTOPSECRET;
    JButton JBback;
    JPanel panTOPSECRET;
    JPanel plateauBateaux;
    JLabel[][] cases;



    // ECRAN TITRE
    JLabel labBackgroundEcranTitre;
    JLayeredPane layeredPaneEcranTitre;

    JButton JBplay;
    JButton JBquit;
    JButton JBrules;

    JPanel panEcranTitre;

    // ECRAN REGLES
    JLabel labBackgroundRules;
    JButton JBbacktotitlescreen;
    JLayeredPane layeredPaneRules;
    JLabel texteRegles;

    JPanel panRegles;

    // CHANGEMENT
    JButton JBChangeP1;
    JPanel panChangeP1;
    JLayeredPane layeredPaneP1;

    JButton JBChangeP2;
    JPanel panChangeP2;
    JLayeredPane layeredPaneP2;



    public Vue(Model model) {

        this.model = model;
        loadIcons();
        initAttribut();
        refresh();
        refreshShowBateaux();
        setTitle("Bataille navale");
        setLocation(200, 30);
        setSize(845, 640);
        setResizable(false);
        pack();
        setDefaultCloseOperation(3);

    }

    public void initAttribut() {

        // TOP SECRET *********************************************
        plateauBateaux = new JPanel(new GridLayout(10, 10));
        cases = new JLabel[10][10];
        Border borders = BorderFactory.createEmptyBorder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cases[i][j] = new JLabel();
                cases[i][j].setPreferredSize(new Dimension(50, 50));
                cases[i][j].setBorder(borders);
                plateauBateaux.add(cases[i][j]);
            }
        }
        plateauBateaux.setBounds(180, 90, 500, 500);
        labTOPSECRET = new JLabel(gameBackgroundBateaux);
        JBback = new JButton();
        JBback.setIcon(buttoneye);
        JBback.setBorder(BorderFactory.createEmptyBorder());
        JBback.setBounds(755, 15, 64, 68);
        layeredPaneTOPSECRET = new JLayeredPane();
        layeredPaneTOPSECRET.setPreferredSize(new Dimension(835, 640));
        labTOPSECRET.setBounds(0, 0, 835, 640);
        layeredPaneTOPSECRET.add(plateauBateaux);
        layeredPaneTOPSECRET.add(JBback);
        layeredPaneTOPSECRET.add(labTOPSECRET);
        panTOPSECRET = new JPanel();
        panTOPSECRET.add(layeredPaneTOPSECRET);

        // CHANGE JOUEUR 1 2 **************************************
        JBChangeP1 = new JButton(redTransition);
        layeredPaneP1 = new JLayeredPane();
        layeredPaneP1.setPreferredSize(new Dimension(835,640));
        JBChangeP1.setBounds(0, 0, 835, 640);
        layeredPaneP1.add(JBChangeP1);
        panChangeP1 = new JPanel();
        panChangeP1.add(layeredPaneP1);

        JBChangeP2 = new JButton(blueTransition);
        layeredPaneP2 = new JLayeredPane();
        layeredPaneP2.setPreferredSize(new Dimension(835,640));
        JBChangeP2.setBounds(0, 0, 835, 640);
        layeredPaneP2.add(JBChangeP2);
        panChangeP2 = new JPanel();
        panChangeP2.add(layeredPaneP2);

        // ECRAN TITRE ***************************************
        labBackgroundEcranTitre = new JLabel(titleScreen);
        labBackgroundEcranTitre.setBounds(0, 0, 835, 640);

        JBplay = new JButton("");
        JBplay.setIcon(buttonplay);
        JBplay.setBorder(BorderFactory.createEmptyBorder());
        JBplay.setBounds(365, 560, 128, 64);

        JBquit = new JButton("");
        JBquit.setIcon(buttonquit);
        JBquit.setBorder(BorderFactory.createEmptyBorder());
        JBquit.setBounds(545, 560, 128, 64);

        JBrules = new JButton("");
        JBrules.setIcon(buttonrules);
        JBrules.setBorder(BorderFactory.createEmptyBorder());
        JBrules.setBounds(150, 560, 160, 64);

        layeredPaneEcranTitre = new JLayeredPane();
        layeredPaneEcranTitre.setPreferredSize(new Dimension(835,640));

        layeredPaneEcranTitre.add(labBackgroundEcranTitre, new Integer(0), 0);
        layeredPaneEcranTitre.add(JBrules, new Integer(1), 0);
        layeredPaneEcranTitre.add(JBplay, new Integer(2), 0);
        layeredPaneEcranTitre.add(JBquit, new Integer(3), 0);

        panEcranTitre = new JPanel();
        panEcranTitre.add(layeredPaneEcranTitre);


        // ECRAN REGLES **************************************

        labBackgroundRules = new JLabel(rules);
        labBackgroundRules.setBounds(0, 0, 835, 640);
        JBbacktotitlescreen = new JButton("");
        JBbacktotitlescreen.setIcon(buttonback);
        JBbacktotitlescreen.setBorder(BorderFactory.createEmptyBorder());
        JBbacktotitlescreen.setBounds(345, 530, 160, 80);

        texteRegles = new JLabel("<html> Bataille navale : Règles <br><br>" +
                "La bataille navale oppose deux joueurs dont la flotte est composée de 5 bateaux :<br>" +
                "  - Un porte avion (5 cases)<br>" +
                "  - Un croiseur (4 cases)<br>" +
                "  - Un contre-torpilleur (3 cases)<br>" +
                "  - Un sous marin (3 cases)<br>" +
                "  - Un torpilleur (2 cases)<br>" +
                "Ces 5 bateaux sont placés dans une grille de 10x10 cases, numérotée de 1 à 10<br>" +
                "verticalement, et de A à J horizontalement. Le but du jeu pour les joueurs est de<br>" +
                "couler tous les navires adverses.<br>" +
                "A chaque tour, le joueur peut choisir une case où tirer. Si un bateau adverse est<br>" +
                "touché, le joueur peut rejouer. </html>");

        texteRegles.setForeground(Color.white);
        texteRegles.setBounds(175, 100, 750,500);

        layeredPaneRules = new JLayeredPane();
        layeredPaneRules.setPreferredSize(new Dimension(835,640));

        layeredPaneRules.add(labBackgroundRules, new Integer(0), 0);
        layeredPaneRules.add(JBbacktotitlescreen, new Integer(1), 0);
        layeredPaneRules.add(texteRegles, new Integer(2), 0);

        panRegles = new JPanel();
        panRegles.add(layeredPaneRules);


        // ECRAN EN JEU **************************************

        labBackground = new JLabel(gameBackground);
        labBackground.setBounds(0, 0, 835, 640);


        // Création du tableau de boutons
        plateau = new JPanel(new GridLayout(10, 10));
        boutons = new JButton[10][10];
        borders = BorderFactory.createLineBorder(Color.blue);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boutons[i][j] = new JButton("");
                boutons[i][j].setPreferredSize(new Dimension(50, 50));
                boutons[i][j].setBorder(borders);
                plateau.add(boutons[i][j]);
            }
        }
        plateau.setBounds(180, 90, 500, 500);

        JBtourFini = new JButton("");
        JBtourFini.setIcon(buttondone);
        JBtourFini.setBorder(BorderFactory.createEmptyBorder());
        JBtourFini.setBounds(700, 560, 128, 64);

        JBviewbateaux = new JButton("");
        JBviewbateaux.setIcon(buttoneye);
        JBviewbateaux.setBorder(BorderFactory.createEmptyBorder());
        JBviewbateaux.setBounds(755, 15, 64, 68);


        layeredPaneJeu = new JLayeredPane();
        layeredPaneJeu.setPreferredSize(new Dimension(835,640));

        layeredPaneJeu.add(labBackground, new Integer(0), 0);
        layeredPaneJeu.add(plateau, new Integer(1), 0);
        layeredPaneJeu.add(JBtourFini, new Integer(2), 0);
        layeredPaneJeu.add(JBviewbateaux, new Integer(3), 0);

        panJeu = new JPanel();
        panJeu.add(layeredPaneJeu);


        cards = new JPanel(new CardLayout());
        cards.add(panEcranTitre, "titlescreen");
        cards.add(panRegles, "rules");
        cards.add(panJeu, "game");
        cards.add(panChangeP1,"p1");
        cards.add(panChangeP2,"p2");
        cards.add(panTOPSECRET, "showbateaux");


        setContentPane(cards);

    }

    public void switchView(String view) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        refresh();
        refreshShowBateaux();
        cl.show(cards, view);
    }


    public void loadIcons() {

        titleScreen = new ImageIcon(path + "backgrounds/titlescreenFinal.png");
        gameBackground = new ImageIcon(path + "backgrounds/gamebackground.png");
        rules = new ImageIcon(path + "backgrounds/rules.png");
        gameBackgroundBateaux = new ImageIcon(path + "backgrounds/gamebackgroundbateaux.gif");

        blueTransition = new ImageIcon(path + "backgrounds/bluetransition.gif");
        redTransition = new ImageIcon(path + "backgrounds/redtransition.gif");

        buttonback = new ImageIcon(path + "boutons/buttonback.gif");
        buttonplay = new ImageIcon(path + "boutons/buttonplay.gif");
        buttonquit = new ImageIcon(path + "boutons/buttonquit.gif");
        buttonrules = new ImageIcon(path + "boutons/buttonrules.gif");
        buttondone = new ImageIcon(path + "boutons/buttondone.gif");
        buttoneye = new ImageIcon(path + "boutons/buttoneye.gif");

        BOOM = new ImageIcon(path + "cases/BOOM.gif");
        inconnu = new ImageIcon(path + "cases/inconnu.gif");
        naviredetruit = new ImageIcon(path + "cases/naviredétruit.gif");
        navirecoule = new ImageIcon(path + "cases/navirecoulé.gif");
        vide = new ImageIcon(path + "cases/vide.gif");
        navire = new ImageIcon(path + "cases/navire.gif");
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

    public void refreshShowBateaux() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cases[i][j].setIcon(vide);
            }
        }
        Bateau[] tabBateaux;
        Coordonnee[] tirs;
        int istop;

        if (model.getJoueurEnCours() == Model.JOUEUR_1) {
            tabBateaux = model.getJoueur1();
            tirs = model.getTir2();
        } else {
            tabBateaux = model.getJoueur2();
            tirs = model.getTir1();
        }

        for (Bateau b : tabBateaux) {
            if (b.getState()== Bateau.Etat.COULE) {
                for (Coordonnee c : b.getPosition()) {
                    cases[c.getLigne()][c.getColone()].setIcon(navirecoule);
                }
            }
            if (b.getState()== Bateau.Etat.TOUCHE) {
                for (Coordonnee c : b.getPosition()) {
                    cases[c.getLigne()][c.getColone()].setIcon(naviredetruit);
                }
            }
            if (b.getState()== Bateau.Etat.SAIN) {
                for (Coordonnee c : b.getPosition()) {
                    cases[c.getLigne()][c.getColone()].setIcon(navire);
                }
            }
        }
    }


    void dialog(String message, String titre, int option) {
        JOptionPane d = new JOptionPane();
        d.showMessageDialog(this, message, titre, option); //JOPtionPane.
    }


    public void setButtonsControlGame(ActionListener actionListener) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boutons[i][j].addActionListener(actionListener);
            }
        }
    }

    public void setControls(ActionListener actionListener) {

        JBtourFini.addActionListener(actionListener);
        JBplay.addActionListener(actionListener);
        JBquit.addActionListener(actionListener);
        JBrules.addActionListener(actionListener);
        JBbacktotitlescreen.addActionListener(actionListener);
        JBviewbateaux.addActionListener(actionListener);
        JBback.addActionListener(actionListener);
        JBChangeP1.addActionListener(actionListener);
        JBChangeP2.addActionListener(actionListener);
    }

    public JButton getButtonAt(int i, int j) {
        return boutons[i][j];
    }

    public JButton getJBTourFini() { return JBtourFini; }

    public JButton getJBplay() { return JBplay; }

    public JButton getJBquit() { return JBquit; }

    public JButton getJBrules() { return JBrules; }

    public JButton getJBbacktotitlescreen() { return JBbacktotitlescreen; }

    public JButton getJBback() { return JBback; }

    public JButton getJBviewbateaux() { return JBviewbateaux; }

    public JButton getJBChangeP1() { return JBChangeP1; }

    public JButton getJBChangeP2() { return JBChangeP2; }

    public ImageIcon getExplosion() { return BOOM; }

    public JPanel getCards() { return cards; }

    public void display() {
        setVisible(true);
    }
}