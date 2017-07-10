package com.webbyit.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * An extended <code>JInternalFrame</code> that provides modality in a child/parent
 * hierarchy
 * 
 * @author webbyit
 */
public class ModalityInternalFrame extends JInternalFrame {

    protected JDesktopPane desktopPane;
    protected JComponent parent;
    protected ModalityInternalFrame childFrame;
    protected JComponent focusOwner;
    private boolean wasCloseable;

    public ModalityInternalFrame() {
        init(); // here to allow netbeans to use class in gui builder
    }

    public ModalityInternalFrame(JComponent parent) {
        this(parent, null);
    }

    public ModalityInternalFrame(JComponent parent, String title) {
        this(parent, title, false);
    }

    public ModalityInternalFrame(JComponent parent, String title, boolean resizable) {
        this(parent, title, resizable, false);
    }

    public ModalityInternalFrame(JComponent parent, String title, boolean resizable, boolean closeable) {
        this(parent, title, resizable, closeable, false);
    }

    public ModalityInternalFrame(JComponent parent, String title, boolean resizable, boolean closeable,
                                 boolean maximizable) {
        this(parent, title, resizable, closeable, maximizable, false);
    }

    public ModalityInternalFrame(JComponent parent, String title, boolean resizable, boolean closeable,
                                 boolean maximizable,
                                 boolean iconifiable) {
        super(title, resizable, closeable, maximizable, iconifiable);
        setParentFrame(parent);
        setFocusTraversalKeysEnabled(false);
        if (parent != null && parent instanceof ModalityInternalFrame) {
            ((ModalityInternalFrame) parent).setChildFrame(ModalityInternalFrame.this);
        }

        // Add glass pane
        ModalityInternalGlassPane glassPane = new ModalityInternalGlassPane(this);
        setGlassPane(glassPane);

        // Add frame listeners
        addFrameListener();

        // Add frame veto listenr
        addFrameVetoListener();

        init();

        // calculate size and position


    }

    private void setParentFrame(JComponent parent) {
        desktopPane = JOptionPane.getDesktopPaneForComponent(parent);
        this.parent = parent == null ? JOptionPane.getDesktopPaneForComponent(parent) : parent; // default to desktop if no parent given
    }

    public JComponent getParentFrame() {
        return parent;
    }

    public void setChildFrame(ModalityInternalFrame childFrame) {
        this.childFrame = childFrame;
    }

    public ModalityInternalFrame getChildFrame() {
        return childFrame;
    }

    public boolean hasChildFrame() {
        return (childFrame != null);
    }

    protected void addFrameVetoListener() {
        addVetoableChangeListener(new VetoableChangeListener() {

            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                if (evt.getPropertyName().equals(JInternalFrame.IS_SELECTED_PROPERTY)
                        && evt.getNewValue().equals(Boolean.TRUE)) {
                    if (hasChildFrame()) {
                        childFrame.setSelected(true);
                        if (childFrame.isIcon()) {
                            childFrame.setIcon(false);
                        }
                        throw new PropertyVetoException("no!", evt);
                    }
                }
            }
        });
    }

    /**
     * Method to control the display of the glasspane, dependant
     * on the frame being active or not
     */
    protected void addFrameListener() {
        addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                if (hasChildFrame() == true) {
                    getGlassPane().setVisible(true);
                    grabFocus();
                } else {
                    getGlassPane().setVisible(false);
                }
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                if (hasChildFrame() == true) {
                    getGlassPane().setVisible(true);
                    grabFocus();
                } else {
                    getGlassPane().setVisible(false);
                }
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                getGlassPane().setVisible(false);
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (parent != null && parent instanceof ModalityInternalFrame) {
                    ((ModalityInternalFrame) parent).childClosing();
                }
            }
        });
    }

    /**
     * Method to handle child frame closing and make this frame
     * available for user input again with no glasspane visible
     */
    protected void childClosing() {
        setClosable(wasCloseable);
        getGlassPane().setVisible(false);
        if (focusOwner != null) {
            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    try {
                        moveToFront();
                        setSelected(true);
                        focusOwner.grabFocus();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(ModalityInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            focusOwner.grabFocus();
        }
        getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setChildFrame(null);
    }

    /*
     * Method to handle child opening and becoming visible.
     */
    protected void childOpening() {
        // record the present focused component
        setClosable(false);
        focusOwner = (JComponent) getFocusOwner();
        grabFocus();
        getGlassPane().setVisible(true);
        getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    @Override
    public void show() {
        if (parent != null && parent instanceof ModalityInternalFrame) {
            // Need to inform parent its about to lose its focus due
            // to child opening
            ((ModalityInternalFrame) parent).childOpening();
        }
        calculateBounds();
        super.show();
    }

    protected void init() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 394, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 274, Short.MAX_VALUE));

        pack();
    }

    public void calculateBounds() {
        Dimension frameSize = getPreferredSize();
        Dimension parentSize = new Dimension();
        Dimension rootSize = new Dimension(); // size of desktop
        Point frameCoord = new Point();

        if (desktopPane != null) {
            rootSize = desktopPane.getSize(); // size of desktop
            frameCoord = SwingUtilities.convertPoint(parent, 0, 0, desktopPane);
            parentSize = parent.getSize();
        }

        //setBounds((rootSize.width - frameSize.width) / 2, (rootSize.height - frameSize.height) / 2, frameSize.width, frameSize.height);

        // We want dialog centered relative to its parent component
        int x = (parentSize.width - frameSize.width) / 2 + frameCoord.x;
        int y = (parentSize.height - frameSize.height) / 2 + frameCoord.y;

        // If possible, dialog should be fully visible
        int ovrx = x + frameSize.width - rootSize.width;
        int ovry = y + frameSize.height - rootSize.height;
        x = Math.max((ovrx > 0 ? x - ovrx : x), 0);
        y = Math.max((ovry > 0 ? y - ovry : y), 0);
        setBounds(x, y, frameSize.width, frameSize.height);
    }

    /**
     * Glass pane to overlay. Listens for mouse clicks and sets selected
     * on associated modal frame. Also if modal frame has no children make
     * class pane invisible
     */
    class ModalityInternalGlassPane extends JComponent {

        private ModalityInternalFrame modalFrame;

        public ModalityInternalGlassPane(ModalityInternalFrame frame) {
            modalFrame = frame;
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (modalFrame.isSelected() == false) {
                        try {
                            modalFrame.setSelected(true);
                            if (modalFrame.hasChildFrame() == false) {
                                setVisible(false);
                            }
                        } catch (PropertyVetoException e1) {
                            //e1.printStackTrace();
                        }
                    }
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
