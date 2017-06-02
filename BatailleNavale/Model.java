package BatailleNavale;

import java.util.Random;

/**
 * Created by fabie_000 on 16/05/2017.
 */
public class Model {
    public static final int JOUEUR_1=1;
    public static final int JOUEUR_2=2;
    private Bateau[] joueur1, joueur2;
    private Coordonnee[] tir1, tir2;
    private int indiceDernierCoupJoueur1,indiceDernierCoupJoueur2;
    private int joueurEnCours;
    private boolean placementBateau;
    private boolean aJoue;

    public Model() {
        joueur1 = new Bateau[]{
                new Bateau(5), new Bateau(4), new Bateau(3), new Bateau(3), new Bateau(2)
        };
        tir1 = new Coordonnee[100];
        tir2 = new Coordonnee[100];
        joueur2 = new Bateau[]{
                new Bateau(5), new Bateau(4), new Bateau(3), new Bateau(3), new Bateau(2)
        };


        for (int i = 0; i < joueur1.length; i++) {
            placeBateau(joueur1[i]);
            placeBateau(joueur2[i]);
        }
        indiceDernierCoupJoueur1=0;
        indiceDernierCoupJoueur2=0;
        joueurEnCours =2;
        placementBateau=false;
    }

    public void setaJoue(boolean aJoue) { this.aJoue = aJoue; }

    public boolean getaJoue() { return aJoue; }

    public boolean isPlacementBateau() {
        return placementBateau;
    }

    public void setPlacementBateau(boolean placementBateau) {
        this.placementBateau = placementBateau;
    }

