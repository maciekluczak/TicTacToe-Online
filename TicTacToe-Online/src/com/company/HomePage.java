package com.company;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JLayeredPane {
    private MenuButton playButton;

    public HomePage() {

        //Creating HomePage components
        JPanel panelUp = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelDown = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelBackground = new JPanel();

        //Testing components - - - - - - - - - - - - - - - - - - -
        panelUp.setBackground(new Color(168,218,220));
        panelDown.setBackground(Color.green);
        panelLeft.setBackground(Color.blue);
        panelRight.setBackground(Color.blue);
        panelCenter.setBackground(Color.orange);

        panelUp.setOpaque(false);
        panelRight.setOpaque(false);
        panelLeft.setOpaque(false);
        panelDown.setOpaque(false);
        panelCenter.setOpaque(false);
        panelBackground.setOpaque(false);
        //- - - - - - - - - - - - - - - - - - - - - - - - - - -  - -

        //Initializing Page Structure (component sizes)
        panelUp.setPreferredSize(new Dimension(100,150));
        panelRight.setPreferredSize(new Dimension(200,100));
        panelLeft.setPreferredSize(new Dimension(200,100));
        panelDown.setPreferredSize(new Dimension(100,70));
        panelCenter.setPreferredSize(new Dimension(100,100));
        panelBackground.setSize(1100,700);

        panelBackground.setLayout(new BorderLayout());

        //Creating Logo Label
        ImageIcon ticImage = new ImageIcon("image\\TicTac.png");
        JLabel titleLabel1 = new JLabel();
        titleLabel1.setIcon(ticImage);
        titleLabel1.setHorizontalAlignment(JLabel.CENTER);
        titleLabel1.setVerticalAlignment(JLabel.TOP);

        ImageIcon tacImage = new ImageIcon("image\\toeImage.png");
        JLabel titleLabel2 = new JLabel();
        titleLabel2.setIcon(tacImage);
        titleLabel2.setHorizontalAlignment(JLabel.CENTER);
        titleLabel2.setVerticalAlignment(JLabel.TOP);
        panelCenter.setLayout(new BorderLayout());

        panelCenter.add(titleLabel1, BorderLayout.NORTH);
        panelCenter.add(titleLabel2, BorderLayout.SOUTH);

        //Creating Match Buttons
        panelUp.setLayout(new GridLayout(1,3,20,70));

        panelUp.add(new JLabel());
        playButton = new MenuButton("Let's Play!", 400, 350);
        panelUp.add(playButton);
        panelUp.add(new JLabel());


        //Implementation in the frame
        panelBackground.add(panelUp, BorderLayout.NORTH);
        panelBackground.add(panelCenter, BorderLayout.CENTER);
        panelBackground.add(panelRight, BorderLayout.EAST);
        panelBackground.add(panelLeft, BorderLayout.WEST);
        panelBackground.add(panelDown, BorderLayout.SOUTH);
        this.add(panelBackground, JLayeredPane.DRAG_LAYER);

        //Page background
        AnimatedBackground animationPanel= new AnimatedBackground();
        Thread animationPanelThread = new Thread(animationPanel);
        animationPanelThread.start();
        this.add(animationPanel, JLayeredPane.DEFAULT_LAYER);

        //visibility
        this.setVisible(true);

    }

    public MenuButton getPlayButton() {
        return playButton;
    }

}
