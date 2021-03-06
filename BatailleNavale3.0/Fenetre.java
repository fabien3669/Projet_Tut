package BatailleNavale;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fabie_000 on 05/05/2017.
 */
public class Fenetre extends JFrame {
    private Model model;
    private JButton jbRules, jbBack, jbPlay, jbQuit, jbEye, jbEyeVB, jbDone, jbTransition, jbMenu;
    private JButton[][] jButtonsJeux, jButtonsPlacement;
    private JLayeredPane layeredPane;

    public Fenetre(Model model){
        this.model = model;
        initAttribut();
        affichageMenu();
        setTitle("BATAILLE NAVALE");
        setIconImage(new ImageIcon(Textures.ICONE.getPath()).getImage());
        setSize(new Dimension(860,680));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display();
    }

    private void initAttribut() {
        jbRules = new JButton();
        jbBack = new JButton();
        jbPlay = new JButton();
        jbQuit = new JButton();
        jbEye = new JButton();
        jbEyeVB = new JButton();
        jbEye.setToolTipText("Au rapport Capitaine, quelle est la situation?");
        jbEyeVB.setToolTipText("A L'ATTAQUE, MOUSSAILLON");
        jbDone = new JButton();
        jbMenu = new JButton();
        jbMenu.setToolTipText("Quitter le navire");
        jbTransition = new JButton();
        jButtonsJeux = new JButton[10][10];
        jButtonsPlacement = new JButton[10][10];
        for (int i = 0; i < jButtonsJeux.length; i++) {
            for (int j = 0; j < jButtonsJeux.length; j++) {
                jButtonsJeux[i][j] = new JButton();
                jButtonsPlacement[i][j] = new JButton();
            }
        }
    }

