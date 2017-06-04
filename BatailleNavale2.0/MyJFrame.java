package BatailleNavale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by fabie_000 on 04/06/2017.
 */
public class MyJFrame extends JDialog {
    private String string;
    private JButton but;

    public MyJFrame(JFrame parent){
        super(parent);
        setUndecorated(true);
        but = new JButton();
        but.setBorder(BorderFactory.createEmptyBorder());
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyJFrame.this.setVisible(false);
            }
        });
        setSize(300,152);
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public void setIcon(String icon) {
        this.string = icon;
        display();
    }

    private void display() {
        but.setIcon(new ImageIcon(string));
        setContentPane(but);
        setVisible(true);
    }
}
