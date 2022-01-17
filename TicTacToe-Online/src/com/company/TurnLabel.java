package com.company;

import javax.swing.*;
import java.awt.*;

public class TurnLabel extends JLabel implements Runnable{
    private int ExitButtonWidth;
    private int ExitButtonHeight;
    private ImageIcon imageLabel;
    private ImageIcon imageLabelAnimated;
    private GameBufor gameBufor = new GameBufor();

    public TurnLabel(int ExitButtonWidth, int ExitButtonHeight){

        this.ExitButtonHeight=ExitButtonHeight;
        this.ExitButtonWidth= ExitButtonWidth;

        this.setSize(ExitButtonWidth,ExitButtonHeight);
        this.setFont(new Font("MV Boli", Font.PLAIN, 20));



        ImageIcon image = new ImageIcon("image\\Turn.png");


        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        this.setIcon(image);
        this.setOpaque(false);

    }


    @Override
    public void run() {
        do{
            if(GameBufor.getTurn()%2==0){
                this.setText("Player 1");
            }
            else {
                this.setText("Player 2");
            }
        } while(gameBufor.getIsGameRun()==1);
    }

}