    public void affichageMenu(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Textures.TITLESCREEN.getPath()));
        label.setBounds(0, 0, 845, 640);

        jbRules.setIcon(new ImageIcon(Textures.REGLES.getPath()));
        jbRules.setBounds(19, 535, 256, 96);
        jbRules.setBorder(BorderFactory.createEmptyBorder());

        jbPlay.setIcon(new ImageIcon(Textures.JOUER.getPath()));
        jbPlay.setBounds(294, 535, 256, 96);
        jbPlay.setBorder(BorderFactory.createEmptyBorder());

        jbQuit.setIcon(new ImageIcon(Textures.QUITTER.getPath()));
        jbQuit.setBounds(569, 535, 256, 96);
        jbQuit.setBorder(BorderFactory.createEmptyBorder());

        layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(label,0, 0);
        layeredPane.add(jbRules,1, 0);
        layeredPane.add(jbPlay, 1, 0);
        layeredPane.add(jbQuit, 1, 0);
        refreshPane(layeredPane);
    }

    public void affichageRegles(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Textures.BACKGROUNDRULES.getPath()));
        label.setBounds(0, 0, 845, 640);
        JLabel textLab = new JLabel();
        String text = model.getRegles();                ;
        textLab.setText(text);
        textLab.setForeground(Color.white);
        textLab.setBounds(90, 80, 700,500);


        jbBack.setIcon(new ImageIcon(Textures.RETOUR.getPath()));
        jbBack.setBounds(155, 551, 64, 64);
        jbBack.setBorder(BorderFactory.createEmptyBorder());

        jbPlay.setIcon(new ImageIcon(Textures.JOUER.getPath()));
        jbPlay.setBounds(294, 535, 256, 96);
        jbPlay.setBorder(BorderFactory.createEmptyBorder());

        layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(label,0, 0);
        layeredPane.add(textLab,1, 0);
        layeredPane.add(jbPlay, 1, 0);
        layeredPane.add(jbBack, 1, 0);
        refreshPane(layeredPane);
    }

    public void affichageTransition(){
        if (model.getJoueurEnCours()==model.JOUEUR_1){
            jbTransition.setIcon(new ImageIcon(Textures.TRANSITIONJ1.getPath()));
        }
        else {
            jbTransition.setIcon(new ImageIcon(Textures.TRANSITIONJ2.getPath()));
        }
        jbTransition.setBounds(0, 0, 845, 640);
        layeredPane = new JLayeredPane();
        layeredPane.add(jbTransition);
        refreshPane(layeredPane);
    }

    public void affichageTirs(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Textures.BACKGROUNDJEU.getPath()));
        label.setBounds(0, 0, 845, 640);

        jbDone.setIcon(new ImageIcon(Textures.FINTOUR.getPath()));
        jbDone.setBounds(742, 560, 64, 64);
        jbDone.setBorder(BorderFactory.createEmptyBorder());
        if (!model.isaJoue()){
            jbDone.setEnabled(false);
        }

        jbEye.setIcon(new ImageIcon(Textures.EYE.getPath()));
        jbEye.setBounds(757, 5, 64, 64);
        jbEye.setBorder(BorderFactory.createEmptyBorder());

        jbMenu.setIcon(new ImageIcon(Textures.MENU.getPath()));
        jbMenu.setBounds(5,5,64,64);
        jbMenu.setBorder(BorderFactory.createEmptyBorder());

        JPanel tabBut = new JPanel(new GridLayout(10,10));
        for (int i = 0; i < jButtonsJeux.length; i++) {
            for (int j = 0; j < jButtonsJeux.length; j++) {
                Coordonnee c = new Coordonnee(i,j);
                Coordonnee[] tab;
                if (model.getJoueurEnCours()==model.JOUEUR_1){
                    tab = model.getTir1();
                }
                else {
                    tab = model.getTir2();
                }
                if (c.isDansTableau(tab)){
                    Bateau b = model.getBateauAt(c);
                    if (b==null){
                        jButtonsJeux[i][j].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                    }
                    else if (b.getState().equals("BATEAUTOUCHE")){
                        jButtonsJeux[i][j].setIcon(new ImageIcon(Textures.BATEAUDETRUT.getPath()));
                    }
                    else if (b.getState().equals("BATEAUCOULE")){
                        jButtonsJeux[i][j].setIcon(new ImageIcon(Textures.BATEAUCOULE.getPath()));
                    }
                }
                else {
                    jButtonsJeux[i][j].setIcon(new ImageIcon(Textures.INCONNU.getPath()));
                }
                jButtonsJeux[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
                tabBut.add(jButtonsJeux[i][j]);
            }
        }
        tabBut.setBounds(185,90,500,500);

        JLabel label1 = new JLabel();
        String text = "L'adversaire a raté son dernier tir !";
        Coordonnee coordonnee = null;
        if (!model.isPlacementBateau()){
            if (model.getJoueurEnCours()==model.JOUEUR_1){
                if (model.getIndiceDernierCoupJoueur2()>=1){
                    coordonnee = model.getTir2()[model.getIndiceDernierCoupJoueur2()-1];
                }
                else{
                    text="";
                }
            }
            else{
                coordonnee = model.getTir1()[model.getIndiceDernierCoupJoueur1()-1];
            }
        }
        model.changeJoueurEnCours();
        if (model.isBateauAt(coordonnee)){
            Bateau b = model.getBateauAt(coordonnee);
            if (b.getState().equals("BATEAUTOUCHE")){
                text = "L'adversaire a touché un bateau ! ";
            }
            else{
                text = "L'adversaire a coulé un bateau ! ";
            }
        }
        model.changeJoueurEnCours();
        label1.setText(text);
        label1.setFont(new Font("stencil", Font.BOLD, 15));
        label1.setBounds(300, 600, 400, 40);

        JPanel tabEtatBateaux = new JPanel(new GridLayout(1, 5));
        tabEtatBateaux.setOpaque(false);
        tabEtatBateaux.setBorder(BorderFactory.createLineBorder(Color.black));
        Bateau[] tabBateau;
        if (model.getJoueurEnCours()==model.JOUEUR_1){
            tabBateau=model.getJoueur2();
        }
        else{
            tabBateau=model.getJoueur1();
        }
        for (int i = 0; i < tabBateau.length; i++) {
            JButton but = new JButton();
            but.setBorder(BorderFactory.createLineBorder(Color.black));
            String etat = "";
            if (tabBateau[i].getState().equals(Etat.SAIN.getState())){
                but.setBackground(Color.red);
                etat="SAIN";
            }
            else if (tabBateau[i].getState().equals(Etat.TOUCHE.getState())){
                but.setBackground(Color.orange);
                etat="TOUCHE";
            }
            else{
                but.setBackground(Color.green);
                etat="COULE";
            }
            String txt="Bateau de ";
            switch (i){
                case 0:
                    txt+="5 cases";
                    break;
                case 1:
                    txt+="4 cases";
                    break;
                case 2:
                    txt+="3 cases";
                    break;
                case 3:
                    txt+="3 cases";
                    break;
                default:
                    txt+="2 cases";
                    break;
            }
            txt+=(", "+etat);
            but.setToolTipText(txt);
            tabEtatBateaux.add(but);
        }
        tabEtatBateaux.setBounds(727, 117, 98, 24);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(label,0, 0);
        layeredPane.add(jbDone, 1, 0);
        layeredPane.add(jbEye, 1, 0);
        layeredPane.add(jbMenu, 1, 0);
        layeredPane.add(tabBut, 1, 0);
        layeredPane.add(label1, 1, 0);
        layeredPane.add(tabEtatBateaux, 1, 0);

        refreshPane(layeredPane);
    }

    public void affichageBateaux(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Textures.BACKGROUNDBATEAUX.getPath()));
        label.setBounds(0, 0, 845, 640);

        jbEyeVB.setIcon(new ImageIcon(Textures.EYE.getPath()));
        jbEyeVB.setBounds(757, 5, 64, 64);
        jbEyeVB.setBorder(BorderFactory.createEmptyBorder());

        JPanel tabBut = new JPanel(new GridLayout(10,10));

        JButton[][] jButtons = new JButton[10][10];
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons.length; j++) {
                jButtons[i][j] = new JButton();
                jButtons[i][j].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                jButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
                Coordonnee c = new Coordonnee(i,j);
                Coordonnee[] tab;
                Bateau[] tab2;
                if (model.getJoueurEnCours()==model.JOUEUR_1){
                    tab = model.getTir2();
                    tab2 = model.getJoueur1();
                }
                else {
                    tab = model.getTir1();
                    tab2 = model.getJoueur2();
                }
                for (int k = 0; k < tab2.length; k++) {
                    Bateau b = tab2[k];
                    if (c.isDansTableau(b.getPosition())){
                        if (c.isDansTableau(tab)){
                            if (b.getState().equals("BATEAUTOUCHE")){
                                jButtons[i][j].setIcon(new ImageIcon(Textures.BATEAUDETRUT.getPath()));
                            }
                            else {
                                jButtons[i][j].setIcon(new ImageIcon(Textures.BATEAUCOULE.getPath()));
                            }
                        }
                        else {
                            jButtons[i][j].setIcon(new ImageIcon(Textures.BATEAU.getPath()));
                        }
                        jButtons[i][j].setBorder(BorderFactory.createEmptyBorder());
                    }
                }
                tabBut.add(jButtons[i][j]);
            }
        }
        tabBut.setBounds(185,90,500,500);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(label,0, 0);
        layeredPane.add(jbEyeVB, 1, 0);
        layeredPane.add(tabBut, 1, 0);

        refreshPane(layeredPane);
    }

    public void affichagePositionnement(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Textures.BACKGROUNDJEU.getPath()));
        label.setBounds(0, 0, 845, 640);

        jbDone.setIcon(new ImageIcon(Textures.FINTOUR.getPath()));
        jbDone.setBounds(742, 560, 64, 64);
        jbDone.setBorder(BorderFactory.createEmptyBorder());
        if (!model.placementOk()){
            jbDone.setEnabled(false);
        }
        else{
            jbDone.setEnabled(true);
        }

        jbMenu.setIcon(new ImageIcon(Textures.MENU.getPath()));
        jbMenu.setBounds(5,5,64,64);
        jbMenu.setBorder(BorderFactory.createEmptyBorder());

        JPanel tabButPlacement = new JPanel(new GridLayout(10,10));
        for (int i = 0; i < jButtonsPlacement.length; i++) {
            for (int j = 0; j < jButtonsPlacement.length; j++) {
                jButtonsPlacement[i][j].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                jButtonsPlacement[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
                Coordonnee c = new Coordonnee(i,j);
                Bateau[] tab2;
                if (model.getJoueurEnCours()==model.JOUEUR_1){
                    tab2 = model.getJoueur1();
                }
                else {
                    tab2 = model.getJoueur2();
                }
                for (int k = 0; k < tab2.length; k++) {
                    Bateau b = tab2[k];
                    if (c.isDansTableau(b.getPosition())){
                        if (b.equals(model.getBateauOnMoving())){
                            jButtonsPlacement[i][j].setIcon(new ImageIcon(Textures.BATEAUSELECTIONNE.getPath()));
                        }
                        else{
                            jButtonsPlacement[i][j].setIcon(new ImageIcon(Textures.BATEAU.getPath()));
                        }
                        jButtonsPlacement[i][j].setBorder(BorderFactory.createEmptyBorder());
                    }
                }
                tabButPlacement.add(jButtonsPlacement[i][j]);
            }
        }
        tabButPlacement.setBounds(185,90,500,500);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(label,0, 0);
        layeredPane.add(jbDone, 1, 0);
        layeredPane.add(jbMenu, 1, 0);
        layeredPane.add(tabButPlacement, 1, 0);

        refreshPane(layeredPane);
    }

    public void display(){
        setVisible(true);
    }

    private void refreshPane(JLayeredPane layeredPane) {
        setContentPane(layeredPane);
    }

    public void setControlButtonMenu(ControlButtonMenu controlButtonMenu) {
        jbQuit.addActionListener(controlButtonMenu);
        jbPlay.addActionListener(controlButtonMenu);
        jbRules.addActionListener(controlButtonMenu);
        jbBack.addActionListener(controlButtonMenu);
    }

    public void setControlButtonTransition(ControlButtonTransition controlButtonTransition){
        jbTransition.addActionListener(controlButtonTransition);
    }

    public void setControlButtonGame(ControlButtonJeu controlButtonJeu) {
        jbMenu.addActionListener(controlButtonJeu);
        jbDone.addActionListener(controlButtonJeu);
        jbEye.addActionListener(controlButtonJeu);
        jbEyeVB.addActionListener(controlButtonJeu);
        for (int i = 0; i < jButtonsJeux.length; i++) {
            for (int j = 0; j < jButtonsJeux.length; j++) {
                jButtonsJeux[i][j].addActionListener(controlButtonJeu);
            }
        }
    }

    public void setControlPlacement(ControlPlacement controlPlacement) {
        for (int i = 0; i < jButtonsPlacement.length; i++) {
            for (int j = 0; j < jButtonsPlacement.length; j++) {
                jButtonsPlacement[i][j].addMouseListener(controlPlacement);
            }
        }
    }

    public void unsetControlButtonGame(ControlButtonJeu controlButtonJeu){
        for (int i = 0; i < jButtonsJeux.length; i++) {
            for (int j = 0; j < jButtonsJeux.length; j++) {
                jButtonsJeux[i][j].removeActionListener(controlButtonJeu);
            }
        }
        jbDone.removeActionListener(controlButtonJeu);

    }

    public JButton getJbRules() {
        return jbRules;
    }

    public JButton getJbPlay() {
        return jbPlay;
    }

    public JButton getJbQuit() {
        return jbQuit;
    }

    public JButton getJbEye() {
        return jbEye;
    }

    public JButton getJbEyeVB() {
        return jbEyeVB;
    }

    public JButton getJbMenu() {
        return jbMenu;
    }

    public JButton getJbDone() {
        return jbDone;
    }

    public JButton[][] getJButtonsJeux() {
        return jButtonsJeux;
    }
}