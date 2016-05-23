package com.apparatus.swing.mdi;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * http://www.comp.nus.edu.sg/~cs3283/ftp/Java/swingConnect/friends/mdi-swing/
 * mdi-swing.html
 * 
 * 
 * http://webbyit.blogspot.no/2011/03/managing-jinternalframes-within.html
 * 
 * http://stackoverflow.com/questions/16422939/jinternalframe-as-modal
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0
 */
public class InternalFrames extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;
	private DDesktopManager desktopManager;
	private DInternalFrame parentFrame;
	private JMenuBar menuBar;

	public InternalFrames() {
		super("Internal Frames Demo");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		// Display the desktop in a top-level frame
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		// Add menu-bar just above the desktop in a top-level frame
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// populate menuBar
		createMenuBar();

		desktopManager = new DDesktopManager();
		desktopPane.setDesktopManager(desktopManager);

		// create GUI
		DInternalFrame childFrame;
		JButton okButton = new JButton("Ok");

		// layer - default
		childFrame = createInternalFrame("Always Below", 0, 0, 300, 150);
		// Add components to internal frame...
		childFrame.getContentPane().add(okButton, BorderLayout.CENTER);
		// Add internal frame to desktop
		desktopPane.add(childFrame,
				new Integer(JDesktopPane.DEFAULT_LAYER.intValue() - 1));

		// layer - default
		childFrame = createInternalFrame("Default Layer #1", 25, 25, 300, 150);
		// Add components to internal frame...
		childFrame.getContentPane().add(okButton, BorderLayout.CENTER);
		// Add internal frame to desktop
		desktopPane.add(childFrame, JDesktopPane.DEFAULT_LAYER);

		// layer - default
		childFrame = createInternalFrame("Default Layer #2", 50, 50, 300, 150);
		// Add components to internal frame...
		childFrame.getContentPane().add(okButton, BorderLayout.CENTER);
		// Add internal frame to desktop
		desktopPane.add(childFrame, JDesktopPane.DEFAULT_LAYER);

		// layer - default
		childFrame = createInternalFrame("Always Above", 75, 75, 300, 150);
		// Add components to internal frame...
		childFrame.getContentPane().add(okButton, BorderLayout.CENTER);
		// Add internal frame to desktop
		desktopPane.add(childFrame,
				new Integer(JDesktopPane.DEFAULT_LAYER.intValue() + 1));

		// layer - pop-up
		childFrame = createInternalFrame("Popup Frame", 100, 100, 300, 150);
		// Add components to internal frame...
		childFrame.getContentPane().add(okButton, BorderLayout.NORTH);
		// Add internal frame to desktop
		desktopPane.add(childFrame,
				new Integer(JDesktopPane.POPUP_LAYER.intValue() + 1));

		// layer - modal
		childFrame = createInternalFrame("Modal Frame", 125, 125, 300, 150);
		// Add components to internal frame...
		childFrame.getContentPane().add(okButton, BorderLayout.NORTH);
		// Add internal frame to desktop
		desktopPane.add(childFrame,
				new Integer(JDesktopPane.MODAL_LAYER.intValue() + 1));

		// layer - pop-up
		childFrame = createInternalFrame("About Dialog (Modal)", 50, 150, 300,
				150);
		// Add components to internal frame...
		childFrame.getContentPane().add(okButton, BorderLayout.NORTH);
		// Add internal frame to desktop
		desktopPane.add(childFrame,
				new Integer(JDesktopPane.POPUP_LAYER.intValue() + 1));
		childFrame.moveToFront();

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AboutDialog(InternalFrames.this, "About Dialog",
						"Menu must be disabled!");
			}
		});

		// show
		setVisible(true);
	}

	private void createMenuBar() {
		// File Menu
		JMenu menuFile = new JMenu("File");
		// Exit Menu Item
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		menuFile.add(exitMenuItem);
		menuBar.add(menuFile);

		// Edit Menu
		JMenu menuEdit = new JMenu("Edit");
		// Undo Menu Item
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		menuEdit.add(undoMenuItem);
		menuBar.add(menuEdit);

		// View Menu
		JMenu menuView = new JMenu("View");
		// Zoom Menu Item
		JMenuItem zoomMenuItem = new JMenuItem("Zoom");
		menuView.add(zoomMenuItem);
		menuBar.add(menuView);

		// Window Menu
		JMenu menuWindow = new JMenu("Window");
		// Minimize Menu Item
		JMenuItem minimizeMenuItem = new JMenuItem("Minimize");
		menuWindow.add(minimizeMenuItem);
		menuBar.add(menuWindow);
	}

	/**
	 * Returns an internal frame with the specified bounds.
	 * 
	 * @param title
	 * @param bounds
	 * @return
	 */
	private DInternalFrame createInternalFrame(String title, Rectangle bounds) {
		return this.createInternalFrame(title, bounds.x, bounds.y,
				bounds.width, bounds.height);
	}

	/**
	 * Returns an internal frame with the specified bounds.
	 * 
	 * @param title
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	private DInternalFrame createInternalFrame(String title, int x, int y,
			int width, int height) {
		DInternalFrame dInternalFrame = new DInternalFrame(title);
		dInternalFrame.setBounds(x, y, width, height);
		// By default, internal frames are not visible; make it visible
		dInternalFrame.setVisible(true);

		return dInternalFrame;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new InternalFrames();
	}

	public void changeLookAndFeelAtRuntime() {
		// Set look and feel to checkbox-label
		// (equal to classname)

		try {
			UIManager.setLookAndFeel("");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Update complete gui-component tree
		// (all components will set to new lf)
		SwingUtilities.updateComponentTreeUI(this);

		// This code forces VM to repaint all:
		invalidate();
		validate();
		repaint();
	}

	public void showDialog() {
		// create a simple yes/no-dialog
		int res = JOptionPane.showConfirmDialog(this, "Really quit ?",
				"Yes/No-Dialog", JOptionPane.YES_NO_OPTION);
	}

}