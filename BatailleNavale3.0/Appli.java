package BatailleNavale;

/**
 * Created by fhenriot on 03/04/17.
 */
public class Appli {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Model model = new Model();
                ControlGroup control = new ControlGroup(model);
            }
        });
    }
}