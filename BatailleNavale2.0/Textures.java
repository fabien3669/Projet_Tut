package BatailleNavale;

/**
 * Created by fabie_000 on 03/06/2017.
 */
public enum Textures {
    TOUCHE ("Textures/cases/BOOM.gif"),
    DETRUIT ("Textures/cases/naviredétruit.gif"),
    BATEAU ("Textures/cases/navire.gif"),
    COULE ("Textures/cases/navirecoulé.gif"),
    INCONNU ("Textures/cases/inconnu.gif"),
    VIDE ("Textures/cases/vide.gif"),
    BACK ("Textures/boutons/buttonback2.gif"),
    PLAY ("Textures/boutons/buttonplay.gif"),
    QUIT ("Textures/boutons/buttonquit.gif"),
    RULES ("Textures/boutons/buttonrules.gif"),
    DONE ("Textures/boutons/buttondone.gif"),
    EYE ("Textures/boutons/buttoneye.gif"),
    TRANSITIONJ1 ("Textures/backgrounds/redtransition.gif"),
    TRANSITIONJ2 ("Textures/backgrounds/bluetransition.gif"),
    BACKGROUND ("Textures/backgrounds/gamebackground.png"),
    BACKGROUNDBATEAUX ("Textures/backgrounds/gamebackgroundbateaux.gif"),
    BACKGROUNDRULES ("Textures/backgrounds/rules.png"),
    TITLESCREEN ("Textures/backgrounds/titlescreenFinal.png"),
    ICONE ("Textures/icone/icone.png"),
    AFFICHAGETOUCHE ("Textures/icone/touche.png"),
    AFFICHAGECOULE ("Textures/icone/coule.png"),
    AFFICHAGERATE ("Textures/icone/rate.png"),
    AFFICHAGETERMINEJ1 ("Textures/icones/termine1.png"),
    AFFICHAGETERMINEJ2 ("Textures/icones/termine2.png"),
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
