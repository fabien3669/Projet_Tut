package BatailleNavale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by fabie_000 on 04/06/2017.
 */
public class MyJFrame extends JDialog {
    private String string;
    private JButton but;
    private JButton reprendre, nouvellePartie;
    private Fenetre fenetre;

    public MyJFrame(JFrame parent, Dimension d){
        super(parent);
        setUndecorated(true);
        fenetre =(Fenetre) parent;
        but = new JButton();
        reprendre = new JButton();
        nouvellePartie = new JButton();
        but.setBorder(BorderFactory.createEmptyBorder());
        reprendre.setBorder(BorderFactory.createEmptyBorder());
        nouvellePartie.setBorder(BorderFactory.createEmptyBorder());
        setSize(d);
        setResizable(false);
    }

    public void setIcon(String icon) {
        setLocationRelativeTo(fenetre);
        this.string = icon;
        but.setIcon(new ImageIcon(string));
        this.but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setContentPane(but);
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
    }

    public JButton getNouvellePartie() {
        return nouvellePartie;
    }

    public JButton getReprendre() {
        return reprendre;
    }
}
