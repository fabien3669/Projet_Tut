package BatailleNavale;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fabie_000 on 05/05/2017.
 */
public class Fenetre extends JFrame {
    private Model model;
    private JButton jbRules, jbBack, jbPlay, jbQuit, jbEye, jbEyeVB, jbDone, jbTransition, jbMenu;
    private JButton[][] jButtons;
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
        jbDone = new JButton();
        jbMenu = new JButton();
        jbTransition = new JButton();
        jButtons = new JButton[10][10];
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons.length; j++) {
                jButtons[i][j] = new JButton();
            }
        }
    }

    public void affichageMenu(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Textures.TITLESCREEN.getPath()));
        label.setBounds(0, 0, 845, 640);

        jbRules.setIcon(new ImageIcon(Textures.RULES.getPath()));
        jbRules.setBounds(150, 560, 160, 64);
        jbRules.setBorder(BorderFactory.createEmptyBorder());

        jbPlay.setIcon(new ImageIcon(Textures.PLAY.getPath()));
        jbPlay.setBounds(365, 560, 128, 64);
        jbPlay.setBorder(BorderFactory.createEmptyBorder());

        jbQuit.setIcon(new ImageIcon(Textures.QUIT.getPath()));
        jbQuit.setBounds(545, 560, 128, 64);
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
        textLab.setBounds(175, 100, 750,500);


        jbBack.setIcon(new ImageIcon(Textures.BACK.getPath()));
        jbBack.setBounds(200, 560, 128, 64);
        jbBack.setBorder(BorderFactory.createEmptyBorder());

        jbPlay.setIcon(new ImageIcon(Textures.PLAY.getPath()));
        jbPlay.setBounds(365, 560, 128, 64);
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

        jbDone.setIcon(new ImageIcon(Textures.DONE.getPath()));
        jbDone.setBounds(710, 560, 128, 64);
        jbDone.setBorder(BorderFactory.createEmptyBorder());
        if (!model.isaJoue()){
            jbDone.setEnabled(false);
        }

        jbEye.setIcon(new ImageIcon(Textures.EYE.getPath()));
        jbEye.setBounds(755, 5, 64, 68);
        jbEye.setBorder(BorderFactory.createEmptyBorder());

        jbMenu.setIcon(new ImageIcon(Textures.DONE.getPath()));
        jbMenu.setBounds(5,5,128,64);
        jbMenu.setBorder(BorderFactory.createEmptyBorder());

        JPanel tabBut = new JPanel(new GridLayout(10,10));
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons.length; j++) {
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
                        jButtons[i][j].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                    }
                    else if (b.getState().equals("BATEAUTOUCHE")){
                        jButtons[i][j].setIcon(new ImageIcon(Textures.BATEAUDETRUT.getPath()));
                    }
                    else if (b.getState().equals("BATEAUCOULE")){
                        jButtons[i][j].setIcon(new ImageIcon(Textures.BATEAUCOULE.getPath()));
                    }
                }
                else {
                    jButtons[i][j].setIcon(new ImageIcon(Textures.INCONNU.getPath()));
                }
                jButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
                tabBut.add(jButtons[i][j]);
            }
        }
        tabBut.setBounds(185,90,500,500);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(label,0, 0);
        layeredPane.add(jbDone, 1, 0);
        layeredPane.add(jbEye, 1, 0);
        layeredPane.add(jbMenu, 1, 0);
        layeredPane.add(tabBut, 1, 0);

        refreshPane(layeredPane);
    }

    public void affichageBateaux(){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(Textures.BACKGROUNDBATEAUX.getPath()));
        label.setBounds(0, 0, 845, 640);

        jbEyeVB.setIcon(new ImageIcon(Textures.EYE.getPath()));
        jbEyeVB.setBounds(755, 5, 64, 68);
        jbEyeVB.setBorder(BorderFactory.createEmptyBorder());

        JPanel tabBut = new JPanel(new GridLayout(10,10));

        JButton[][] tabButtons = new JButton[10][10];
        for (int i = 0; i < tabButtons.length; i++) {
            for (int j = 0; j < tabButtons.length; j++) {
                tabButtons[i][j] = new JButton();
                tabButtons[i][j].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                tabButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
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
                                tabButtons[i][j].setIcon(new ImageIcon(Textures.BATEAUDETRUT.getPath()));
                            }
                            else {
                                tabButtons[i][j].setIcon(new ImageIcon(Textures.BATEAUCOULE.getPath()));
                            }
                        }
                        else {
                            tabButtons[i][j].setIcon(new ImageIcon(Textures.BATEAU.getPath()));
                        }
                        tabButtons[i][j].setBorder(BorderFactory.createEmptyBorder());
                    }
                }
                tabBut.add(tabButtons[i][j]);
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
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons.length; j++) {
                jButtons[i][j].addActionListener(controlButtonJeu);
            }
        }
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

    public JButton getJbTransition() {
        return jbTransition;
    }

    public JButton getJbMenu() {
        return jbMenu;
    }

    public JButton getJbDone() {
        return jbDone;
    }

    public void dialog(String state, String s) {
        JOptionPane d = new JOptionPane();
        d.showMessageDialog(this, state, s, JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton[][] getJButtons() {
        return jButtons;
    }
}