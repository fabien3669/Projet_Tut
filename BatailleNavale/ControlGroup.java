package BatailleNavale;

public class ControlGroup {

    private Model model;
    private Vue vue;
    public ControlButton controlBouton;


    public ControlGroup(Model model) {

        this.model = model;

        vue = new Vue(model);

        controlBouton = new ControlButton(model,vue);


        vue.display();
    }
}
