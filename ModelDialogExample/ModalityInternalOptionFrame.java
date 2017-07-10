package com.webbyit.swing;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * Wraps <code>JOptionPane</code> so that the modality is controlled.
 * for
 * 
 * @author webbyit
 */
public class ModalityInternalOptionFrame extends ModalityInternalFrame {

    public ModalityInternalOptionFrame(final JComponent parentComponent, final JOptionPane optionPane,
                                       String title) {

        super(parentComponent, title, true, false, false, false);

        optionPane.setValue(null); // default to null

        if (desktopPane == null && (parentComponent == null || (parentComponent.getParent() == null))) {
            throw new RuntimeException("ModalityInternalOptionPane: parentComponent does not have a valid parent");
        }

        // overide default layout
        getContentPane().setLayout(new BorderLayout());

        // Add pane to frame
        getContentPane().add(optionPane, BorderLayout.CENTER);

        final ModalityInternalOptionFrame frame = this;

        // Add listener for value changes
        optionPane.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent event) {
                // Let the defaultCloseOperation handle the closing
                // if the user closed the iframe without selecting a button
                // (newValue = null in that case).  Otherwise, close the dialog.
                if (frame.isVisible() && event.getSource() == optionPane && event.getPropertyName().equals(
                        JOptionPane.VALUE_PROPERTY)) {
                    try {
                        frame.setClosed(true);
                    } catch (PropertyVetoException ex) {
                        // Well its an exception but in reality its no exceptional behaviour!
                    }
                    frame.setVisible(false);
                }
            }
        });
    }
}
