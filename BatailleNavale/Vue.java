import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;


public class Vue extends JFrame {

    String path = "textures/"; // Ecrire le bon chemin qui mène au dossier des icones


    private final Model model;

    JButton[][] boutons;

    ImageIcon titleScreen;
    ImageIcon gameBackground;
    ImageIcon rules;




    public Vue(Model model) {

        this.model = model;
        loadIcons();
        initAttribut();
        creerVue();
        setTitle("Bataille navale");
        setLocation(200,200);
        setSize(700,300);
        setResizable(false);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void initAttribut() {

        titleScreen = new ImageIcon("backgrounds/titlescreenFinal.gif");
        gameBackground = new ImageIcon("backgrounds/gameBackground.gif");
        rules = new ImageIcon("backgrounds/rules.gif");

    }


    public void creerVue() {
    }

    void dialog(String message, String titre, int option) {
        JOptionPane d = new JOptionPane();
        d.showMessageDialog( this, message, titre, option); //JOPtionPane.
    }



    public void setButtonsControl(ActionListener actionListener) {
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                boutons[i][j].addActionListener(actionListener);
            }
        }
    }




    public void loadIcons() {


    }

    public void display() {
        setVisible(true);
    }
}
