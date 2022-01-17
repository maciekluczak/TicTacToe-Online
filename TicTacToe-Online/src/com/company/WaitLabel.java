package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class WaitLabel extends JLabel implements Runnable {
    private GameBufor gameBufor = new GameBufor();



    private final ImageIcon waitImage0, waitImage1, waitImage2,  waitImage3;
    private boolean shouldWait;

    public WaitLabel(){

         waitImage0 = new ImageIcon("image\\Wait for player .png");
         waitImage1 = new ImageIcon("image\\Wait for player ..png");
         waitImage2 = new ImageIcon("image\\Wait for player . . .png");
         waitImage3 = new ImageIcon("image\\Wait for player . . ..png");

        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);

        this.setIcon(waitImage0);
        this.setOpaque(false);



    }

    public void setShouldWait(boolean shouldWait) {
        this.shouldWait = shouldWait;
    }
    public boolean isShouldWait() {
        return shouldWait;
    }
    @Override
    public void run() {
        shouldWait = true;
        otherloop:
        do{
            this.setIcon(waitImage0);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(shouldWait == false){break otherloop;}

            this.setIcon(waitImage1);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(shouldWait == false){break otherloop;}

            this.setIcon(waitImage2);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(shouldWait == false){break otherloop;}

            this.setIcon(waitImage3);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }while(shouldWait == true);




    }

}
