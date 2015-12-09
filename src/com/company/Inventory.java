package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 5/6/15.
 */
public class Inventory extends JPanel {
    private JPanel tabInventoryPanel;
    private JTextField textFieldRecordTitle;
    private JTextField textFieldArtistName;
    private JTextField textFieldYear;
    private JTextField textField4;
    private JLabel recordTitleLabel;
    private JLabel artistNameLabel;
    private JLabel dateRecievedLabel;
    private JButton quitButton;


    public Inventory() {

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public JPanel getPanel() {
        return tabInventoryPanel;
    }
}
