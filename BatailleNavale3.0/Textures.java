package BatailleNavale;

/**
 * Created by fabie_000 on 03/06/2017.
 */
public enum Textures {

    // BOUTONS
    RETOUR("Textures/boutons/buttonback.png"),
    JOUER("Textures/boutons/buttonplay.png"),
    QUITTER("Textures/boutons/buttonquit.png"),
    REGLES("Textures/boutons/buttonrules.png"),
    FINTOUR("Textures/boutons/buttondone.png"),
    EYE ("Textures/boutons/buttoneye.png"),
    MENU ("Textures/boutons/buttonmenu.png"),
    NOUVEAU ("Textures/boutons/buttonnouveau.png"),
    REPRENDRE ("Textures/boutons/buttonreprendre.png"),
    UNJOUEUR ("Textures/boutons/button1joueur.png"),
    DEUXJOUEUR ("Textures/boutons/button2joueurs.png"),

    // ICONE & REPONSES
    ICONE ("Textures/icone/icone.png"),
    AFFICHAGETOUCHE ("Textures/icone/touche.gif"),
    AFFICHAGECOULE ("Textures/icone/coule.gif"),
    AFFICHAGERATE ("Textures/icone/rate.gif"),
    AFFICHAGEVICTOIRE("Textures/icone/victoire.gif"),
    AFFICHAGEDEFAITE("Textures/icone/defaite.gif"),

    // FONDS D'ECRANS
    TRANSITIONJ1 ("Textures/backgrounds/redtransition.gif"),
    TRANSITIONJ2 ("Textures/backgrounds/bluetransition.gif"),
    BACKGROUNDJEU("Textures/backgrounds/gamebackground.png"),
    BACKGROUNDBATEAUX ("Textures/backgrounds/gamebackgroundbateaux.gif"),
    BACKGROUNDRULES ("Textures/backgrounds/rules.png"),
    BACKGROUNDPOPUP ("Textures/backgrounds/backgroundpopup.gif"),
    TITLESCREEN ("Textures/backgrounds/titlescreenFinal.png"),

    // CASES DE JEU
    BATEAUTOUCHE("Textures/cases/BOOM.gif"),
    BATEAUDETRUT("Textures/cases/naviredétruit.png"),
    BATEAU ("Textures/cases/navire.png"),
    BATEAUSELECTIONNE ("Textures/cases/navireSelectionne.gif"),
    BATEAUCOULE("Textures/cases/navirecoulé.gif"),
    INCONNU ("Textures/cases/inconnu.png"),
    VIDE ("Textures/cases/vide.png");

    private String path;

    Textures(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Textures{" +
                "path='" + path + '\'' +
                '}';
    }

    public String getPath() {
        return "C:/Users/fabie_000/Desktop/Travail/Projet_Tut/BatailleNavale/"+path;
    } //Chemin à modifier pour l'adapté à votre pc...
}
