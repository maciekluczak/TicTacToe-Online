package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

//Custom dynamic/animated background for game menu
public class AnimatedBackground extends JPanel implements ActionListener, Runnable {
    private Timer timer;

    //Speed of animated X and O
    private int xXVelocity= -8;
    private int yXVelocity= 8;
    private int xOVelocity= 8;
    private int yOVelocity= -8;

    //Random Start Location of animated X and O
    private int xX= ThreadLocalRandom.current().nextInt(50, 500 + 1);
    private int yX= ThreadLocalRandom.current().nextInt(50, 500 + 1);
    private int xO= ThreadLocalRandom.current().nextInt(50, 500 + 1);
    private int yO= ThreadLocalRandom.current().nextInt(50, 500 + 1);

    private final Image cross, circle, background;

    public AnimatedBackground(){
    //Class constructor

        // Generate size of Background
        this.setSize(1100,700);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        //Read images from files
        cross= new ImageIcon("image\\cancel64.png").getImage();
        circle= new ImageIcon("image\\circumference (3).png").getImage();
        background= new ImageIcon("image\\notebook2.jpg").getImage();
    }

    public void paint(Graphics g){
    // Draw background method

        super.paint(g);
        Graphics2D g2d =(Graphics2D) g;
        g2d.drawImage(background,0,0,null);
        g2d.drawImage(cross,xX, yX, null);
        g2d.drawImage(circle,xO, yO, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //Draw animation method

        //Collision detection with window frame for X, object changes track of motion
        if(xX >= 1030 || xX<=0){xXVelocity=xXVelocity* -1;}
        if(yX >= 630 || yX <=0){yXVelocity=yXVelocity* -1;}
        yX= yX+ yXVelocity;
        xX= xX+ xXVelocity;

        //Collision detection with window frame for Y, object changes track of motion
        if(xO >= 1030 || xO<=0){xOVelocity=xOVelocity* -1;}
        if(yO >= 630 || yO <=0){yOVelocity=yOVelocity* -1;}
        yO= yO+ yOVelocity;
        xO= xO+ xOVelocity;
        repaint();
    }

    @Override
    public void run() {
    //Refresh rate for background

        timer= new Timer(10, this);
        timer.start();
    }
}
