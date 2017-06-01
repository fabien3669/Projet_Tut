package BatailleNavale;


import java.awt.event.*;

public class ControlButtonGame extends Control implements ActionListener
{

    public ControlButtonGame(Model model, Vue vue) {
        super(model, vue);
        vue.setButtonsControlGame(this);
    }

    public void actionPerformed(ActionEvent e) {

        int x = 0;
        int y = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (e.getSource().equals(vue.getButtonAt(i, j))) {
                    y = i;
                    x = j;
                }
            }
        }
        //System.out.println("x = "+x+", y = "+y);

        if (model.isCoupValide(new Coordonnee(y, x)) && model.getaJoue()==false) {
            System.out.println("a joué");
            model.setaJoue(true);
            if (model.getJoueurEnCours()==Model.JOUEUR_1) {
                model.getTir1()[model.getIndiceDernierCoupJoueur1()]=new Coordonnee(y, x);
                model.setIndiceDernierCoupJoueur1(model.getIndiceDernierCoupJoueur1()+1);
            } else {
                model.getTir2()[model.getIndiceDernierCoupJoueur2()]=new Coordonnee(y, x);
                model.setIndiceDernierCoupJoueur2(model.getIndiceDernierCoupJoueur2()+1);
            }

            vue.refresh();
            if (model.isBateauAt(new Coordonnee(y, x))) {
                vue.getButtonAt(y, x).setIcon(vue.getExplosion());

            }
            return;
        }
        System.out.println("Ne peut plus jouer");
    }


}