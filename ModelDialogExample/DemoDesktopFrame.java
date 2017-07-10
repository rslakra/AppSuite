package com.webbyit.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Demo desktop
 *
 * @author webbit
 */
public class DemoDesktopFrame extends javax.swing.JFrame implements ActionListener {

    private static String NIMBUS_CLASS = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    private LookAndFeelInfo[] lafInfo;
//    private static IncidentInternalFrame incidentFrame;
    private static int frameCount = 0;

    enum TileType {

        TILE_HORIZONTAL, TILE_VERTICAL, TILE_CASCADE, TILE_SQUARE
    };

    /** Creates new form DgcDesktopFrame */
    public DemoDesktopFrame() {
        initComponents();
        getLookAndFeelInfo();
    }

    public void newFrame() {
        JInternalFrame newFrame = new DemoModalityFrame(desktopPane, "Frame " + ++frameCount,
                true, true, true, true);
        Dimension dim = newFrame.getPreferredSize();

        desktopPane.add(newFrame);
        newFrame.show();

    }

    public void newDialogFrame() {
        JOptionPane pane = new JOptionPane("A test of a none modal dialog", JOptionPane.ERROR_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        JInternalFrame newFrame = new ModalityInternalOptionFrame(desktopPane, pane, "Frame Modal Error Dialog");

        desktopPane.add(newFrame);
        newFrame.show();

    }

    private void getLookAndFeelInfo() {

        lafInfo = UIManager.getInstalledLookAndFeels();
        String defaultLaf = UIManager.getLookAndFeel().getName();

        // default to using nimbus if its available
        boolean nimbus = false;
        for (int i = 0; i < lafInfo.length; i++) {
            if (lafInfo[i].getClassName().equals(NIMBUS_CLASS)) {
                setLAF(lafInfo[i].getClassName());
                nimbus = true;
                break;
            }
        }
        for (int i = 0; i < lafInfo.length; i++) {
            LAFMenuItem menuItem = new LAFMenuItem(lafInfo[i].getName(), lafInfo[i].getClassName());
            if (nimbus) {
                menuItem.setSelected(lafInfo[i].getClassName().equals(NIMBUS_CLASS));
            } else {
                menuItem.setSelected(lafInfo[i].getName().equals(defaultLaf));
            }
            menuItem.addActionListener(this);
            lafMenu.add(menuItem);
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof LAFMenuItem) {
            // look and feel menu item selected
            LAFMenuItem menuItem = (LAFMenuItem) e.getSource();
            Component[] menuComponents = lafMenu.getMenuComponents();
            // clear selection from all menu items
            if (menuComponents != null && menuComponents.length > 0) {
                for (Component menuComponent : menuComponents) {
                    ((LAFMenuItem) menuComponent).setSelected(false);
                }
            }
            menuItem.setSelected(true);
            setLAF(menuItem.getLAFClassName());
        }
    }

    private void setLAF(String lafName) {
        try {
            UIManager.setLookAndFeel(lafName);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            System.err.println("Could not load " + lafName);
        }
    }

    private void deiconifyInternalFrames() {
        final int numComps = desktopPane.getComponentCount();
        int count = 0;
        while (count < numComps) {
            final Component component = desktopPane.getComponent(count);
            if (component instanceof JDesktopIcon) {
                try {
                    ((JDesktopIcon) component).getInternalFrame().setIcon(false);
                    count = 0;
                } catch (PropertyVetoException e) {
                    // What to do !
                } finally {
                    count++;
                }
            } else {
                count++;
            }
        }
    }

    /**
     * Layout frames
     * 
     * @param format
     */
    private void arrangeCurrentWindow(TileType format) {

        /*
         * get the component count at the xc frame layer as only interest in
         * xcFrame nothing else
         */
        final JInternalFrame[] frames = desktopPane.getAllFramesInLayer(JLayeredPane.DEFAULT_LAYER);

        final Dimension dim = desktopPane.getSize();

        deiconifyInternalFrames();

        switch (format) {
            case TILE_HORIZONTAL: {
                int vertSize = dim.height / frames.length;
                for (int i = 0; i < frames.length; i++) {
                    frames[i].setSize(new Dimension(dim.width, vertSize));
                    frames[i].setLocation(0, i * vertSize);
                }
                break;
            }
            case TILE_VERTICAL: {
                int horizSize = dim.width / frames.length;
                for (int i = 0; i < frames.length; i++) {
                    frames[i].setSize(new Dimension(horizSize, dim.height));
                    frames[i].setLocation(i * horizSize, 0);
                }
                break;
            }
            case TILE_CASCADE: {
                for (int i = 0; i < frames.length; i++) {
                    frames[i].setSize(new Dimension(dim.height / 2, dim.height / 2));
                    frames[i].setLocation(40 * i, 40 * i);
                }
                break;
            }
            case TILE_SQUARE: {
                int hCount = frames.length / 2 + 1;
                if (hCount >= 3) {
                    hCount = 2;
                }
                int vCount = 1;
                if (frames.length > 2) {
                    vCount = 2;
                }
                int vertSize = dim.height / vCount;
                int horizSize = dim.width / hCount;

                for (int i = 0; i < vCount; i++) {
                    for (int j = 0; j < hCount; j++) {
                        if (i * 2 + j >= frames.length) {
                            break;
                        }
                        JInternalFrame frame = frames[i * 2 + j];
                        frame.setSize(new Dimension(horizSize, vertSize));
                        frame.setLocation(j * horizSize, i * vertSize);
                    }
                }
            }
        }
        desktopPane.validate();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMI = new javax.swing.JMenuItem();
        windowMenu = new javax.swing.JMenu();
        newFrameMI = new javax.swing.JMenuItem();
        nonModalDialogMI = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        cascadeMenuItem = new javax.swing.JMenuItem();
        tileVerticalMI = new javax.swing.JMenuItem();
        tileHorizontalMI = new javax.swing.JMenuItem();
        tileGridMI = new javax.swing.JMenuItem();
        lafMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        fileMenu.setText("File");

        exitMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        exitMI.setMnemonic('E');
        exitMI.setText("Exit");
        exitMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmClose(evt);
            }
        });
        fileMenu.add(exitMI);

        menuBar.add(fileMenu);

        windowMenu.setText("Window");

        newFrameMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        newFrameMI.setMnemonic('N');
        newFrameMI.setText("New Frame");
        newFrameMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFrameMIActionPerformed(evt);
            }
        });
        windowMenu.add(newFrameMI);

        nonModalDialogMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        nonModalDialogMI.setText("New Non-Modal Dialog");
        nonModalDialogMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonModalDialogMIActionPerformed(evt);
            }
        });
        windowMenu.add(nonModalDialogMI);
        windowMenu.add(jSeparator1);

        cascadeMenuItem.setMnemonic('C');
        cascadeMenuItem.setText("Tile Cascade");
        cascadeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cascadeMenuItemActionPerformed(evt);
            }
        });
        windowMenu.add(cascadeMenuItem);

        tileVerticalMI.setText("Tile Vertical");
        tileVerticalMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileVerticalMIActionPerformed(evt);
            }
        });
        windowMenu.add(tileVerticalMI);

        tileHorizontalMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        tileHorizontalMI.setText("Tile Horizontal");
        tileHorizontalMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileHorizontalMIActionPerformed(evt);
            }
        });
        windowMenu.add(tileHorizontalMI);

        tileGridMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        tileGridMI.setText("Tile Grid");
        tileGridMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tileGridMIActionPerformed(evt);
            }
        });
        windowMenu.add(tileGridMI);

        menuBar.add(windowMenu);

        lafMenu.setText("Look & Feel");
        menuBar.add(lafMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmClose();
    }//GEN-LAST:event_formWindowClosing

    private void confirmClose(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmClose
        confirmClose();
    }//GEN-LAST:event_confirmClose

    private void newFrameMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFrameMIActionPerformed
        newFrame();
    }//GEN-LAST:event_newFrameMIActionPerformed

    private void cascadeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cascadeMenuItemActionPerformed
        arrangeCurrentWindow(TileType.TILE_CASCADE);
    }//GEN-LAST:event_cascadeMenuItemActionPerformed

    private void tileVerticalMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileVerticalMIActionPerformed
        arrangeCurrentWindow(TileType.TILE_VERTICAL);
    }//GEN-LAST:event_tileVerticalMIActionPerformed

    private void tileHorizontalMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileHorizontalMIActionPerformed
        arrangeCurrentWindow(TileType.TILE_HORIZONTAL);
    }//GEN-LAST:event_tileHorizontalMIActionPerformed

    private void tileGridMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tileGridMIActionPerformed
        arrangeCurrentWindow(TileType.TILE_SQUARE);
    }//GEN-LAST:event_tileGridMIActionPerformed

    private void nonModalDialogMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonModalDialogMIActionPerformed
        newDialogFrame();
    }//GEN-LAST:event_nonModalDialogMIActionPerformed

    private void confirmClose() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DemoDesktopFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cascadeMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMI;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu lafMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newFrameMI;
    private javax.swing.JMenuItem nonModalDialogMI;
    private javax.swing.JMenuItem tileGridMI;
    private javax.swing.JMenuItem tileHorizontalMI;
    private javax.swing.JMenuItem tileVerticalMI;
    private javax.swing.JMenu windowMenu;
    // End of variables declaration//GEN-END:variables
}
