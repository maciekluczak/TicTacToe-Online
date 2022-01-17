package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Default static Background during TicTacToe match
public class GameBackground extends JPanel implements ActionListener {
    private Timer timer;
    private final Image background;

    public GameBackground(){

        // Generate size of Background
        this.setSize(1100,700);

        //Read background appearance from image
        background= new ImageIcon("image\\notebookgameback.png").getImage();
    }

    public void paint(Graphics g){
        // Draw background method

        super.paint(g);
        Graphics2D g2d =(Graphics2D) g;
        g2d.drawImage(background,0,0,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}