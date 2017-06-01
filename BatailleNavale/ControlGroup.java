package BatailleNavale;

public class ControlGroup {

    private Model model;
    private Vue vue;
    public ControlButtonGame controlBoutonGame;
    public ControlButton controlBouton;


    public ControlGroup(Model model) {

        this.model = model;

        vue = new Vue(model);

        controlBoutonGame = new ControlButtonGame(model, vue);
        controlBouton = new ControlButton(model, vue);


        vue.display();
    }
}
