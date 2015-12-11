package com.glenda;


public class Main {

    public static void main(String[] args) throws Exception {

        if (DBManager.setup()) {
        } else {
            System.exit(-1);
        }

        if (!DBManager.loadAllConsignors()) {
            System.exit(-1);
        }


        Form form = new Form();
        //form has references (directly or indirectly) to all the GUI components.



    }
}
