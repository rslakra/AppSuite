/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.java.charts;

import java.awt.*;
import java.io.Serializable;
import java.beans.*;

public class BarChartBean extends Canvas implements Serializable, PropertyChangeListener
{
    // Members
    private int iPercent_= 0;

    private Color fillColor_ = Color.blue;
    private Color floodColor_ = Color.red;
    private Color borderColor_ = Color.black;

    private int barWidth_;
    private int barHeight_;
    private int borderWidth_;

    // Methods

    // Default constructor
    public BarChartBean()
    {
	super();
	barWidth_ = 25;
	barHeight_ = 150;
	borderWidth_ = 1;
	setSize(barWidth_, barHeight_);
    }

    // This allows the user to set the bar size visually.
    public void setBounds(int x, int y, int width, int height)
    {
	super.setBounds(x,y,width,height);
	setBarWidth(width);
	setBarHeight(height);
    }

    // Respond to a property change event for the property
    // "value" by updating my value to a new value.
    public void propertyChange(PropertyChangeEvent event)
    {
	Integer newValue = (Integer)event.getNewValue();
	setPercent(newValue.intValue());
    }

    // Accessor methods
    public void setFloodColor(Color floodColor)
    {
	floodColor_ = floodColor;
    }

    public Color getFloodColor()
    {
	return floodColor_;
    }

    public void setFillColor(Color fillColor)
    {
	fillColor_ = fillColor;
    }

    public Color getFillColor()
    {
	return fillColor_;
    }

    public void setBorderColor(Color borderColor)
    {
	borderColor_ = borderColor;
    }

    public Color getBorderColor()
    {
	return borderColor_;
    }

    public void setBorderWidth(int borderWidth)
    {
	borderWidth_ = borderWidth;
    }
    public int getBorderWidth()
    {
	return borderWidth_;
    }

    public void setBarWidth(int barWidth)
    {
	barWidth_ = barWidth;
    }

    public int getBarWidth()
    {
	return barWidth_;
    }

    public void setBarHeight(int barHeight)
    {
	barHeight_ = barHeight;
    }

    public int getBarHeight()
    {
	return barHeight_;
    }

    // Set/Get methods for percent
    public void setPercent(int iPercent)
    {
	if (iPercent <= 100 && iPercent >= 0)
	{
	    iPercent_ = iPercent;
	    repaint();
	}
    }

    public int getPercent()
    {
	return iPercent_;
    }

    // Redraw bar chart.
    public void paint(Graphics g)
    {
	int i;

	// Fill color
	g.setColor(fillColor_);
	g.fillRect(0, 0, barWidth_, barHeight_);

	// Draw percent flood
	int f = (int)(0.5 + (iPercent_ / 100.0) * (barHeight_ - (2 * borderWidth_)));
 	g.setColor(floodColor_);
	g.fillRect(0, barHeight_ - borderWidth_ - f, barWidth_, f);

	// Draw border, if applicable
	if (borderWidth_ > 0)
	{
	    g.setColor(borderColor_);
	    g.fillRect(0, 0, borderWidth_, barHeight_);
	    g.fillRect(0, 0, barWidth_, borderWidth_);
	    g.fillRect(0, barHeight_ - borderWidth_, barWidth_, borderWidth_);
	    g.fillRect(barWidth_ - borderWidth_, 0, borderWidth_, barHeight_);
	}
    }
}