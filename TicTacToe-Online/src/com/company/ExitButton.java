package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Custom implementation of extended Jlabel class, used as "Quit" - Button
public class ExitButton extends JLabel implements MouseListener {
    private int exitButtonWidth;
    private int exitButtonHeight;
    private final ImageIcon imageLabel, image;
    private final ImageIcon imageLabelAnimated;

    public ExitButton(int ExitButtonWidth, int ExitButtonHeight){

        //Construct custom sizes
        this.exitButtonHeight=ExitButtonHeight;
        this.exitButtonWidth= ExitButtonWidth;

        this.setSize(exitButtonWidth,exitButtonHeight);

        //Read appearance of the button from the image
        image = new ImageIcon("image\\exit32.png");
        Image convertImage = image.getImage();

        //Custom image resizing
        Image newImage= convertImage.getScaledInstance(exitButtonWidth, exitButtonHeight, Image.SCALE_SMOOTH);
        imageLabel = new ImageIcon(newImage);

        //second image of button animation
        imageLabelAnimated = new ImageIcon("image\\exit50.png");

        //set ExitButton location in Jpanel Layouts
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);

        //Default appearance
        this.setIcon(imageLabel);
        this.setOpaque(false);

        //Button response to mouse movement
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.setIcon(imageLabel);}

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(image); }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(imageLabel);}

}
