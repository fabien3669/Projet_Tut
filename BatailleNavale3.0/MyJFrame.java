package BatailleNavale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by fabie_000 on 04/06/2017.
 */
public class MyJFrame extends JDialog {
    public static final int AFFICHAGE_TEXTURE = 0;
    public static final int AFFICHAGE_LABEL = 1;
    public static final int AFFICHAGE_VICTOIRE = 2;
    public static final int VICTOIRE = 0;
    public static final int DEFAITE = 1;
    private String string;
    private JButton but;
    private JButton reprendre, nouvellePartie, deuxJoueur, unJoueur;
    private Fenetre fenetre;

    public MyJFrame(JFrame parent){
        super(parent);
        setUndecorated(true);
        fenetre =(Fenetre) parent;
        reprendre = new JButton();
        nouvellePartie = new JButton();
        deuxJoueur = new JButton();
        unJoueur = new JButton();
        reprendre.setBorder(BorderFactory.createEmptyBorder());
        nouvellePartie.setBorder(BorderFactory.createEmptyBorder());
        setResizable(false);
    }

    public void setInformation(String icon, int code) {
        JLayeredPane layeredPane = null;
        but = new JButton();
        but.setBorder(BorderFactory.createEmptyBorder());
        if (code == AFFICHAGE_VICTOIRE){
            setSize(new Dimension(845, 345));
        }
        else if (code == AFFICHAGE_LABEL){
            setSize(new Dimension(640, 320));
        }
        else{
            setSize(new Dimension(845, 65));
        }
        setLocationRelativeTo(fenetre);
        this.string = icon;
        if (code == AFFICHAGE_LABEL){
            layeredPane = new JLayeredPane();
            but.setIcon(new ImageIcon(Textures.BACKGROUNDPOPUP.getPath()));
            JLabel lab = new JLabel(icon);
            lab.setFont(new Font("stencil", Font.BOLD, 20));
            but.setBounds(0,0,640,320);
            lab.setBounds(140,135,370,50);
            layeredPane.add(but, 0, 0);
            layeredPane.add(lab, 1, 0);
        }
        else{
            but.setIcon(new ImageIcon(string));
        }
        this.but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        if (code == AFFICHAGE_LABEL){
            setContentPane(layeredPane);
        }
        else{
            setContentPane(but);
        }
        display();
    }

    private void display() {
        setVisible(true);
    }

    public void setForms() {
        setLocation(fenetre.getX()+110, fenetre.getY()+175);
        setSize(new Dimension(640, 320));
        JLabel label = new JLabel("<html>Voulez vous commencer une nouvelle partie <br>&emsp;&emsp;&emsp;&emsp;  ou continuer celle en cours?</html>");
        label.setFont(new Font("stencil", Font.BOLD, 20));
        label.setBounds(60, 60, 560, 50);
        JLabel lab = new JLabel();
        lab.setIcon(new ImageIcon(Textures.BACKGROUNDPOPUP.getPath()));
        lab.setBounds(0,0,640,320);
        reprendre.setIcon(new ImageIcon(Textures.REPRENDRE.getPath()));
        reprendre.setBounds(40, 200, 256, 96);
        nouvellePartie.setIcon(new ImageIcon(Textures.NOUVEAU.getPath()));
        nouvellePartie.setBounds(344, 200, 256, 96);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(lab,0, 0);
        layeredPane.add(label, 1, 0);
        layeredPane.add(reprendre, 1, 0);
        layeredPane.add(nouvellePartie, 1, 0);

        setContentPane(layeredPane);
        display();
    }

    public void setControler(ControlForm controler) {
        this.reprendre.addActionListener(controler);
        this.nouvellePartie.addActionListener(controler);
        this.unJoueur.addActionListener(controler);
        this.deuxJoueur.addActionListener(controler);
    }

    public JButton getNouvellePartie() {
        return nouvellePartie;
    }

    public JButton getReprendre() {
        return reprendre;
    }

    public void setFormulaireModeJeu() {
        setLocation(fenetre.getX()+110, fenetre.getY()+175);
        setSize(new Dimension(640, 320));
        JLabel label = new JLabel("<html>Voulez vous jouer seul ou contre un autre joueur?</html>");
        label.setFont(new Font("stencil", Font.BOLD, 20));
        label.setBounds(60, 60, 560, 50);
        JLabel lab = new JLabel();
        lab.setIcon(new ImageIcon(Textures.BACKGROUNDPOPUP.getPath()));
        lab.setBounds(0,0,640,320);
        unJoueur.setIcon(new ImageIcon(Textures.UNJOUEUR.getPath()));
        unJoueur.setBounds(23, 200, 288, 96);
        unJoueur.setBorder(BorderFactory.createEmptyBorder());
        deuxJoueur.setIcon(new ImageIcon(Textures.DEUXJOUEUR.getPath()));
        deuxJoueur.setBounds(329, 200, 288, 96);
        deuxJoueur.setBorder(BorderFactory.createEmptyBorder());
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setSize(new Dimension(845,640));
        layeredPane.add(lab,0, 0);
        layeredPane.add(label, 1, 0);
        layeredPane.add(unJoueur, 1, 0);
        layeredPane.add(deuxJoueur, 1, 0);

        setContentPane(layeredPane);
        display();
    }

    public void setFinPartie(int victoireDefaite) {
        String path = "";
        if (victoireDefaite==VICTOIRE){
            path = Textures.AFFICHAGEVICTOIRE.getPath();
        }
        else{
            path = Textures.AFFICHAGEDEFAITE.getPath();
        }
        setInformation(path, AFFICHAGE_VICTOIRE);
    }

    public JButton getUnJoueur() {
        return unJoueur;
    }
}
