package BatailleNavale;

import java.util.Arrays;
import java.util.Random;

public class Model {
    public static final int JOUEUR_1=1;
    public static final int JOUEUR_2=2;
    public static final int JOUEUR_CONTRE_JOUEUR = 0;
    public static final int JOUEUR_CONTRE_IA = 1;
    private Bateau[] joueur1, joueur2;
    private Coordonnee[] tir1, tir2;
    private int indiceDernierCoupJoueur1,indiceDernierCoupJoueur2, joueurEnCours;
    private boolean placementBateau, aJoue, isBateauSelected;
    private Bateau bateauOnMoving;
    private String regles;
    private int modeDeJeu;
    private boolean bateauTouche;
    private boolean directionConnue;

    public Model() {
        setRegles();
    }

    // Getters & Setters

    public void setRegles() {
        this.regles = "<html>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Bonjour Capitaine ! Bienvenu à bord de votre nouveau navire !<br><br>\n" +
                "Mais nous n’avons pas le temps de tergiverser, l’ennemi commence déjà à réfléchir à une stratégie, vous devriez sérieusement vous y mettre aussi !\n" +
                "\n" +
                "Voici comment procéder :<br>\n" +
                "&emsp;Cliquez « JOUER » puis choisissez entre combattre un ami ou combattre une intelligence artificielle.<br><br>\n" +
                "\n" +
                "Vous devrez ensuite placer sur la carte qui apparaîtra tout notre bataillon. Il est composé de :<br>\n" +
                "&emsp;- 1 porte-avions de 5 cases de long<br>\n" +
                "&emsp;- 1 curiassé de 4 cases de long<br>\n" +
                "&emsp;- 2 vengeurs de 3 cases de long<br>\n" +
                "&emsp;- 1 Typhon de 2 cases de long<br><br>\n" +
                "\n" +
                "Cliquez sur la flèche verte une fois vos troupes placées. Il vous suffira ensuite de cliquer sur la case ou vous souhaiter frapper.\n" +
                "5 boutons vous informeront de l'état des bateaux adverses, et on vous avertira, sur le bas de l'écran, du dernier coup de l'adversaire.<br>\n" +
                "Une fois votre tour terminé, cliquez à nouveau sur la flèche verte.<br>\n" +
                "Les bateaux touchés ne coulent que si tout le bateau a été entièrement anéantit. Votre but est de détruite toute la flotte ennemie avant que la vôtre de soi réduite en poussière.<br>\n" +
                "\n" +
                "Vous pouvez déserter à tout moment, en cliquant sur le bouton rouge en haut à gauche de l'écran, puis soit reprendre la partie en cours, soit commencer une nouvelle partie. Attention toutefois, vos marins sont très rancuniers, et si vous quittez la bataille (l'application), il ne sera plus possible de continuer l'aventure avec eux, vous devrez donc commencer une nouvelle partie.<br><br>\n" +
                "\n" +
                "&emsp;&emsp;&emsp;Bon courage Capitaine !</html>";
        /*this.regles = "<html> Bataille navale : Règles <br><br>" +
                "La bataille navale oppose deux joueurs dont la flotte est composée de 5 bateaux :<br>" +
                "  - Un porte avion (5 cases)<br>" +
                "  - Un croiseur (4 cases)<br>" +
                "  - Un contre-torpilleur (3 cases)<br>" +
                "  - Un sous marin (3 cases)<br>" +
                "  - Un torpilleur (2 cases)<br>" +
                "Ces 5 bateaux sont placés dans une grille de 10x10 cases, numérotée de 1 à 10<br>" +
                "verticalement, et de A à J horizontalement. Le but du jeu pour les joueurs est de<br>" +
                "couler tous les navires adverses.<br>" +
                "A chaque tour, le joueur peut choisir une case où tirer. Si un bateau adverse est<br>" +
                "touché, le joueur peut rejouer. </html>";*/
    }

    public void setaJoue(boolean aJoue) {
        this.aJoue = aJoue;
    }

    public boolean isaJoue() {
        return aJoue;
    }

    public void setPlacementBateau(boolean placementBateau) {
        this.placementBateau = placementBateau;
    }

    public boolean isPlacementBateau() {
        return placementBateau;
    }

    public Bateau[] getJoueur1() {
        return joueur1;
    }

    public Bateau[] getJoueur2() {
        return joueur2;
    }

    public Coordonnee[] getTir1() {
        return tir1;
    }

    public Coordonnee[] getTir2() {
        return tir2;
    }

    public int getIndiceDernierCoupJoueur1() {
        return indiceDernierCoupJoueur1;
    }

    public int getIndiceDernierCoupJoueur2() {
        return indiceDernierCoupJoueur2;
    }

    public String getRegles() {
        return regles;
    }

    public int getJoueurEnCours() {
        return joueurEnCours;
    }

    public void changeJoueurEnCours(){
        if (joueurEnCours==JOUEUR_1){
            joueurEnCours=JOUEUR_2;
        }
        else{
            joueurEnCours=JOUEUR_1;
        }
    }

