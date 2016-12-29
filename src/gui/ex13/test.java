package gui.ex13;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class test extends Applet implements ActionListener {

PopupMenu popup;

public void init() {	    
        MenuItem mi;

    popup = new PopupMenu("Edit");

        mi = new MenuItem("Cut");
        mi.addActionListener(this);
    popup.add(mi);

        mi = new MenuItem("Copy");
        mi.addActionListener(this);
    popup.add(mi);

    popup.addSeparator();

        mi = new MenuItem("Paste");
        mi.addActionListener(this);
    popup.add(mi);

    add(popup); // add popup menu to applet
       
        enableEvents(AWTEvent.MOUSE_EVENT_MASK); 

    resize(200, 200);
    }

public void processMouseEvent(MouseEvent e) {

    if (e.isPopupTrigger()) { 
        popup.show(e.getComponent(), e.getX(), e.getY());
    }
    super.processMouseEvent(e);
    }

    public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

        if (command.equals("Cut")) {
        // perform cut operation
        } else if (command.equals("Copy")) {
            // perform copy operation
        } else if (command.equals("Paste")) {
            // perform paste operation
        }
    }
}
