package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * Created by admin on 5/6/15.
 */
public class Tab1 extends JPanel {
    private JCheckBox checkBox1;
    private JPanel tabOneJpanel;
    private JLabel lblWhatIsChecked;


    public Tab1() {
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox1.isSelected()) {
                    lblWhatIsChecked.setText("Checked!");
                } else {
                    lblWhatIsChecked.setText("Not checked");
                }
            }
        });
    }

    public JPanel getPanel() {
        return tabOneJpanel;
    }
}