    public boolean isBateauSelected() {
        return isBateauSelected;
    }

    public void setBateauSelected(boolean bateauSelected) {
        isBateauSelected = bateauSelected;
    }

    public Bateau getBateauOnMoving() {
        return bateauOnMoving;
    }

    public void setBateauOnMoving(Bateau bateauOnMoving) {
        this.bateauOnMoving = bateauOnMoving;
    }

    public void setModeDeJeu(int modeDeJeu) {
        this.modeDeJeu = modeDeJeu;
    }

    public int getModeDeJeu() {
        return modeDeJeu;
    }

    // UPDATERS
    public void initModel(){
        joueur1 = new Bateau[]{
                new Bateau(5), new Bateau(4), new Bateau(3), new Bateau(3), new Bateau(2)
        };
        joueur2 = new Bateau[]{
                new Bateau(5), new Bateau(4), new Bateau(3), new Bateau(3), new Bateau(2)
        };
        placeBateau();
        tir1 = new Coordonnee[100];
        tir2 = new Coordonnee[100];
        indiceDernierCoupJoueur2=0;
        indiceDernierCoupJoueur1=0;
        joueurEnCours=JOUEUR_1;
        placementBateau=true;
        isBateauSelected=false;
        aJoue=false;
        bateauTouche=false;
        directionConnue=false;
    }

    public void updateBateau(Bateau b){
        switch (b.getState()){
            case "SAIN":
                b.incrementeCaseTouché();
                b.setState(Etat.TOUCHE);
                break;
            case "BATEAUTOUCHE":
                b.incrementeCaseTouché();
                if (b.getCaseTouché()==b.getTaille()){
                    b.setState(Etat.COULE);
                }
                break;
        }
    }

    public void update(){
        if (isPlacementBateau()){
            this.bateauOnMoving=null;
            this.isBateauSelected=false;
            if (joueurEnCours==JOUEUR_2 || modeDeJeu==JOUEUR_CONTRE_IA){
                setPlacementBateau(false);
            }
        }
        aJoue=false;
        if (modeDeJeu==JOUEUR_CONTRE_JOUEUR){
            changeJoueurEnCours();
        }
    }


    //Placement Bateaux
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

