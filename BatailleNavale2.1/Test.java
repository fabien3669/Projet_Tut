package BatailleNavale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by fabie_000 on 06/06/2017.
 */
public class Test {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        final JButton bouton;

        bouton = new JButton("Ne me faites pas de mal.");
        jFrame.add(bouton);
        jFrame.pack();
        jFrame.setVisible(true);
        bouton.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
               // bouton.setText("Aaargh...");
                System.out.println("yolo");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bouton.setText("Non pas moi !");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bouton.setText("Ouf...");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                bouton.setText("Pitié !!!");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bouton.setText("Sauvé par le gong !");
            }
        });
    }
}
