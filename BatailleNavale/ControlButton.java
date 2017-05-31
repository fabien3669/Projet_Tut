package BatailleNavale;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.awt.event.*;

public class ControlButton extends Control implements ActionListener
{

    public ControlButton(Model model, Vue vue) {
        super(model, vue);
        vue.setButtonsControl(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(vue.getTourFini())) {

            // CODE ICI
            System.out.println("Tour fini");

            return;
        }

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

        // CODE ICI

        System.out.println("x = "+x+", y = "+y);
    }


}
