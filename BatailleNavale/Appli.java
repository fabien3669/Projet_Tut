package Version_MVC;

/**
 * Created by fabie_000 on 11/05/2017.
 */
public class Appli {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Model model = new Model();
                ControlGroup controler = new ControlGroup(model);
            }
        });
    }
}
