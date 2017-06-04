package BatailleNavale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

/**
 * Created by fabie_000 on 03/06/2017.
 */
public class ControlGroup {
    private Model model;
    private Fenetre fenetre;
    private ControlButtonMenu controlButton;
    private ControlButtonJeu controlButtonJeu;
    private ControlButtonTransition controlButtonTransition;

    public ControlGroup(Model model) {
        this.model = model;
        this.fenetre = new Fenetre(model);
        this.controlButton = new ControlButtonMenu(model, fenetre);
        this.controlButtonJeu = new ControlButtonJeu(model, fenetre);
        this.controlButtonTransition = new ControlButtonTransition(model, fenetre);
    }
}

abstract class ControlButton {
    Model model;
    Fenetre fenetre;

    public ControlButton(Model model, Fenetre fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }
}

class ControlButtonMenu extends ControlButton implements ActionListener {

    public ControlButtonMenu(Model model, Fenetre fenetre) {
        super(model, fenetre);
        fenetre.setControlButtonMenu(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but = (JButton) e.getSource();
        if (but.equals(fenetre.getJbQuit())){
            exit(0);
        }
        else if (but.equals(fenetre.getJbRules())){
            fenetre.affichageRegles();
            fenetre.display();
        }
        else if (but.equals(fenetre.getJbPlay())){
            model.resetModel();
            fenetre.affichageTransition();
            fenetre.display();
        }
        else {
            fenetre.affichageMenu();
            fenetre.display();
        }
    }
}

class ControlButtonTransition extends ControlButton implements ActionListener {
    public ControlButtonTransition(Model model, Fenetre fenetre) {
        super(model, fenetre);
        fenetre.setControlButtonTransition(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).equals(fenetre.getJbTransition())){
            fenetre.affichageTirs();
            fenetre.display();
        }
    }
}

class ControlButtonJeu extends ControlButton implements ActionListener{
    private MyJFrame frame;

    public ControlButtonJeu(Model model, Fenetre fenetre) {
        super(model, fenetre);
        frame = new MyJFrame(fenetre);
        fenetre.setControlButtonGame(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but = (JButton) e.getSource();
        if (but.equals(fenetre.getJbMenu())){
            fenetre.affichageMenu();
            fenetre.display();
        }
        else if (but.equals(fenetre.getJbEye())){
            fenetre.affichageBateaux();
            fenetre.display();
        }
        else if (but.equals(fenetre.getJbEyeVB())){
            fenetre.affichageTirs();
            fenetre.display();
        }
        else if (but.equals(fenetre.getJbDone())){
            model.update();
            fenetre.affichageTransition();
            fenetre.display();
        }
        else{
            if (!model.isaJoue()){
                int l = but.getY()/but.getHeight();
                int c = but.getX()/but.getWidth();
                Coordonnee xy = new Coordonnee(l, c);
                boolean b1 = model.isCoupValide(xy);
                if (b1){
                    boolean b2 = model.isBateauAt(xy);
                    if (b2){
                        Bateau b = model.getBateauAt(xy);
                        if (b.getState().equals("TOUCHE")){
                            frame.setIcon(Textures.AFFICHAGETOUCHE.getPath());
                            fenetre.getJButtons()[l][c].setIcon(new ImageIcon(Textures.TOUCHE.getPath()));
                        }
                        else{
                            frame.setIcon(Textures.AFFICHAGECOULE.getPath());
                            for (int i = 0; i < b.getPosition().length; i++) {
                                int k = b.getPosition()[i].getLigne();
                                int m = b.getPosition()[i].getColonne();
                                fenetre.getJButtons()[k][m].setIcon(new ImageIcon(Textures.COULE.getPath()));
                            }
                        }
                    }
                    else{
                        frame.setIcon(Textures.AFFICHAGERATE.getPath());
                        fenetre.getJButtons()[l][c].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                    }
                    if (model.partieTerminee()){
                        if (model.getJoueurEnCours()==Model.JOUEUR_1){
                            frame.setIcon(Textures.AFFICHAGETERMINEJ1.getPath());
                        }
                        else{
                            frame.setIcon(Textures.AFFICHAGETERMINEJ2.getPath());
                        }
                    }
                    else{
                        model.setaJoue(true);
                        fenetre.getJbDone().setEnabled(true);
                        fenetre.display();
                    }
                }
            }
            else {
                fenetre.dialog("Vous avez terminé votre tour, veuillez cliquer sur DONE", "Tour terminé");
            }
        }
    }
}
