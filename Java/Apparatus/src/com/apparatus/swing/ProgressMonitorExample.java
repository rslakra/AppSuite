package com.apparatus.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 * A demonstration of the ProgressMonitor toolbar. A timer is used to induce
 * progress. This example also shows how to use the UIManager properties
 * associated with progress monitors.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 9, 2015 10:20:35 AM
 */
public class ProgressMonitorExample extends JFrame implements ActionListener {
	
	private static ProgressMonitor progressMonitor;
	private static int counter = 0;
	
	public ProgressMonitorExample() {
		super("Progress Monitor Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 100);
		progressMonitor = new ProgressMonitor(null, "Monitoring Progress", "Initializing ...", 0, 100);
		
		// Fire a timer every once in a while to update the progress.
		Timer timer = new Timer(500, this);
		timer.start();
		setVisible(true);
	}
	
	/**
	 * Invoked by the timer every half second. Simply place the progress monitor
	 * update on the event queue.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		SwingUtilities.invokeLater(new ProgressUpdate());
	}
	
	/**
	 * 
	 * @author Rohtash Singh (rohtash.singh@gmail.com)
	 * @version 1.0.0
	 * @since Jul 9, 2015 10:23:35 AM
	 */
	private class ProgressUpdate implements Runnable {
		public void run() {
			if(progressMonitor.isCanceled()) {
				progressMonitor.close();
				System.exit(1);
			}
			progressMonitor.setProgress(counter);
			progressMonitor.setNote("Operation: " + counter + "% completed!");
			counter += 2;
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		UIManager.put("ProgressMonitor.progressText", "Current Progress?");
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		new ProgressMonitorExample();
	}
	
}
