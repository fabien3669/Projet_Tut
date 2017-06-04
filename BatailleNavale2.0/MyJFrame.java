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
        reprendre = new JButton("Reprendre");
        nouvellePartie = new JButton("Nouvelle Partie");
        but.setBorder(BorderFactory.createEmptyBorder());
        reprendre.setBorder(BorderFactory.createEmptyBorder());
        nouvellePartie.setBorder(BorderFactory.createEmptyBorder());
        setSize(d);
        setLocationRelativeTo(parent);
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
        setLocationRelativeTo(fenetre);
        JLabel label = new JLabel("Voulez vous commencer une nouvelle partie?");
        JPanel panel = new JPanel();
        panel.add(label);
        JPanel butpan = new JPanel();
        reprendre.setBounds(10, 50, 50, 20);
        nouvellePartie.setBounds(90, 50, 50, 20);
        butpan.add(reprendre);
        butpan.add(nouvellePartie);
        panel.add(butpan);
        setContentPane(panel);
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