    public Bateau[] getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Bateau[] joueur1) {
        this.joueur1 = joueur1;
    }

    public Bateau[] getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Bateau[] joueur2) {
        this.joueur2 = joueur2;
    }

    public Coordonnee[] getTir1() {
        return tir1;
    }

    public void setTir1(Coordonnee[] tir1) {
        this.tir1 = tir1;
    }

    public Coordonnee[] getTir2() {
        return tir2;
    }

    public void setTir2(Coordonnee[] tir2) {
        this.tir2 = tir2;
    }

    public int getIndiceDernierCoupJoueur1() {
        return indiceDernierCoupJoueur1;
    }

    public void setIndiceDernierCoupJoueur1(int indiceDernierCoupJoueur1) {
        this.indiceDernierCoupJoueur1 = indiceDernierCoupJoueur1;
    }

    public int getIndiceDernierCoupJoueur2() {
        return indiceDernierCoupJoueur2;
    }

    public void setIndiceDernierCoupJoueur2(int indiceDernierCoupJoueur2) {
        this.indiceDernierCoupJoueur2 = indiceDernierCoupJoueur2;
    }

    public int getJoueurEnCours() {
        return joueurEnCours;
    }

    public void setJoueurEnCours(int joueurEnCours) {
        this.joueurEnCours = joueurEnCours;
    }

    public void changeJoueurEnCours() {
        if (this.joueurEnCours == JOUEUR_1) {
            joueurEnCours = JOUEUR_2;
        } else { joueurEnCours = JOUEUR_1; }
    }

    public boolean isCoupValide(Coordonnee xy){
        switch (joueurEnCours){
            case 1:
                for (int i = 0; i < indiceDernierCoupJoueur1; i++) {
                    if (tir1[i].equals(xy)){
                        return false;
                    }
                }
                return true;
            case 2:
                for (int i = 0; i < indiceDernierCoupJoueur2; i++) {
                    if (tir2[i].equals(xy)){
                        return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean isBateauAt(Coordonnee xy){
        for (int i = 0; i < joueur2.length; i++) {
            Coordonnee[] tab;
            if (joueurEnCours==JOUEUR_1){
                if (isPlacementBateau()){
                    tab = joueur1[i].getPosition();
                }
                else{
                    tab = joueur2[i].getPosition();
                }
            }
            else{
                if (isPlacementBateau()){
                    tab = joueur2[i].getPosition();
                }
                else{
                    tab = joueur1[i].getPosition();
                }
            }
            for (int j = 0; j < tab.length; j++) {
                if (tab[j]!=null){
                    if (tab[j].equals(xy)){
                        if (!isPlacementBateau()){
                            updateBateau(i);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void placeBateau() {
        int choixSens1;
        int choixSens2;
        int l;
        int c;
        Random rd = new Random();
        while (!placementOk()){
            for (int i = 0; i < joueur1.length*2; i++) {
                Coordonnee[] tab;
                if (i<joueur1.length){
                    tab = joueur1[i].getPosition();
                }
                else{
                    tab = joueur2[i-5].getPosition();
                }
                choixSens1 = rd.nextInt(2); // 0 pour horizontal, 1 pour vertical
                choixSens2=rd.nextInt(2); // 0 pour gauche ou haut, 1 pour droite ou bas
                l=rd.nextInt(10);
                c=rd.nextInt(10);
                tab[0] = new Coordonnee(l, c);
                int dl=2;
                int dc=2;
                switch (choixSens1){
                    case 0:
                        dl = 0;
                        break;
                    case 1:
                        dc=0;
                        break;
                }
                switch (choixSens2){
                    case 0:
                        if (dl==0){
                            dc=-1;
                        }
                        else {
                            dl=-1;
                        }
                        break;
                    case 1:
                        if (dl==0){
                            dc=1;
                        }
                        else {
                            dl=1;
                        }
                        break;
                }
                int caseMise = 1;
                while (caseMise!=tab.length){
                    if (l+dl <0 || l+dl > 9){
                        if (dl<0){
                            dl=1;
                        }
                        else {
                            dl=-1;
                        }
                    }
                    if (c+dc < 0 || c+dc > 9){
                        if (dc<0){
                            dc=1;
                        }
                        else {
                            dc=-1;
                        }
                    }
                    tab[caseMise] = new Coordonnee(l+dl, c+dc);
                    caseMise++;
                    if (dl>0){
                        dl++;
                    }
                    else if (dl<0){
                        dl--;
                    }
                    if (dc>0){
                        dc++;
                    }
                    else if (dc<0){
                        dc--;
                    }
                }
            }
        }
    }

    private boolean placementOk() {
        if (joueur1[0].getPosition()[0]==null){
            return false;
        }
        for (int i = 0; i < joueur1.length; i++) {
            for (int j = i+1; j < joueur1.length; j++) {
                Coordonnee[] tabj11 = joueur1[i].getPosition();
                Coordonnee[] tabj12 = joueur1[j].getPosition();
                Coordonnee[] tabj21 = joueur2[i].getPosition();
                Coordonnee[] tabj22 = joueur2[j].getPosition();
                for (Coordonnee c1 : tabj11) {
                    for (Coordonnee c2 : tabj12) {
                        if (c1.equals(c2)) {
                            return false;
                        }
                    }
                }
                for (Coordonnee c1 : tabj21) {
                    for (Coordonnee c2 : tabj22) {
                        if (c1.equals(c2)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void updateBateau(int i){
        Bateau b;
        if (joueurEnCours==JOUEUR_1){
            b = joueur2[i];
        }
        else {
            b = joueur1[i];
        }
        switch (b.getState()){
            case SAIN:
                b.incrementeCaseTouché();
                b.setState(Bateau.Etat.TOUCHE);
                break;
            case TOUCHE:
                b.incrementeCaseTouché();
                if (b.getCaseTouché()==b.getTaille()){
                    b.setState(Bateau.Etat.COULE);
                }
                break;
        }
        if (joueurEnCours==JOUEUR_1){
            joueur2[i]=b;
        }
        else {
            joueur1[i]=b;
        }
    }

    public boolean partieTerminee(){
        for (int i = 0; i < this.joueur1.length; i++) {
            Bateau b = null;
            if (this.joueurEnCours==JOUEUR_1){
                b = this.joueur2[i];
            }
            else{
                b = this.joueur1[i];
            }
            if (b.getTaille()!=b.getCaseTouché()){
                return false;
            }
        }
        return true;
    }
}

class Bateau{
    public enum Etat {SAIN, TOUCHE, COULE}
    private int taille;
    private int caseTouché;
    private Coordonnee[] position;
    private Etat state;

    public Bateau(int taille) {
        this.taille = taille;
        this.caseTouché=0;
        this.position = new Coordonnee[this.taille];
        this.state = Etat.SAIN;
    }

    public int getTaille() {
        return taille;
    }

    public int getCaseTouché() {
        return caseTouché;
    }

    public void incrementeCaseTouché() {
        this.caseTouché++;
    }

    public void setCaseTouché(int nb) {
        this.caseTouché=nb;
    }

    public Etat getState() {
        return state;
    }

    public void setState(Etat state) {
        this.state = state;
    }

    public Coordonnee[] getPosition() {
        return position;
    }

    public void setPosition(Coordonnee[] position) {
        this.position = position;
    }

    public void displayCoord(){
        for (int i = 0; i < position.length; i++) {
            System.out.println(position[i]);
        }
    }


}
