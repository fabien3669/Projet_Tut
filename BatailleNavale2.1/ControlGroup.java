package BatailleNavale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/**
 * Created by fabie_000 on 03/06/2017.
 */
public class ControlGroup {
    private Model model;
    private Fenetre fenetre;
    private MyJFrame frame;
    private ControlButtonMenu controlButton;
    private ControlButtonJeu controlButtonJeu;
    private ControlButtonTransition controlButtonTransition;
    private ControlForm controlForm;
    private ControlPlacement controlPlacement;

    public ControlGroup(Model model) {
        this.model = model;
        this.fenetre = new Fenetre(model);
        this.frame = new MyJFrame(fenetre, new Dimension(300,100));
        this.controlButton = new ControlButtonMenu(model, fenetre, frame);
        this.controlButtonJeu = new ControlButtonJeu(model, fenetre);
        this.controlButtonTransition = new ControlButtonTransition(model, fenetre);
        this.controlForm = new ControlForm(frame, model, fenetre);
        this.controlPlacement = new ControlPlacement(model, fenetre);
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
    private MyJFrame frame;

    public ControlButtonMenu(Model model, Fenetre fenetre, MyJFrame frame) {
        super(model, fenetre);
        fenetre.setControlButtonMenu(this);
        this.frame = frame;
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
            if (model.getIndiceDernierCoupJoueur1()!=0){
                frame.setForms();
            }
            else{
                fenetre.affichageTransition();
                fenetre.display();
            }
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
        if (model.isPlacementBateau()){
            fenetre.affichagePositionnement();
            fenetre.display();
        }
        else{
            fenetre.affichageTirs();
            fenetre.display();
        }
    }
}

class ControlButtonJeu extends ControlButton implements ActionListener{
    private MyJFrame frame;

    public ControlButtonJeu(Model model, Fenetre fenetre) {
        super(model, fenetre);
        fenetre.setControlButtonGame(this);
        this.frame = new MyJFrame(fenetre, new Dimension(845, 65));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but = (JButton) e.getSource();
        frame.setVisible(false);
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
                if (model.isCoupValide(xy)){
                    if (model.isBateauAt(xy)){
                        Bateau b = model.getBateauAt(xy);
                        model.updateBateau(b);
                        if (b.getState().equals("BATEAUTOUCHE")){
                            frame.setIcon(Textures.AFFICHAGETOUCHE.getPath());
                            fenetre.getJButtonsJeux()[l][c].setIcon(new ImageIcon(Textures.BATEAUTOUCHE.getPath()));
                        }
                        else{
                            frame.setIcon(Textures.AFFICHAGECOULE.getPath());
                            for (int i = 0; i < b.getPosition().length; i++) {
                                int k = b.getPosition()[i].getLigne();
                                int m = b.getPosition()[i].getColonne();
                                fenetre.getJButtonsJeux()[k][m].setIcon(new ImageIcon(Textures.BATEAUCOULE.getPath()));
                            }
                        }
                    }
                    else{
                        frame.setIcon(Textures.AFFICHAGERATE.getPath());
                        fenetre.getJButtonsJeux()[l][c].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                    }
                    if (model.partieTerminee()){
                        frame.setSize(new Dimension(845, 345));
                        frame.setIcon(Textures.AFFICHAGETERMINE.getPath());
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

class ControlPlacement extends ControlButton implements MouseListener{


    public ControlPlacement(Model model, Fenetre fenetre) {
        super(model, fenetre);
        fenetre.setControlPlacement(this);
    }

    public void deplacementBateauAt(Coordonnee c){
        Coordonnee[] tab = model.getBateauOnMoving().getPosition();
        int diffL = c.getLigne()-tab[0].getLigne();
        int diffC = c.getColonne()-tab[0].getColonne();
        for (int i = 0; i < tab.length; i++) {
            tab[i] = new Coordonnee(tab[i].getLigne()+diffL, tab[i].getColonne()+diffC);
        }
    }

    public void pivoteBateauAt(){
        Coordonnee[] tab = model.getBateauOnMoving().getPosition();
        int l = tab[0].getLigne();
        int c = tab[0].getColonne();
        for (int i = 1; i < tab.length; i++) {
            int diffL =0;
            int diffC = 0;
            if (tab[0].getColonne()-tab[i].getColonne()==0){
                diffC = tab[i].getLigne() - tab[0].getLigne();
            }
            else {
                diffL = tab[i].getColonne() - tab[0].getColonne();
            }
            Coordonnee coord = new Coordonnee(l+diffL, c+diffC);
            tab[i]=coord;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton but = (JButton) e.getSource();
        int l = but.getY()/50;
        int c = but.getX()/50;
        Coordonnee coordonnee = new Coordonnee(l, c);
        if (model.isBateauAt(coordonnee)){
            if (model.getBateauAt(coordonnee).equals(model.getBateauOnMoving())){
                if (e.getButton()==1){
                    deplacementBateauAt(coordonnee);
                }
            }
            model.setBateauSelected(true);
            model.setBateauOnMoving(model.getBateauAt(coordonnee));
            if (e.getButton()==3){
                pivoteBateauAt();
            }
        }
        else {
            if (model.isBateauSelected()){
                if (e.getButton()==1){
                    deplacementBateauAt(coordonnee);
                }
            }
        }
        fenetre.affichagePositionnement();
        fenetre.display();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

class ControlForm implements ActionListener{
    Fenetre fenetre;
    MyJFrame frame;
    Model model;

    public ControlForm(MyJFrame frame, Model model, Fenetre fenetre) {
        this.model=model;
        this.frame = frame;
        this.fenetre = fenetre;
        frame.setControler(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but = (JButton) e.getSource();
        if (but.equals(frame.getNouvellePartie())) {
            model.resetModel();
            fenetre.affichageTransition();
            fenetre.display();
        }
        else if (but.equals(frame.getReprendre())) {
            fenetre.affichageTransition();
            fenetre.display();
        }
        frame.setVisible(false);
    }
}