package com.webbyit.swing;

import javax.swing.JRadioButtonMenuItem;

/**
 * Help class for definition of Look and Feel menu items. Simply holds a look and feel
 * class name against the item.
 *
 * @author webbyit
 */
public class LAFMenuItem extends JRadioButtonMenuItem {

    private String lafClassName;

    private LAFMenuItem() {

    }

    /**
     * Constructor
     *
     * @param name 
     * @param lafClassName
     */
    public LAFMenuItem(String name, String lafClassName) {
        super(name);
        this.lafClassName = lafClassName;
    }

    public String getLAFClassName() {
        return lafClassName;
    }

}
