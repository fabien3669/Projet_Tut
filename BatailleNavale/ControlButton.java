package BatailleNavale;
/*
Views possibles :
"titlescreen" -> écran titre
"rules" -> écran des règles
"game" -> écran de jeu normal où on voit où on a tiré etc.
"showbateaux" -> écran où on voit ses bateaux
"p1" -> transition joueur1
"p2" -> transition joueur2
*/

import java.awt.event.*;

public class ControlButton extends Control implements ActionListener
{

    public ControlButton(Model model, Vue vue) {
        super(model, vue);
        vue.setControls(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(vue.getJBTourFini())) {
            if (model.getaJoue()) {
                model.setaJoue(false);
                model.changeJoueurEnCours();
                if (model.getJoueurEnCours()==1) {
                    vue.switchView("p2");
                }  else {
                    vue.switchView("p1");
                }
            }


            return;
        }

        if (e.getSource().equals(vue.getJBplay())) {

            if (model.getJoueurEnCours()==1) {
                vue.switchView("p2");
            }  else {
                vue.switchView("p1");
            }
            return;
        }

        if (e.getSource().equals(vue.getJBChangeP1()) || e.getSource().equals(vue.getJBChangeP2())) {

            vue.switchView("game");
            return;
        }

        if (e.getSource().equals(vue.getJBrules())) {
            vue.switchView("rules");
            return;
        }

        if (e.getSource().equals(vue.getJBquit())) {
            vue.dispose();
            return;
        }

        if (e.getSource().equals(vue.getJBbacktotitlescreen())) {
            vue.switchView("titlescreen");
            return;
        }

        if (e.getSource().equals(vue.getJBviewbateaux())) {
            vue.switchView("showbateaux");
            return;
        }

        if (e.getSource().equals(vue.getJBback())) {
            vue.switchView("game");
            return;
        }




    }


}