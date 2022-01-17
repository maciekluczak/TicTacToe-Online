package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Custom implementation of extended Jlabel class, used as "Back"- Button
public class BackButton extends JLabel implements MouseListener {
    private int backButtonWidth;
    private int backButtonHeight;
    private final ImageIcon imageLabel;
    private final ImageIcon imageLabelAnimated;

    public BackButton(int BackButtonWidth, int BackButtonHeight){
        //Construct custom sizes
        this.backButtonHeight=BackButtonHeight;
        this.backButtonWidth= BackButtonWidth;

        this.setSize(backButtonWidth,backButtonHeight);

        //Read appearance of the button from the image
        ImageIcon image = new ImageIcon("image\\back.png");
        Image convertImage = image.getImage();

        //Custom image resizing
        Image newImage= convertImage.getScaledInstance(backButtonWidth, backButtonHeight, Image.SCALE_SMOOTH);
        imageLabel = new ImageIcon(newImage);

        //second image of button animation
        imageLabelAnimated = new ImageIcon("image\\back.png");

        this.setIcon(imageLabel);
        this.setOpaque(false);

        //Button response to mouse movement
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(imageLabelAnimated);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(imageLabel);
    }
}