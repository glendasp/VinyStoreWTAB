package com.glenda;

import javax.swing.*;

/**
 * Created by admin on 5/6/15.
 */
public class Form extends JFrame {
    private JPanel rootPanel;

    //Not created in GUI designer
    private JTabbedPane tabbedPane;


    //Note that this fails with a NullPointer if the default layoutmanager (GridLayoutManager) for this form is used
    //Since all it does is hold the JTabbedPane, set the layout manager to something (probably anything) else).

    public Form() throws Exception {
        setContentPane(rootPanel);

        //Create a a JTabbedPanel, add to JPanel, add tabs to JTabbedPane.
        tabbedPane = new JTabbedPane();
        rootPanel.add(tabbedPane);
        setTitle(" Database Application");
        tabbedPane.add("Consignors", new Consignors(DBManager.consignorModel).getPanel());
        tabbedPane.add("Inventory", new Inventory(DBManager.inventoryModel).getPanel());
        tabbedPane.add("Sales", new Sales().getPanel());
        setVisible(true);
        pack();


    }


}
