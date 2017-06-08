package BatailleNavale;

/**
 * Created by fabie_000 on 03/06/2017.
 */
public enum Textures {
    BATEAUTOUCHE("Textures/cases/BOOM.gif"),
    BATEAUDETRUT("Textures/cases/naviredétruit.gif"),
    BATEAU ("Textures/cases/navire.gif"),
    BATEAUSELECTIONNE ("Textures/cases/navireSelectionne.gif"),
    BATEAUCOULE("Textures/cases/navirecoulé.gif"),
    INCONNU ("Textures/cases/inconnu.gif"),
    VIDE ("Textures/cases/vide.gif"),
    BACK ("Textures/boutons/buttonback2.gif"),
    PLAY ("Textures/boutons/buttonplay.gif"),
    QUIT ("Textures/boutons/buttonquit.gif"),
    RULES ("Textures/boutons/buttonrules.gif"),
    DONE ("Textures/boutons/buttondone.gif"),
    EYE ("Textures/boutons/buttoneye.gif"),
    MENU ("Textures/boutons/buttonmenu.gif"),
    NOUVEAU ("Textures/boutons/buttonnouveau.gif"),
    REPRENDRE ("Textures/boutons/buttonreprendre.gif"),
    TRANSITIONJ1 ("Textures/backgrounds/redtransition.gif"),
    TRANSITIONJ2 ("Textures/backgrounds/bluetransition.gif"),
    BACKGROUNDJEU("Textures/backgrounds/gamebackground.png"),
    BACKGROUNDBATEAUX ("Textures/backgrounds/gamebackgroundbateaux.gif"),
    BACKGROUNDRULES ("Textures/backgrounds/rules.png"),
    BACKGROUNDPOPUP ("Textures/backgrounds/backgroundpopup.gif"),
    TITLESCREEN ("Textures/backgrounds/titlescreenFinal.png"),
    ICONE ("Textures/icone/icone2.png"),
    AFFICHAGETOUCHE ("Textures/icone/touche.gif"),
    AFFICHAGECOULE ("Textures/icone/coule.gif"),
    AFFICHAGERATE ("Textures/icone/rate.gif"),
    AFFICHAGETERMINE ("Textures/icone/termine.gif"),

    ;

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
        return path;
    }
}
