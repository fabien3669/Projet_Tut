package BatailleNavale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;

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
        this.frame = new MyJFrame(fenetre);
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
            if (model.getIndiceDernierCoupJoueur1()!=0 && !model.partieTerminee()){
                frame.setForms();
            }
            else{
                model.initModel();
                frame.setFormulaireModeJeu();
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
        this.frame = new MyJFrame(fenetre);
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
            if (model.getModeDeJeu()==model.JOUEUR_CONTRE_JOUEUR){
                fenetre.affichageTransition();
            }
            else {
                model.joueIA();
                fenetre.affichageTirs();
            }
            fenetre.display();
        }
        else{
            if (!model.isaJoue()){
                int l = but.getY()/but.getHeight();
                int c = but.getX()/but.getWidth();
                Coordonnee xy = new Coordonnee(l, c);
                if (model.isCoupValide(xy)){
                    model.joueTir(xy);
                    if (model.isBateauAt(xy)){
                        Bateau b = model.getBateauAt(xy);
                        model.updateBateau(b);
                        if (b.getState().equals("BATEAUTOUCHE")){
                            frame.setInformation(Textures.AFFICHAGETOUCHE.getPath(), frame.AFFICHAGE_TEXTURE);
                            fenetre.getJButtonsJeux()[l][c].setIcon(new ImageIcon(Textures.BATEAUTOUCHE.getPath()));
                        }
                        else{
                            frame.setInformation(Textures.AFFICHAGECOULE.getPath(), frame.AFFICHAGE_TEXTURE);
                            for (int i = 0; i < b.getPosition().length; i++) {
                                int k = b.getPosition()[i].getLigne();
                                int m = b.getPosition()[i].getColonne();
                                fenetre.getJButtonsJeux()[k][m].setIcon(new ImageIcon(Textures.BATEAUCOULE.getPath()));
                            }
                        }
                    }
                    else{
                        frame.setInformation(Textures.AFFICHAGERATE.getPath(), frame.AFFICHAGE_TEXTURE);
                        fenetre.getJButtonsJeux()[l][c].setIcon(new ImageIcon(Textures.VIDE.getPath()));
                    }
                    if (model.partieTerminee()){
                        frame.setFinPartie();
                    }
                    else{
                        model.setaJoue(true);
                        fenetre.getJbDone().setEnabled(true);
                        fenetre.display();
                    }
                }
            }
            else {
                frame.setInformation("<html>Vous avez terminé votre tour,<br>&emsp; veuillez cliquer sur DONE</html>", frame.AFFICHAGE_LABEL);
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

class ControlForm extends ControlButton implements ActionListener{
    MyJFrame frame;

    public ControlForm(MyJFrame frame, Model model, Fenetre fenetre) {
        super(model, fenetre);
        this.frame = frame;
        frame.setControler(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but = (JButton) e.getSource();
        if (but.equals(frame.getNouvellePartie())) {
            model.initModel();
            frame.setFormulaireModeJeu();
        }
        else if (but.equals(frame.getReprendre())) {
            if (model.getModeDeJeu()==model.JOUEUR_CONTRE_JOUEUR){
                fenetre.affichageTransition();
            }
            else{
                fenetre.affichageTirs();
            }
            frame.setVisible(false);
            fenetre.display();
        }
        else {
            if (but.equals(frame.getUnJoueur())){
                model.setModeDeJeu(model.JOUEUR_CONTRE_IA);
            }
            else {
                model.setModeDeJeu(model.JOUEUR_CONTRE_JOUEUR);
            }
            if (model.getModeDeJeu()==model.JOUEUR_CONTRE_JOUEUR){
                fenetre.affichageTransition();
            }
            else{
                fenetre.affichagePositionnement();
            }
            fenetre.display();
            frame.setVisible(false);
        }
    }
}