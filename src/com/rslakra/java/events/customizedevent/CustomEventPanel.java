/*
 * Created on Dec 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.rslakra.java.events.customizedevent;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomEventPanel extends JPanel implements TimerListener {
	private int currentValue = 1;
	private JProgressBar bar = new JProgressBar(1,100);
	
	public CustomEventPanel() {
		TimerComponent t = new TimerComponent(1);
	    t.addTimerListener(this);
	    add(bar);
	}

	public void timeElapsed(TimerEvent evt) {
		currentValue += 10;
		bar.setValue(currentValue);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Customized Event");
	    frame.setSize(300, 80);
	    frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });

	    Container contentPane = frame.getContentPane();
	    contentPane.add(new CustomEventPanel());

	    frame.show();
	  }
	}