    public boolean placementOk() {
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
                        if (c1.equals(c2) || invalideCoord(c1) || invalideCoord(c2)) {
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

    private boolean invalideCoord(Coordonnee c2) {
        if (c2.getLigne()<0 || c2.getLigne()>9 || c2.getColonne()<0 || c2.getColonne()>9){
            return true;
        }
        return false;
    }


    // Methode de jeu
    public boolean isCoupValide(Coordonnee xy){
        if (xy==null){
            return false;
        }
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

    public boolean isBateauAt(Coordonnee xy) {
        for (int i = 0; i < joueur2.length; i++) {
            Coordonnee[] tab;
            if (joueurEnCours == JOUEUR_1) {
                if (isPlacementBateau()) {
                    tab = joueur1[i].getPosition();
                } else {
                    tab = joueur2[i].getPosition();
                }
            } else {
                if (isPlacementBateau()) {
                    tab = joueur2[i].getPosition();
                } else {
                    tab = joueur1[i].getPosition();
                }
            }
            for (int j = 0; j < tab.length; j++) {
                if (tab[j] != null) {
                    if (tab[j].equals(xy)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Bateau getBateauAt(Coordonnee c) {
        for (int i = 0; i < joueur1.length; i++) {
            Bateau b;
            if (joueurEnCours==JOUEUR_1){
                if (isPlacementBateau()){
                    b=joueur1[i];
                }
                else{
                    b = joueur2[i];
                }
            }
            else {
                if (isPlacementBateau()){
                    b=joueur2[i];
                }
                else{
                    b = joueur1[i];
                }
            }
            if (c.isDansTableau(b.getPosition())){
                return b;
            }
        }
        return null;
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


    // Methodes pour IA

    public void joueTir(Coordonnee coordonnee){
        if (modeDeJeu==JOUEUR_CONTRE_IA && joueurEnCours==JOUEUR_2){
            if (isBateauAt(coordonnee)){
                Bateau b = getBateauAt(coordonnee);
                updateBateau(b);
                bateauTouche=true;
                if (b.getState().equals(Etat.TOUCHE.getState()) && b.getCaseTouché()>1){
                    directionConnue=true;
                }
                else{
                    directionConnue=false;
                }
                if (b.getState().equals(Etat.COULE.getState())){
                    bateauTouche=false;
                    for (int i = 0; i < joueur1.length; i++) {
                        if (joueur1[i].getState().equals(Etat.TOUCHE.getState())){
                            bateauTouche = true;
                        }
                    }
                    directionConnue=false;
                }
            }
        }
        if (joueurEnCours==JOUEUR_1){
            tir1[indiceDernierCoupJoueur1]=coordonnee;
            indiceDernierCoupJoueur1++;
        }
        else{
            tir2[indiceDernierCoupJoueur2]=coordonnee;
            indiceDernierCoupJoueur2++;
        }
    }

    public void joueIA() { //ok
        if (bateauTouche){
            Coordonnee last = dernierCoupReussi();
            if (directionConnue){
                joueCoup(last);
            }
            else{
                coupAutour(last);
            }
        }
        else{
            coupAleatoire();
        }
    }

    private void joueCoup(Coordonnee last) {
        Coordonnee autre = autreCoordonneeTouche(last);
        int diffL = 0;
        int diffC = 0;
        if (last.getLigne()>autre.getLigne()){
            diffL=1;
        }
        else if (last.getLigne()<autre.getLigne()){
            diffL=-1;
        }
        else{
            diffL=0;
            if (last.getColonne()>autre.getColonne()){
                diffC=1;
            }
            else{
                diffC=-1;
            }
        }
        if (last.getLigne()+diffL>9){
            diffL =-1;
        }
        else if (last.getLigne()+diffL<0){
            diffL=1;
        }
        if (last.getColonne()+diffC>9){
            diffC =-1;
        }
        else if (last.getColonne()+diffC<0){
            diffC=1;
        }
        if (!isBateauAt(tir2[indiceDernierCoupJoueur2-1])){
            diffL = -diffL;
            diffC = -diffC;
        }
        Coordonnee coup = new Coordonnee(last.getLigne()+diffL, last.getColonne()+diffC);
        while (!isCoupValide(coup)){
            if (diffL>0){
                diffL++;
            }
            else if (diffL<0){
                diffL--;
            }
            else{
                if (diffC>0){
                    diffC++;
                }
                else{
                    diffC--;
                }
            }
            if (last.getLigne()+diffL>9){
                diffL =-1;
            }
            else if (last.getLigne()+diffL<0){
                diffL=1;
            }
            if (last.getColonne()+diffC>9){
                diffC =-1;
            }
            else if (last.getColonne()+diffC<0){
                diffC=1;
            }
            coup = new Coordonnee(last.getLigne()+diffL, last.getColonne()+diffC);
        }
        joueTir(coup);
    }

    private Coordonnee autreCoordonneeTouche(Coordonnee last) {
        Bateau b = getBateauAt(last);
        for (int i = 0; i < b.getPosition().length; i++) {
            if (b.getPosition()[i].isDansTableau(tir2) && !b.getPosition()[i].equals(last)){
                return b.getPosition()[i];
            }
        }
        return null;
    }

    private void coupAutour(Coordonnee last) {
        Random rd = new Random();
        int dl =-1;
        int dc = -1;
        Coordonnee coordonnee = new Coordonnee(-1, -1);
        while (!isCoupValide(coordonnee) || invalideCoord(coordonnee)){
            dl = rd.nextInt(3)-1;
            if (dl==0){
                dc = rd.nextInt(3)-1;
                while (dc==0){
                    dc = rd.nextInt(3)-1;
                }
            }
            else{
                dc=0;
            }
            coordonnee = new Coordonnee(last.getLigne()+dl, last.getColonne()+dc);
        }
        joueTir(coordonnee);
    }

    private Coordonnee dernierCoupReussi() {
        for (int i = indiceDernierCoupJoueur2-1 ; i >= 0; i--) {
            if (isBateauAt(tir2[i]) && getBateauAt(tir2[i]).getState().equals(Etat.TOUCHE.getState())){
                return tir2[i];
            }
        }
        return null;
    }

    private void coupAleatoire() {
        Random rd = new Random();
        int l = rd.nextInt(10);
        int c = rd.nextInt(10);
        Coordonnee coordonnee = new Coordonnee(l, c);
        while (!isCoupValide(coordonnee)){
            l = rd.nextInt(10);
            c = rd.nextInt(10);
            coordonnee = new Coordonnee(l, c);
        }
        joueTir(coordonnee);
    }
}

enum Etat{
    SAIN ("SAIN"),
    TOUCHE ("BATEAUTOUCHE"),
    COULE ("BATEAUCOULE");

    private String state;

    Etat(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Bateau{
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

    public String getState() {
        return state.getState();
    }

    public void setState(Etat state) {
        this.state = state;
    }

    public Coordonnee[] getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bateau bateau = (Bateau) o;

        if (getTaille() != bateau.getTaille()) return false;
        if (getCaseTouché() != bateau.getCaseTouché()) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getPosition(), bateau.getPosition())) return false;
        return getState() == bateau.getState();
    }

    @Override
    public String toString() {
        return "Bateau{" +
                "taille=" + taille +
                ", caseTouché=" + caseTouché +
                ", position=" + Arrays.toString(position) +
                ", state=" + state +
                '}';
    }
}

class Coordonnee {
    private int ligne;
    private int colonne;

    public Coordonnee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordonnee that = (Coordonnee) o;

        if (getLigne() != that.getLigne()) return false;
        return getColonne() == that.getColonne();
    }

    public boolean isDansTableau(Coordonnee[] coordonnees){
        for (int i = 0; i < coordonnees.length; i++) {
            if (coordonnees[i]!=null){
                if (coordonnees[i].equals(this)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Coordonnee{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                '}';
    }
}