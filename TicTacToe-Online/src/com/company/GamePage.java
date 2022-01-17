package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePage extends JLayeredPane implements Runnable {
    private GameBufor gameBufor;
    private GameBoard game2D;
    private ExitButton exitButton;
    private TurnLabel turnLabel;
    private  JPanel panelRight, panelCenter, panelUp, panelLeft, panelDown, panelBackground;

    public ExitButton getExitButton() {
        return exitButton;
    }

    public GamePage() {
        gameBufor = new GameBufor();
         panelUp = new JPanel();
         panelRight = new JPanel();
         panelLeft = new JPanel();
         panelDown = new JPanel();
         panelCenter = new JPanel();
         panelBackground = new JPanel();

        panelUp.setBackground(new Color(168,218,220));
        panelDown.setBackground(Color.green);
        panelLeft.setBackground(Color.blue);
        panelRight.setBackground(Color.blue);
        panelCenter.setBackground(Color.red);

        panelUp.setOpaque(false);
        panelRight.setOpaque(false);
        panelLeft.setOpaque(false);
        panelDown.setOpaque(false);
        panelCenter.setOpaque(false);
        panelBackground.setOpaque(false);

        //Initializing Page Structure
        panelUp.setPreferredSize(new Dimension(100,50));
        panelRight.setPreferredSize(new Dimension(200,100));
        panelLeft.setPreferredSize(new Dimension(200,100));
        panelDown.setPreferredSize(new Dimension(100,70));
        panelCenter.setPreferredSize(new Dimension(300,150));
        panelBackground.setSize(1100,700);

        panelBackground.setLayout(new BorderLayout());
        panelCenter.setLayout(new BorderLayout());

        exitButton=new ExitButton(100, 50);
        exitButton.setAlignmentX(JLabel.BOTTOM_ALIGNMENT);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
        panelLeft.add(Box.createVerticalGlue());

        panelBackground.add(panelUp, BorderLayout.NORTH);
        panelBackground.add(panelCenter, BorderLayout.CENTER);
        panelBackground.add(panelRight, BorderLayout.EAST);
        panelBackground.add(panelLeft, BorderLayout.WEST);
        panelBackground.add(panelDown, BorderLayout.SOUTH);

        this.add(panelBackground, JLayeredPane.DRAG_LAYER);
        this.add(new GameBackground(), JLayeredPane.DEFAULT_LAYER);

        this.setVisible(true);

    }
    public void ExitGame() throws IOException {
        gameBufor.endConnectServer();
        gameBufor.setTurn(0);
        panelCenter.remove(game2D);
        panelCenter.remove(exitButton);


    }

    @Override
    public void run() {gameBufor.setIsGameRun(1);

        turnLabel = new TurnLabel(150,150);
        Thread turnThread= new Thread(turnLabel);
        panelRight.add(turnLabel);
        game2D = new GameBoard();
        panelCenter.add(game2D, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
        game2D.setVisible(true);
        Thread gameThread = new Thread(game2D);
        gameThread.start();
        turnThread.start();
        try {
            gameThread.join();

            gameBufor.setIsGameRun(0);
            panelRight.remove(turnLabel);
            panelCenter.add(exitButton, BorderLayout.SOUTH);


            panelCenter.add(Box.createHorizontalStrut(150));
            SwingUtilities.updateComponentTreeUI(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
